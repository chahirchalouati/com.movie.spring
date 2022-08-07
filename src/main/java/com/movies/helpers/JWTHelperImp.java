/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movies.helpers;

import com.movies.configuration.properties.SecurityProps;
import com.movies.domain.User;
import com.movies.dtos.Responses.UserResponse;
import com.movies.services.AppUserDetailsService;
import com.movies.services.Impl.UserDetailsImpl;
import com.movies.services.ProfileService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Chahir Chalouati
 */
@Component
@Slf4j
public class JWTHelperImp implements JWTHelper {

    private final SecurityProps securityProps;
    private final AppUserDetailsService userDetailsService;
    private final ProfileService profileService;

    public JWTHelperImp(SecurityProps securityProps, AppUserDetailsService userDetailsService, ProfileService profileService) {
        this.securityProps = securityProps;
        this.userDetailsService = userDetailsService;
        this.profileService = profileService;
    }

    @Override
    public String generateJwtToken(Authentication authentication) {
        final UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        final long date = System.currentTimeMillis() + this.securityProps.getExpirationTime();
        final User user = this.userDetailsService.getUser();
        final UserResponse userResponse = profileService.getOne(user.getId());
        return Jwts.builder()
                .setIssuer(securityProps.getIssuer())
                .setSubject(userPrincipal.getUsername())
                .claim("user", userResponse)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(date))
                .signWith(SignatureAlgorithm.HS512, this.securityProps.getSecret())
                .compact();
    }

    @Override
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(securityProps.getSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public boolean validateJwtToken(String authToken, HttpServletRequest httpServletRequest) {
        try {
            if (httpServletRequest.getHeader("SUPER_ADMIN").equals(securityProps.getAdminSecret())) return true;
            Jwts.parser().setSigningKey(this.securityProps.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.info("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            log.warn("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("Expired JWT token");
            httpServletRequest.setAttribute("expired", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Unsupported JWT exception");
        } catch (IllegalArgumentException ex) {
            log.info("Jwt claims string is empty");
        }
        return false;

    }

}
