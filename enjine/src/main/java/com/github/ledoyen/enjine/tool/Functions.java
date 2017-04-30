package com.github.ledoyen.enjine.tool;

import java.util.function.Function;
import java.util.function.Predicate;

public class Functions {

    public static <T> Function<T, T> conditional(Predicate<T> condition, Function<T, T> function) {
        return item -> condition.test(item) ? function.apply(item) : item;
    }
}
