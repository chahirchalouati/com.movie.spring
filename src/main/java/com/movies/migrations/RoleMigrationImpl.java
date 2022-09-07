package com.movies.migrations;

import com.movies.configuration.properties.RoleProps;
import com.movies.domain.Role;
import com.movies.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("roleMigrator")
@RequiredArgsConstructor
@Slf4j
public class RoleMigrationImpl implements Migration {
    private final RoleProps roleProps;
    private final RoleRepository roleRepository;

    @Override
    public void migrate() {
        log.info("START MIGRATION ON " + this.getClass().getName());
        roleProps.getUserRoles().stream()
                .filter(role -> !roleRepository.existsByRole(role))
                .peek(role -> log.info("added new role " + role))
                .forEach(role -> roleRepository.save(new Role().setRole(role)));

        roleProps.getAdminRoles().stream()
                .filter(role -> !roleRepository.existsByRole(role))
                .peek(role -> log.info("added new role " + role))
                .forEach(role -> roleRepository.save(new Role().setRole(role)));
        log.info("END MIGRATION ON " + this.getClass().getName());
    }
}
