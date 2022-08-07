package com.movies.services.Impl;

import com.movies.configuration.properties.SecurityProps;
import com.movies.domain.User;
import com.movies.repositories.UserRepository;
import com.movies.services.AppUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Chahir Chalouati
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements AppUserDetailsService {
    private final UserRepository userRepository;
    private SecurityProps securityProps;
    private User user;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String msg = String.format("Can't find user with email : %s", username);
        log.info(msg);
        Optional<User> optional = username.equalsIgnoreCase(this.getSuperUser()) ? Optional.of(new User().setUserName(username)) : userRepository.findByUserName(username);
        return optional.map(this::setUser)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException(msg));
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public User setUser(User user) {
        this.user = user;
        return this.user;
    }

    @Override
    public String getSuperUser() {
        return "SUPER_ADMIN";
    }
}
