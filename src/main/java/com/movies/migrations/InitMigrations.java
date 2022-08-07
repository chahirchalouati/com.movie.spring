package com.movies.migrations;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitMigrations implements CommandLineRunner {
    private final RoleMigrator roleMigrator;
    private final UserMigrator userMigrator;
    private final MovieMigrator movieMigrator;

    @Override
    public void run(String... args) {
        this.roleMigrator.migrate();
        this.userMigrator.migrate();
        this.movieMigrator.migrate();

    }
}
