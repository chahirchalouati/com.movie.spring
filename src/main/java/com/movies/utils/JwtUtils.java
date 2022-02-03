/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movies.utils;

import com.movies.configuration.properties.SecurityProps;
import com.movies.services.Impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chahir Chalouati
 */
@Component
@Slf4j
@AllArgsConstructor
@Data
public class JwtUtils {

    private final SecurityProps securityProps;

    public String generateJwtToken(Authentication authentication) {
        final UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        final long date = System.currentTimeMillis() + this.securityProps.getExpirationTime();
        List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setIssuer("com.movies")
                .setSubject(userPrincipal.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(date))
                .signWith(SignatureAlgorithm.HS512, this.securityProps.getSecret())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(securityProps.getSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken, HttpServletRequest httpServletRequest) {
        try {
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
