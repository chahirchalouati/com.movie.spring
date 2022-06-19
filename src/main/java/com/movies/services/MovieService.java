package com.movies.services;

import com.movies.dtos.Requests.CreateMovieRequest;
import com.movies.dtos.Requests.UpdateMovieRequest;
import com.movies.dtos.Responses.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Chahir Chalouati
 */
public interface MovieService {
    MovieResponse add(CreateMovieRequest createMovieRequest);

    MovieResponse getOne(String id);

    MovieResponse update(UpdateMovieRequest updateMovieRequest);

    boolean deleteById(String id);

    boolean deleteByTitle(String title);

    Page<MovieResponse> findAll(Pageable pageable);
}
