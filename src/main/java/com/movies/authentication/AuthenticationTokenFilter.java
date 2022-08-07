package com.movies.authentication;

import com.movies.configuration.properties.SecurityProps;
import com.movies.helpers.JWTHelperImp;
import com.movies.services.Impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chahir Chalouati
 */
@Slf4j
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JWTHelperImp JWTHelperImpImpl;
    @Autowired
    private SecurityProps securityProps;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = this.parseJwt(request);
            if (jwt != null && JWTHelperImpImpl.validateJwtToken(jwt, request)) {
                String username = JWTHelperImpImpl.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (UsernameNotFoundException exception) {
            log.warn("AuthenticationTokenFilter::doFilterInternal :{ } ", exception);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader(securityProps.getAuthorizationHeader());
        return this.validateAuthorizationHeader(authorizationHeader) ? authorizationHeader.substring(securityProps.getTokenPrefix().length()) : null;
    }

    private boolean validateAuthorizationHeader(String authorizationHeader) {
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(securityProps.getTokenPrefix());
    }
}
