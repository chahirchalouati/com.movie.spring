package com.movies.controllers;

import com.movies.DTOs.Requests.CreateUserRequest;
import com.movies.DTOs.Requests.SignInRequest;
import com.movies.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Chahir Chalouati
 */
@RestController
@AllArgsConstructor
public class AuthRestController {

    private final AuthenticationService authenticationService;


    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid  SignInRequest request) {
        return this.authenticationService.signIn(request);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid CreateUserRequest request) {
        return this.authenticationService.createUser(request);
    }

}
