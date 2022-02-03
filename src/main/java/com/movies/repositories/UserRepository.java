package com.movies.repositories;

import com.movies.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
/**
 * @author Chahir Chalouati
 */
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByFirstNameAndLastName(String firstName, String lastname);

    Optional<User> findByUserName(String userName);
}
