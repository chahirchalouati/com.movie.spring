package com.movies.mappers;

import com.movies.DTOs.Requests.CreateUserRequest;
import com.movies.DTOs.Requests.UpdateUserRequest;
import com.movies.DTOs.Responses.UserResponse;
import com.movies.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
/**
 * @author Chahir Chalouati
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    public PasswordEncoder passwordEncoder;

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
            @Mapping(target = "roles", source = "roles"),
    })
    public abstract UserResponse mapToUserResponse(User user);

    public abstract List<UserResponse> map(List<User> users) ;
}
