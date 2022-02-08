package com.movies.DTOs.Requests;

import com.movies.validations.annotations.OnlyImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProfileRequest {
    @NotBlank(message = "userid can't be blank")
    private String userId;
    @OnlyImage(message = "avatar should be an image")
    private MultipartFile avatar;
}
