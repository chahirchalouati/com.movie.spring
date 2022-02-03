package com.movies.DTOs.Responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movies.domain.Comment;
import com.movies.domain.Like;
import com.movies.domain.Person;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class MovieResponse {
    private String id;
    private String title;
    private String description;
    private String thumbnails;
    private String downloadUri;
    private Set<Person> actors;
    private Set<Like> likes;
    private Set<Comment> comments;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createdAt;
}
