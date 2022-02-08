package com.movies.DTOs.Requests;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateMovieRequest {
    @Getter(AccessLevel.NONE)
    private String title;
    @NotBlank(message = "description can't be blank")
    private String description;
    @NotNull(message = "file can't be null")
    private MultipartFile file;
    @NotNull(message = "thumbnails can't be null")
    private MultipartFile thumbnails;

    public String getTitle() {
        return title.isBlank() ? file.getName() : title;
    }
}
