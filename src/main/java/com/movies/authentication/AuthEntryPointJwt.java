/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movies.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        final String expired = (String) request.getAttribute("expired");
        if (expired != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, expired);
            log.error("AuthEntryPointJwt::commence : {} {}", HttpServletResponse.SC_UNAUTHORIZED, expired);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Login Credentials");
            log.error("AuthEntryPointJwt::commence : {} {}", HttpServletResponse.SC_UNAUTHORIZED, "Invalid Login Credentials");
        }
    }

}
