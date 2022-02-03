package com.movies.services;

import com.movies.DTOs.Requests.CreateUserRequest;
import com.movies.DTOs.Requests.SignInRequest;
import org.springframework.http.ResponseEntity;
/**
 * @author Chahir Chalouati
 */
public interface AuthenticationService {
    ResponseEntity<?> createUser(CreateUserRequest createUserRequest);

    ResponseEntity<?> signIn(SignInRequest request);
}
