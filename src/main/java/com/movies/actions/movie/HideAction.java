package com.movies.actions.movie;

import com.movies.actions.BaseAction;
import com.movies.domain.Movie;
import org.springframework.stereotype.Component;

@Component
public class HideAction extends BaseAction<Movie> {
    // inject anything u want
    @Override
    public String getName() {
        return "HIDE";
    }

    @Override
    public void evaluate(Movie movie) {
        this.setResult(movie.isExclusive());
    }
}
