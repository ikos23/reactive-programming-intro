package com.ivk23.web.app.intergation.service;

import com.amatkivskiy.gitter.sdk.GitterOauthUtils;
import com.amatkivskiy.gitter.sdk.credentials.GitterDeveloperCredentials;
import com.amatkivskiy.gitter.sdk.credentials.SimpleGitterCredentialsProvider;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Kos
 */
@Service
public class GitterAuthService {

    public String authGitter() {
        GitterDeveloperCredentials.init(new SimpleGitterCredentialsProvider(
                "d5b0f7446fad0733d4dd5f91de88847a8ec42e0d",
                "fab20e0f00e27e5c37be9d34d51eb71170e2b31d",
                "http://localhost:8080/tkn"));
        String gitterAccessUrl = GitterOauthUtils.buildOauthUrl();
        return gitterAccessUrl;
    }

}