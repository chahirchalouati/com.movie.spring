package com.movies.repositories;

import com.movies.domain.Banner;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Chahir Chalouati
 */
public interface BannerRepository extends MongoRepository<Banner, String> {
}
