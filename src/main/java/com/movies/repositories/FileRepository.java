package com.movies.repositories;

import com.movies.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Chahir Chalouati
 */
public interface FileRepository extends MongoRepository<File, String> {

    Optional<File> findByName(String name);
}
