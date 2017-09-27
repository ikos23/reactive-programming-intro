package com.ivk23.reactive.app.controller;

import com.ivk23.reactive.app.integration.model.GitterMessage;
import com.ivk23.reactive.app.integration.service.ReactiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Random;

/**
 * @author Ivan Kos
 */
@Controller
public class ReactiveController {
    private static final Logger log = LoggerFactory.getLogger(ReactiveController.class);

    @Autowired
    private ReactiveService reactiveService;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @GetMapping(value = "/messages", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    @ResponseBody
    public Flux<String> getMessages() {
        return reactiveService.getMessagesStream();
    }

    @GetMapping(value = "/saved", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    @ResponseBody
    public Flux<String> getSavedMessages() {
        return reactiveMongoTemplate.findAll(GitterMessage.class)
                .map((msg) -> {
                    return msg.toString();
                }).defaultIfEmpty("Not found !");
    }

    @GetMapping(value = "/fake", produces = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    public Mono<String> createFake() {
        GitterMessage m = new GitterMessage("Hello, User" + new Random().nextInt(100),
                Instant.now(), "gitter" + new Random().nextInt(100));
        return reactiveMongoTemplate.save(m)
            .doOnSuccess((c) -> {
                log.info("DONE! Message has been saved.");
            }).map(m1 -> "Saved #" + m1.getId());
    }

}
