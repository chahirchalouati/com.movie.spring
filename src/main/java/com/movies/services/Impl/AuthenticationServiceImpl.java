package com.movies.services.Impl;

import com.movies.dtos.Requests.CreateUserRequest;
import com.movies.dtos.Requests.SignInRequest;
import com.movies.dtos.Responses.JWTResponse;
import com.movies.dtos.Responses.UserResponse;
import com.movies.helpers.JWTHelperImp;
import com.movies.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTHelperImp JWTHelperImpImpl;
    private final UserServiceImpl userService;

    @Override
    public ResponseEntity<UserResponse> createUser(CreateUserRequest createUserRequest) {
        final UserResponse user = userService.add(createUserRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<JWTResponse> signIn(SignInRequest request) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = JWTHelperImpImpl.generateJwtToken(authentication);
        return new ResponseEntity<>(new JWTResponse(jwt), HttpStatus.OK);

    }

}
