package com.movies.actions;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseAction<T> {
    @Getter
    @Setter
    private Object result;

    public abstract String getName();

    public abstract void evaluate(T t);

}
