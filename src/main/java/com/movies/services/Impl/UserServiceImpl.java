package com.movies.services.Impl;

import com.movies.DTOs.Requests.CreateUserRequest;
import com.movies.DTOs.Requests.UpdateUserRequest;
import com.movies.DTOs.Responses.UserResponse;
import com.movies.domain.Role;
import com.movies.domain.User;
import com.movies.exceptions.EntityNotFoundException;
import com.movies.mappers.UserMapper;
import com.movies.repositories.RoleRepository;
import com.movies.repositories.UserRepository;
import com.movies.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
/**
 * @author Chahir Chalouati
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    private static void userNotFound() {
        throw new EntityNotFoundException("user not found");
    }

    @Override
    public UserResponse add(CreateUserRequest createUserRequest) {
        final User user = this.userMapper.mapToUser(createUserRequest);
        this.validateRoles(createUserRequest.getRoles(), user);
        final User storedUser = this.userRepository.save(user);
        return this.userMapper.mapToUserResponse(storedUser);
    }


    @Override
    public UserResponse update(UpdateUserRequest updateUserRequest) {
        this.userRepository.findById(updateUserRequest.getId()).ifPresentOrElse(this.userRepository::delete, UserServiceImpl::userNotFound);
        final User user = this.userMapper.mapToUser(updateUserRequest);
        this.validateRoles(updateUserRequest.getRoles(), user);
        final User storedUser = this.userRepository.save(user);
        return this.userMapper.mapToUserResponse(storedUser);
    }

    @Override
    public boolean deleteById(String id) {
        this.userRepository.findById(id).ifPresentOrElse(this.userRepository::delete, UserServiceImpl::userNotFound);
        return true;
    }

    @Override
    public boolean deleteByUserName(String username) {
        this.userRepository.findByUserName(username).ifPresentOrElse(this.userRepository::delete, UserServiceImpl::userNotFound);
        return true;
    }

    @Override
    public UserResponse findByUserName(String username) {
        return this.userRepository.findByUserName(username).map(this.userMapper::mapToUserResponse).orElseThrow(notFoundException());
    }

    private Supplier<RuntimeException> notFoundException() {
        return () -> {
            throw new EntityNotFoundException("user not found");
        };
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(pageable);
        return new PageImpl<>(this.userMapper.map(userPage.getContent()), pageable, userPage.getContent().size());
    }

    private void validateRoles(Set<Role> roles, User user) {
        final Set<Role> roleSet = roles.stream()
                .map(payload -> this.roleRepository.findByRole(payload.getRole()).orElseThrow(() -> new EntityNotFoundException(String.format("role %s does not exists", payload.getRole()))))
                .collect(Collectors.toSet());
        user.setRoles(roleSet);
    }
}
