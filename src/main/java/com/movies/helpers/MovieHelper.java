package com.movies.helpers;

import com.movies.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Chahir Chalouati
 */
@Component
@AllArgsConstructor
public class MovieHelper {

    private final MovieRepository movieRepository;

    public Integer createCode() {
        return Math.toIntExact(movieRepository.count()) + 1;
    }

}
