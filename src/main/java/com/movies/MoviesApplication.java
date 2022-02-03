package com.movies;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * @author Chahir Chalouati
 */
@SpringBootApplication
@EnableMongoAuditing
@ConfigurationPropertiesScan("com.movies.configuration.properties")
public class MoviesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
