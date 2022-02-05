package com.movies.DTOs.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerCreateRequest {

    @NotBlank(message = "title can't be blank")
    private String title;

    @NotNull(message = "file can't be null")
    private MultipartFile file;

    @NotBlank(message = "description can't be blank")
    private String description;
}
