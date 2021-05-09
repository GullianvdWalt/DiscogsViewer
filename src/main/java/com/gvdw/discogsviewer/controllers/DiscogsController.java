package com.gvdw.discogsviewer.controllers;

import com.gvdw.discogsviewer.models.JpaOAuthConsumerToken;
import com.gvdw.discogsviewer.repositories.ConsumerTokenRepository;
import com.gvdw.discogsviewer.services.DiscogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.OAuthConsumerToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gullian Van Der Walt
 * Created at 11:43 on May, 2021
 */
@RestController
@RequestMapping("/api/discogs")
public class DiscogsController {

    private static final String OAUTH_CALLBACK = "/oauthCallback";
    private static final Logger logger = LoggerFactory.getLogger(DiscogsController.class);

    // Constructor Injection
    private final DiscogsService discogsService;
    private final ConsumerTokenRepository consumerTokenRepository;

    @Autowired
    public DiscogsController(DiscogsService discogsService, ConsumerTokenRepository tokenStore) {
        this.discogsService = discogsService;
        this.consumerTokenRepository = tokenStore;
    }

    @GetMapping("/")
    public String index(){
        return "Welcome To Discogs Viewer";
    }

    // Discogs Authentication
    // TODO SKIP IF ALREADY AUTHENTICATED
    @GetMapping("/login")
    public RedirectView login1(HttpSession session) {
        RedirectView rv = new RedirectView();
        String appBaseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        //JpaOAuthConsumerToken testToken = consumerTokenRepository.findAll().get(0);
        OAuthConsumerToken requestToken = discogsService.fetchRequestToken(appBaseUrl + OAUTH_CALLBACK);
        String username = "gullian101";
        JpaOAuthConsumerToken jpaToken = new JpaOAuthConsumerToken(session.getId(),requestToken);
        consumerTokenRepository.save(jpaToken);
        rv.setUrl(DiscogsService.AUTHORIZATION_URL + "?oauth_token=" + requestToken.getValue());
        return rv;
    }
    @GetMapping(OAUTH_CALLBACK)
    public void oauthCallback(@RequestParam Map<String, String> requestParams, HttpSession session, Model model) {
        if (requestParams.containsKey("denied")) {
           model.addAttribute("status", "denied");
        } else if (requestParams.containsKey("oauth_token") && requestParams.containsKey("oauth_verifier")) {
            JpaOAuthConsumerToken requestToken = consumerTokenRepository.findById(session.getId()).orElseThrow();
            // TODO check if request token is already an access token (that means OAuth has been completed).
            OAuthConsumerToken accessToken = discogsService.fetchAccessToken(requestToken.toOAuthConsumerToken(),
                    requestParams.get("oauth_verifier"));
            JpaOAuthConsumerToken jpaToken = new JpaOAuthConsumerToken(session.getId(),accessToken);
            jpaToken.setUsername(discogsService.getUserName(jpaToken));
            consumerTokenRepository.save(jpaToken);
//            mv.addObject("username", jpaToken.getUsername());
            model.addAttribute("username", jpaToken.getUsername());
            model.addAttribute("status", "success");
        } else {
            model.addAttribute("status", "unrecognised OAuth response from Discogs");
            logger.error("Session - " + session.getId() + ", unrecognised OAuth response from Discogs - \n"
                    + requestParams.toString());
            throw new IllegalArgumentException("Unrecognised OAuth response from Discogs");
        }
    }

    @GetMapping("/checkUserAuth")
    public void checkUserAuth(HttpSession session, Model model){
        String sessionId = session.getId();
        model.addAttribute("isAuthenticated", authCheck());
    }

    /**
     * Check to see if a user is authenticated before we send requests.
     * @return authStatus
     */
    private boolean authCheck() {
        Optional<JpaOAuthConsumerToken> userToken = Optional.ofNullable(consumerTokenRepository.findAll().get(0));
        if (userToken.isPresent()) {
            if (userToken.get().isAccessToken()) {
                return true;
            } else {
                throw new IllegalStateException(
                        "OAuth process not complete (it was started, but we have no access token)");
            }
        } else {
            throw new IllegalStateException("User not found in OAuth data store. ");
        }
    }
}
