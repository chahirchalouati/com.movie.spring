package com.movies.mappers;

import com.movies.DTOs.Requests.CreateUserRequest;
import com.movies.DTOs.Requests.UpdateUserRequest;
import com.movies.DTOs.Responses.RoleResponse;
import com.movies.DTOs.Responses.UserResponse;
import com.movies.domain.Profile;
import com.movies.domain.Role;
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
public abstract class RoleMapper {

    @Mappings({@Mapping(target = "role", source = "role")})
    public abstract RoleResponse mapToRoleResponse(Role role);

    public abstract List<RoleResponse> map(List<Role> roles);
}
