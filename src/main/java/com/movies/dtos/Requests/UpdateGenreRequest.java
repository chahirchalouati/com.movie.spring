package com.movies.dtos.Requests;

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
public class UpdateGenreRequest {
    @NotBlank(message = "id can't be blank")
    private String id;
    @NotBlank(message = "name can't be blank")
    private String name;
}
