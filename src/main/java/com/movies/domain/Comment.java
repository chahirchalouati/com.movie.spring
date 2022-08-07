package com.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comment {

    @NotBlank(message = "comment can't be blank")
    @Max(value = 255, message = "comment too long, please make sure your comment length is less than 255 char")
    @Min(value = 10, message = "comment too short, please make sure your comment length is greater than 10 char")
    private String comment;
    @NotNull
    private User commentCreator;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createdAt;

}
