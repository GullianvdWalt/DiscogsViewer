package com.gvdw.discogsviewer.services;

import com.gvdw.discogsviewer.models.JpaOAuthConsumerToken;
import org.springframework.security.oauth.consumer.OAuthConsumerToken;
import org.springframework.stereotype.Service;

/**
 * @author Gullian Van Der Walt
 * Created at 09:55 on May, 2021
 */
public interface DiscogsService {
    public final String APP_ID = "discogs";
    // Discogs Auth Flow
    public final String REQUEST_TOKEN_URL = "https://api.discogs.com/oauth/request_token";
    public final String AUTHORIZATION_URL = "https://www.discogs.com/oauth/authorize";
    public final String ACCESS_TOKEN_URL = "https://api.discogs.com/oauth/access_token";

    public final String USER_AGENT = "discogs-viewer/0.0.1 +http:/gvdw.com";
    public final String IDENTITY_CHECK_URL = "https://api.discogs.com/oauth/identity";


    // Endpoints
    // Example : https://api.discogs.com/users/username
    public final String USERS_PREFIX_URL = "https://api.discogs.com/users";
    public final String USER_SUBMISSIONS_URL = "/submissions";
    public final String USER_COLLECTION_URL = "/collection/folders/0/releases";
    public final String ITEM_BY_RELEASE_URL_TEST = "https://api.discogs.com/users/gullian101/collection/releases/2890309";
    public final String DISCOGS_SEARCH_URL = "https://api.discogs.com/database/search";
    public final String DISCOGS_GET_TEST_RELEASE_URL = "https://api.discogs.com/database/search?release_title=nevermind&artist=nirvana&type=master&format=vinyl&page=1";
    public final String DISCOGS_USERS_URL = "https://api.discogs.com/users";
    public final String MASTER_RELEASE_URL = "https://api.discogs.com/masters/79256";

    OAuthConsumerToken fetchRequestToken(String callbackURL);

    OAuthConsumerToken fetchAccessToken(OAuthConsumerToken oAuthConsumerToken, String oAuthVerifier);

    /**
     * Get the Discogs username that the given access token relates to.
     * @param accessToken
     * @return username
     */
    String getUserName(JpaOAuthConsumerToken accessToken);
    /**
     * Gets a user's submissions if authenticated.
     * @param accessToken
     * @return JSON
     */
    String getUserSubmissions(JpaOAuthConsumerToken accessToken);
    /**
     * Gets a user's collection if authenticated
     * @param accessToken
     * @return JSON
     */
    String getUserCollection(JpaOAuthConsumerToken accessToken);
    /**
     * Gets a specific release
     * @param accessToken
     * @return JSON
     */
    String getRelease(JpaOAuthConsumerToken accessToken);

    //    /**
//     * Returns a list of Releases matching the given search parameters.
//     *
//     * @param currTag
//     * @param accessToken
//     * @return
//     */
//    ArrayList<Release> getReleaseList(JpaOAuthConsumerToken accessToken)
}
