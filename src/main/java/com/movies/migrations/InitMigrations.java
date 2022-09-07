package com.movies.migrations;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InitMigrations implements CommandLineRunner {
    private final List<Migration> migrations;

    @Override
    public void run(String... args) {
        migrations.forEach(Migration::migrate);

    }
}
