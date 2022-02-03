package com.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Like {

    @NotNull
    private boolean isLiked;
    @NotNull
    private User likeCreator;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createdAt;
}
