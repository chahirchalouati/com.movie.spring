package com.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public class Banner {
    @Id
    private String id;
    @Indexed(name = "banner_name")
    @Field(name = "title")
    @NotBlank(message = "title can't be blank")
    private String title;

    @Field(name = "downloadUrl")
    @NotBlank(message = "title can't be blank")
    private String downloadUrl;

    @Field(name = "movie_ref")
    @NotBlank(message = "movie reference can't be blank")
    private String movieRef;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @CreatedDate
    private LocalDateTime createdAt;

    public Banner setId(String id) {
        this.id = id;
        return this;
    }

    public Banner setTitle(String title) {
        this.title = title;
        return this;
    }

    public Banner setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public Banner setMovieRef(String movieRef) {
        this.movieRef = movieRef;
        return this;
    }

    public Banner setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
