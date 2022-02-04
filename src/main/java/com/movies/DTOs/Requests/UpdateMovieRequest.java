package com.movies.DTOs.Requests;

import com.movies.domain.Comment;
import com.movies.domain.Like;
import com.movies.domain.Person;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;
/**
 * @author Chahir Chalouati
 */
@Data
@Builder
public class UpdateMovieRequest {
    private String id;
    @NotBlank(message = "title can't be blank")
    private String title;
    @NotBlank(message = "description can't be blank")
    private String description;
    private String thumbnails;
    private String downloadUrl;

    private Set<Person> actors;
    private Set<Like> likes;
    private Set<Comment> comments;

}
