package com.movies.DTOs.Requests;

import com.movies.validations.annotations.PasswordValidation;
import com.movies.validations.messages.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @NotBlank(message = "username can't be blank")
    private String username;
    @PasswordValidation(message = MessageUtils.PASSWORD_ERROR)
    private String password;
}
