package com.ivk23.reactive.app.integration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivk23.reactive.app.cache.SimpleCache;
import com.ivk23.reactive.app.domain.GitterMessageRepository;
import com.ivk23.reactive.app.integration.model.GitterMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Ivan Kos
 */
@Service
public class ReactiveService {
    private static final Logger log = LoggerFactory.getLogger(ReactiveService.class);

    private static final String GITTER_STREAMING_API = "https://stream.gitter.im/v1/rooms/{roomId}/chatMessages?access_token={token}";

    @Autowired
    private SimpleCache cache;

    @Autowired
    private GitterMessageRepository repository;

    public Flux<String> getMessagesStream() {
        WebClient webClient = WebClient.create();
        log.debug("ReactiveService|getMessagesStream : token " + cache.get("token"));

        return webClient.get()
                .uri(GITTER_STREAMING_API, "59b13cb1d73408ce4f74bab6", cache.get("token"))
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve().bodyToFlux(String.class)
                .filter(this::isValid)
                .map(this::toModelObject)
                .map(this::toSimpleString)
                .log("category", Level.ALL, SignalType.ON_ERROR,
                        SignalType.ON_COMPLETE, SignalType.CANCEL, SignalType.REQUEST);
    }

    private boolean isValid(String content) {
        try {
            new ObjectMapper().readValue(content, GitterMessage.class);
            return true;
        } catch (IOException ex) {
            System.out.println("[FILTERING TRIGGERED]");
            return false;
        }
    }

    private GitterMessage toModelObject(String data) {
        try {
            final GitterMessage message = new ObjectMapper().readValue(data, GitterMessage.class);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String toSimpleString(GitterMessage msg) {
        if (msg != null) {
            return "Message from [" + msg.getFromUser().getDisplayName()
                    + "] : " + msg.getText() + ".";
        } else {
            return "";
        }
    }
}
