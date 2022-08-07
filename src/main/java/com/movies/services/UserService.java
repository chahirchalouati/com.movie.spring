package com.movies.services;

import com.movies.dtos.Requests.CreateUserRequest;
import com.movies.dtos.Requests.UpdateUserRequest;
import com.movies.dtos.Responses.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Chahir Chalouati
 */
public interface UserService {

    UserResponse add(CreateUserRequest createUserRequest);

    UserResponse update(UpdateUserRequest createUserRequest);

    boolean deleteById(String id);

    boolean deleteByUserName(String username);

    UserResponse findByUserName(String username);

    Page<UserResponse> findAll(Pageable pageable);
}
