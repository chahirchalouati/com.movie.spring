package com.movies.repositories;

import com.movies.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Chahir Chalouati
 */
public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByTitle(String title);

    Set<Movie> findByActors_FullName(String fullName);

    Optional<Movie> findByCode(Integer code);
}
