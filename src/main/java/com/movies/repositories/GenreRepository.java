package com.movies.repositories;

import com.movies.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Chahir Chalouati
 */
public interface GenreRepository extends MongoRepository<Genre, String> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByName(String name);
}
