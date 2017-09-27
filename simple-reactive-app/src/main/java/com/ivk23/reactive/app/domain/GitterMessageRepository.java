package com.ivk23.reactive.app.domain;

import com.ivk23.reactive.app.integration.model.GitterMessage;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ivan Kos
 */
public interface GitterMessageRepository extends ReactiveCrudRepository<GitterMessage, String> {

    Mono<Void> save(Publisher<GitterMessage> document);

    Mono<GitterMessage> findById(String id);

    Flux<GitterMessage> findAll();

}
