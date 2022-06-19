package com.movies.migrations;

import com.movies.domain.Role;
import com.movies.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static com.movies.utils.RoleUtils.DEFAULT_ADMIN_ROLES;
import static com.movies.utils.RoleUtils.DEFAULT_USER_ROLES;

@Configuration
@RequiredArgsConstructor
public class RoleMigrator implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() > 0) return;
        Arrays.stream(DEFAULT_USER_ROLES).forEach(role -> roleRepository.save(new Role().setRole(role)));
        Arrays.stream(DEFAULT_ADMIN_ROLES).forEach(role -> roleRepository.save(new Role().setRole(role)));

    }
}
