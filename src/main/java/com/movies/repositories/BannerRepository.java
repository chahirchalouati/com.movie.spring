package com.movies.repositories;

import com.movies.domain.Banner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Chahir Chalouati
 */
public interface BannerRepository extends MongoRepository<Banner, String> {
}
