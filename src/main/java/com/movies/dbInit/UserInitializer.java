package com.movies.dbInit;

import com.movies.DTOs.Requests.CreateUserRequest;
import com.movies.repositories.UserRepository;
import com.movies.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) return;
        final CreateUserRequest defaultUser = new CreateUserRequest();
        defaultUser.setUserName("user");
        defaultUser.setFirstName("firstname");
        defaultUser.setLastName("lastname");
        defaultUser.setPassword("Test@user1");
        userService.add(defaultUser);
    }
}
