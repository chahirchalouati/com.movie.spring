package com.movies.mappers;

import com.movies.domain.Profile;
import com.movies.domain.User;
import com.movies.dtos.Requests.CreateUserRequest;
import com.movies.dtos.Requests.UpdateUserRequest;
import com.movies.dtos.Responses.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chahir Chalouati
 */
@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class UserMapper {

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public RoleMapper roleMapper;
    @Mappings({
            @Mapping(target = "userName", source = "userName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(createUserRequest.getPassword()))")
    })
    public abstract User mapToUser(CreateUserRequest createUserRequest);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "userName", source = "userName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "roles", source = "roles"),
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(createUserRequest.getPassword()))")
    })
    public abstract User mapToUser(UpdateUserRequest createUserRequest);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "userName", source = "userName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "roles", expression = "java(roleMapper.map(user.getRoles().stream().collect(Collectors.toList())))"),
    })
    public abstract UserResponse mapToUserResponse(User user);

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "userName", source = "user.userName"),
            @Mapping(target = "lastName", source = "user.lastName"),
            @Mapping(target = "firstName", source = "user.firstName"),
            @Mapping(target = "roles",expression = "java(roleMapper.map(user.getRoles().stream().collect(Collectors.toList())))"),
            @Mapping(target = "avatar", source = "profile.avatar"),
    })
    public abstract UserResponse mapToUserResponse(User user, Profile profile);

    public abstract List<UserResponse> map(List<User> users);
}
