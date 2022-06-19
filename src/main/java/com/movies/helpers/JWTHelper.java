package com.movies.helpers;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JWTHelper {
    String generateJwtToken(Authentication authentication);

    String getUserNameFromJwtToken(String token);

    boolean validateJwtToken(String authToken , HttpServletRequest httpServletRequest);
}
