package com.ivk23.reactive.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author Ivan Kos
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public Mono<String> test1(@RequestParam(defaultValue = "null") String name, @RequestParam(defaultValue = "-1") int delay) {
        if (delay == -1) {
            return Mono.just("Hello, " + name);
        } else {
            return Mono.just("Hello, " + name).delayElement(Duration.ofSeconds(delay));
        }
    }

    @GetMapping("/test/exchange")
    public Mono<Void> exchange(ServerWebExchange exchange) {
        final ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        return response.writeWith(Flux.empty());
    }

}
