package com.movies.mappers;

import com.movies.DTOs.Requests.CreateMovieRequest;
import com.movies.DTOs.Requests.UpdateMovieRequest;
import com.movies.DTOs.Responses.MovieResponse;
import com.movies.domain.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;
/**
 * @author Chahir Chalouati
 */
@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "thumbnails", target = "thumbnails"),
            @Mapping(source = "actors", target = "actors"),
            @Mapping(source = "comments", target = "comments"),
            @Mapping(source = "likes", target = "likes"),
    })
    public abstract MovieResponse mapToMovieResponse(Movie movie);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(ignore = true, target = "thumbnails"),
    })
    public abstract Movie mapToMovie(CreateMovieRequest createMovieRequest);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "description", target = "description"),
            @Mapping(ignore = true, target = "thumbnails"),
            @Mapping(source = "actors", target = "actors"),
            @Mapping(source = "comments", target = "comments"),
            @Mapping(source = "likes", target = "likes"),
    })
    public abstract Movie mapToMovie(UpdateMovieRequest updateMovieRequest);

    public abstract Set<MovieResponse> map(Set<Movie> movies);


}
