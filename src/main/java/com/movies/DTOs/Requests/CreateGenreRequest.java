package com.movies.DTOs.Requests;

import com.movies.validations.annotations.UniqueGenre;
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
public class CreateGenreRequest {
    @UniqueGenre(message = "genre already exists")
    @NotBlank(message = "name can't be blank")
    private String name;
}
