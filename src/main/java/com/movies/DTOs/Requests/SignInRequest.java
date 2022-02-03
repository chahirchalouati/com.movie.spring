package com.movies.DTOs.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    private String username;
    private String password;
}
