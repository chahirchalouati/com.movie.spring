package com.movies.repositories;

import com.movies.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Chahir Chalouati
 */
public interface FileRepository extends MongoRepository<File, String> {

    Optional<File> findByName(String name);
}
