package com.movies.services;

import com.movies.dtos.Requests.CreateUserRequest;
import com.movies.dtos.Requests.SignInRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author Chahir Chalouati
 */
public interface AuthenticationService {
    ResponseEntity<?> createUser(CreateUserRequest createUserRequest);

    ResponseEntity<?> signIn(SignInRequest request);
}
