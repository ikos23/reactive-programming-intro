package com.ivk23.web.app.controller;

import com.amatkivskiy.gitter.sdk.model.response.AccessTokenResponse;
import com.amatkivskiy.gitter.sdk.sync.client.SyncGitterAuthenticationClient;
import com.ivk23.web.app.domain.GitterMessageRepository;
import com.ivk23.web.app.intergation.model.GitterMessage;
import com.ivk23.web.app.intergation.service.GitterAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ivan Kos
 */
@Controller
public class SimpleController {
    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private GitterAuthService gitterAuthService;

    @Autowired
    private GitterMessageRepository gitterMessageRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "lol");
        return "index";
    }

    @GetMapping("/messages")
    public String messages(@RequestParam("access_token") String token, Model model) throws Exception {
        final RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GitterMessage[]> response = null;

        response = restTemplate.getForEntity(new URI(
                new StringBuilder("https://api.gitter.im")
                    .append("/v1/rooms/59b13cb1d73408ce4f74bab6/chatMessages?limit=50")
                    .append("&")
                    .append("access_token=")
                    .append(token)
                    .toString()),
                GitterMessage[].class);

        final GitterMessage[] messages = response.getBody();
        // save to DB for statistic
        Arrays.stream(messages)
                .map((m) -> {
                    m.setUsername(m.getFromUser().getUsername());
                    return m;
                })
                .forEach(this.gitterMessageRepository::save);

        model.addAttribute("messages", messages);

        return "messages";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:" + gitterAuthService.authGitter();
    }

    @GetMapping("/tkn")
    public String login(@RequestParam("code") String code, Model model) {
        SyncGitterAuthenticationClient authenticationClient = new SyncGitterAuthenticationClient.Builder()
                .build();
        AccessTokenResponse accessTokenResponse = authenticationClient.getAccessToken(code);

        log.debug("Gitter access code : " + code);
        log.debug("Gitter access token : " + accessTokenResponse.accessToken);

        model.addAttribute("token", accessTokenResponse.accessToken);

        return "account";
    }

    @GetMapping("/saved")
    public String getFromDB(Model model) {
        final List<GitterMessage> all = gitterMessageRepository.findAll();
        final Map<String, List<GitterMessage>> map = all.stream().collect(Collectors.groupingBy(GitterMessage::getUsername));

        model.addAttribute("data", map);

        return "statistic";
    }

}
