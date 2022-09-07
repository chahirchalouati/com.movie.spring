package com.movies.actions.movie;

import com.movies.domain.Movie;
import com.movies.actions.ActionHelper;
import com.movies.actions.BaseAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieActionHelperImpl implements ActionHelper<Movie> {
    private final Set<BaseAction> actions;

    @Override
    public Map<String, Object> getAllowedActions(Movie movie) {
        return actions.stream().peek(baseAction -> baseAction.evaluate(movie))
                .collect(Collectors.toMap(BaseAction::getName, BaseAction::getResult));
    }
}
