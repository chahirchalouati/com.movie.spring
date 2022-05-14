package com.movies.mappers;

import com.movies.DTOs.Responses.RoleResponse;
import com.movies.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
