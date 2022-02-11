package com.movies.services;

import com.movies.DTOs.Requests.CreateGenreRequest;
import com.movies.DTOs.Requests.UpdateGenreRequest;
import com.movies.DTOs.Responses.GenreResponse;

import java.util.List;

/**
 * @author Chahir Chalouati
 */
public interface GenreService {
    List<GenreResponse> getGenres(int limit);

    GenreResponse save(CreateGenreRequest request);

    void delete(String id);

    GenreResponse findOne(String id);

    GenreResponse update(UpdateGenreRequest request);

    void deleteById(String id);
}
