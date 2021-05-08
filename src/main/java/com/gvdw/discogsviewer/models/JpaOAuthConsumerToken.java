package com.gvdw.discogsviewer.models;

import java.util.Map;

import javax.persistence.*;

import org.springframework.security.oauth.consumer.OAuthConsumerToken;

/**
 * @author Gullian Van Der Walt
 * Created at 09:59 on May, 2021
 *
 * Custom impl of OAuth1 stored as JPA
 */
@Entity
@Table(name = "jpa_oauth_consumer_token")
public class JpaOAuthConsumerToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Integer id;
    @Column(name = "session_id")
    private String httpSessionId;
    @Column(name = "username")
    private String username;
    @Column(name = "oauth_resource_id")
    private String oauthResourceId;
    @Column(name = "oauth_token")
    private String oauthToken;
    @Column(name = "oauth_secret")
    private String oauthSecret;
    @Column(name = "oauth_access_token")
    private boolean isAccessToken;
    @ElementCollection
    @MapKeyColumn(name = "PARAM_KEY")
    @Column(name = "PARAM_VALUE")
    private Map<String, String> additionalParameters;

    private static final long serialVersionUID = -3314099155713269220L;

    public JpaOAuthConsumerToken() {
    }

    /**
     * Turn a plain OAuthConsumerToken into a JpaOAuthConsumerToken
     *
     * @param httpSessionId
     * @param username
     * @param oauthToken
     */
    public JpaOAuthConsumerToken(String httpSessionId, String username,OAuthConsumerToken oauthToken) {
        this.httpSessionId = httpSessionId;
        this.oauthResourceId = oauthToken.getResourceId();
        this.oauthToken = oauthToken.getValue();
        this.oauthSecret = oauthToken.getSecret();
        this.isAccessToken = oauthToken.isAccessToken();
        this.additionalParameters = oauthToken.getAdditionalParameters();
    }

    public OAuthConsumerToken toOAuthConsumerToken() {
        OAuthConsumerToken returnToken = new OAuthConsumerToken();
        returnToken.setResourceId(getOauthResourceId());
        returnToken.setValue(getOauthToken());
        returnToken.setSecret(getOauthSecret());
        returnToken.setAccessToken(isAccessToken());
        returnToken.setAdditionalParameters(getAdditionalParameters());
        return returnToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHttpSessionId() {
        return httpSessionId;
    }

    public void setHttpSessionId(String httpSessionId) {
        this.httpSessionId = httpSessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOauthResourceId() {
        return oauthResourceId;
    }

    public void setOauthResourceId(String oauthResourceId) {
        this.oauthResourceId = oauthResourceId;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getOauthSecret() {
        return oauthSecret;
    }

    public void setOauthSecret(String oauthSecret) {
        this.oauthSecret = oauthSecret;
    }

    public boolean isAccessToken() {
        return isAccessToken;
    }

    public void setAccessToken(boolean accessToken) {
        isAccessToken = accessToken;
    }

    public Map<String, String> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(Map<String, String> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "JpaOAuthConsumerToken{" +
                "id=" + id +
                ", httpSessionId='" + httpSessionId + '\'' +
                ", username='" + username + '\'' +
                ", oauthResourceId='" + oauthResourceId + '\'' +
                ", oauthToken='" + oauthToken + '\'' +
                ", oauthSecret='" + oauthSecret + '\'' +
                ", isAccessToken=" + isAccessToken +
                ", additionalParameters=" + additionalParameters +
                '}';
    }
}
