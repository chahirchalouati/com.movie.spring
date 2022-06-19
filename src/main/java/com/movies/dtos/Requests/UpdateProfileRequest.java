package com.movies.dtos.Requests;

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
public class UpdateProfileRequest {
    @NotBlank(message = "profileId can't be blank")
    private String id;
    @OnlyImage
    private MultipartFile avatar;
}
