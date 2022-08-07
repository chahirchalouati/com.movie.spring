package com.movies.migrations;

import com.movies.dtos.Requests.CreateUserRequest;
import com.movies.repositories.UserRepository;
import com.movies.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("userMigrator")
@RequiredArgsConstructor
@Slf4j
public class UserMigrator implements Migration {

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void migrate() {
        log.info("START MIGRATION ON " + this.getClass().getName());
        if (userRepository.count() > 0) return;
        final CreateUserRequest defaultUser = new CreateUserRequest();
        defaultUser.setUserName("user");
        defaultUser.setFirstName("firstname");
        defaultUser.setLastName("lastname");
        defaultUser.setPassword("Test@user1");
        userService.add(defaultUser);

        log.info("END MIGRATION ON " + this.getClass().getName());
    }
}
