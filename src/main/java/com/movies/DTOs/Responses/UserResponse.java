package com.movies.DTOs.Responses;

import com.movies.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private Set<Role> roles = new HashSet<>();
}
