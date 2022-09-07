package com.movies.actions;

import java.util.Map;

public interface ActionHelper<T> {
    Map<String, Object> getAllowedActions(T payload);
}
