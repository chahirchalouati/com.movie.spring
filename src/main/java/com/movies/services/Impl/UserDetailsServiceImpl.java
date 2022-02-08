package com.movies.services.Impl;

import com.movies.domain.User;
import com.movies.repositories.UserRepository;
import com.movies.services.AppUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Chahir Chalouati
 */
@Service
public class UserDetailsServiceImpl implements AppUserDetailsService {

    private final UserRepository userRepository;
    private User user;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String msg = String.format("Can't find user with email : %s", username);
        return userRepository.findByUserName(username)
                .map(this::setUser)
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
}
