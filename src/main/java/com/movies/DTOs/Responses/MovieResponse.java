package com.movies.DTOs.Responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movies.domain.Comment;
import com.movies.domain.Like;
import com.movies.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private String id;
    private String title;
    private String description;
    private String code;
    private String thumbnails;
    private String downloadUrl;
    private Set<Person> actors = new HashSet<>();
    private Set<Like> likes = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createdAt;
}
