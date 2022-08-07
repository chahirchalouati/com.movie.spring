package com.movies.services;

import com.movies.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Chahir Chalouati
 */
public interface AppUserDetailsService extends UserDetailsService {
    User getUser();

    User setUser(User user);

    String getSuperUser();
}
