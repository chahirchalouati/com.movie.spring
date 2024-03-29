package com.movies.configuration;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanUtils {
    @Bean
    public Faker faker() {
        return new Faker();
    }
}
