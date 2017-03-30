package com.github.ledoyen.enjine.metamodel;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.ledoyen.enjine.metamodel.ValueFinder;
import com.github.ledoyen.enjine.metamodel.value.Value;

public abstract class ValueFinders {
    private ValueFinders() {
    }

    public static ValueFinder<Object> fields() {
        return model -> model
                .fields(field -> !Modifier.isStatic(field.getModifiers()))
                .map(Value::from)
                .collect(Collectors.toSet());
    }

    public static ValueFinder<Object> methodParameters() {
        return model -> model
                .methods(method -> !Modifier.isStatic(method.getModifiers()))
                .flatMap(method -> Arrays.stream(method.getParameters()))
                .map(Value::from)
                .collect(Collectors.toSet());
    }
}
