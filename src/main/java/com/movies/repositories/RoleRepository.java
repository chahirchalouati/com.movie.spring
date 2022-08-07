package com.movies.repositories;

import com.movies.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Chahir Chalouati
 */
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRole(String role);

    boolean existsByRole(String role);

    Collection<Role> findByRoleContainingIgnoreCase(String role);
}
