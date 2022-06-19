package com.movies.dtos.Requests;

import com.movies.domain.Comment;
import com.movies.domain.Like;
import com.movies.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Chahir Chalouati
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieRequest {
    @NotBlank(message = "id can't be blank")
    private String id;
    @NotBlank(message = "title can't be blank")
    private String title;
    @NotBlank(message = "code can't be blank")
    private String code;
    @NotBlank(message = "description can't be blank")
    private String description;
    @NotNull(message = "file can't be null")
    private MultipartFile file;
    @NotNull(message = "thumbnails can't be null")
    private MultipartFile thumbnails;
    private Set<Person> actors;
    private Set<Like> likes;
    private Set<Comment> comments;

}
