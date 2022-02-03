package com.movies.services;

import com.movies.DTOs.Requests.CreateMovieRequest;
import com.movies.DTOs.Requests.UpdateMovieRequest;
import com.movies.DTOs.Responses.MovieResponse;
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
