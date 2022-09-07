package com.movies.actions.movie;

import com.movies.actions.BaseAction;
import com.movies.domain.Movie;
import org.springframework.stereotype.Component;

@Component
public class DeleteAction extends BaseAction<Movie> {
    // inject anything u want
    @Override
    public String getName() {
        return "DELETE";
    }

    @Override
    public void evaluate(Movie movie) {
        this.setResult(movie.isExclusive());
    }
}
