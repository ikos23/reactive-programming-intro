package com.ivk23.reactive.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author Ivan Kos
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class SimpleReactiveAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleReactiveAppApplication.class, args);
	}
}
