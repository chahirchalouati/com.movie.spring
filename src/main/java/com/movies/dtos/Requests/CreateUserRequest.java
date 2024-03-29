package com.movies.dtos.Requests;

import com.movies.domain.Role;
import com.movies.validations.annotations.PasswordValidation;
import com.movies.validations.annotations.UniqueUserName;
import com.movies.validations.messages.MessageUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chahir Chalouati
 */
@Data
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "firstName can't be blank")
    public String firstName;
    @NotBlank(message = "lastName can't be blank")
    public String lastName;
    @UniqueUserName
    @NotBlank(message = "userName can't be blank")
    private String userName;
    @PasswordValidation(message = MessageUtils.PASSWORD_ERROR)
    private String password;
    private Set<Role> roles = new HashSet<>();
}
