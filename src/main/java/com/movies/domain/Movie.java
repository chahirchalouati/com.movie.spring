package com.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = Movie.COLLECTION_NAME)
public class Movie {
    public static final String COLLECTION_NAME = "movies";
    @Id
    private String id;
    @NotBlank(message = "title can't be blank")
    @Indexed
    private String title;
    @Indexed
    @NotBlank(message = "code can't be blank")
    private Integer code;
    @NotBlank(message = "description can't be blank")
    private String description;
    private String thumbnails;
    @JsonIgnore
    private String path;
    private String downloadUrl;
    // TODO: 2/9/2022 add logic in movie mapper
    private String duration;
    private String genre;
    private LocalDateTime inCinema;
    private boolean isExclusive = false;


    private Set<Person> actors = new HashSet<>();
    private Set<Like> likes = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @CreatedDate
    private LocalDateTime createdAt;


}
