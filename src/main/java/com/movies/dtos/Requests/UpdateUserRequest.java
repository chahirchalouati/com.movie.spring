package com.movies.dtos.Requests;

import com.movies.domain.Role;
import com.movies.validations.annotations.NotEmptyList;
import com.movies.validations.annotations.PasswordValidation;
import com.movies.validations.annotations.UniqueUserName;
import com.movies.validations.messages.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequest {
    @NotBlank(message = "firstName can't be blank")
    public String firstName;
    @NotBlank(message = "lastName can't be blank")
    public String lastName;
    private String id;
    @UniqueUserName(isUpdate = true)
    @NotBlank(message = "userName can't be blank")
    private String userName;
    @PasswordValidation(message = MessageUtils.PASSWORD_ERROR)
    private String password;
    @NotEmptyList
    private Set<Role> roles = new HashSet<>();
}
