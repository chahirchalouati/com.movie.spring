package com.movies.dtos.Responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movies.domain.Comment;
import com.movies.domain.Like;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Getter(AccessLevel.NONE)
    private Set<UserResponse> actors = new HashSet<>();
    private Set<Like> likes = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();
    private Map<String, Object> actions = new HashMap<>();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime createdAt;

    public Set<UserResponse> getActors() {
        return actors.stream().peek(userResponse -> userResponse.setRoles(null)).collect(Collectors.toSet());
    }
}
