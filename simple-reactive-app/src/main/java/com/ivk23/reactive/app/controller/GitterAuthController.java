package com.ivk23.reactive.app.controller;

import com.amatkivskiy.gitter.sdk.model.response.AccessTokenResponse;
import com.amatkivskiy.gitter.sdk.sync.client.SyncGitterAuthenticationClient;
import com.ivk23.reactive.app.cache.SimpleCache;
import com.ivk23.reactive.app.integration.service.GitterAuthService;
import com.ivk23.reactive.app.utils.HtmlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.view.RedirectView;


/**
 * @author Ivan Kos
 */
@Controller
public class GitterAuthController {
    private static final Logger log = LoggerFactory.getLogger(GitterAuthController.class);

    @Autowired
    private GitterAuthService gitterAuthService;

    @Autowired
    private SimpleCache cache;


    @GetMapping("/login")
    public RedirectView login() {
        log.debug("Redirect to Gitter Login.");
        return new RedirectView(gitterAuthService.authGitter());
    }

    @GetMapping(value = "/tkn", produces = {MediaType.TEXT_HTML_VALUE})
    @ResponseBody
    public String getToken(@RequestParam("code") String code, Model model) {
        SyncGitterAuthenticationClient authenticationClient = new SyncGitterAuthenticationClient.Builder()
                .build();
        AccessTokenResponse accessTokenResponse = authenticationClient.getAccessToken(code);

        log.debug("Gitter access code : " + code);
        log.debug("Gitter access token : " + accessTokenResponse.accessToken);

        cache.add("code", code);
        cache.add("token", accessTokenResponse.accessToken);

        return new HtmlHelper().addContent("<h3>Success!</h3>")
                .addContent("<a href=\"/messages\">Show messages</a>")
                .getHtml();
    }

}
