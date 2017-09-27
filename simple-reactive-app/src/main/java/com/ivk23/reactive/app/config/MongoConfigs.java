package com.ivk23.reactive.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Ivan Kos
 */
@Configuration
public class MongoConfigs extends AbstractReactiveMongoConfiguration {

    private MongoProperties mongoProperties;

    @Autowired
    public MongoConfigs(MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    @Bean
    ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json().build();
    }

    @Override
    protected String getDatabaseName() {
        return "test_db";
    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }
}