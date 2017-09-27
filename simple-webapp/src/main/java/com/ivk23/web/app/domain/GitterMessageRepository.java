package com.ivk23.web.app.domain;

import com.ivk23.web.app.intergation.model.GitterMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Kos
 */
public interface GitterMessageRepository extends CrudRepository<GitterMessage, String> {
    GitterMessage save(GitterMessage entity);

    List<GitterMessage> findAll();

    Optional<GitterMessage> findById(String id);
}
