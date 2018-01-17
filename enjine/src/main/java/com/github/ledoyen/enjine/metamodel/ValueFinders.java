package com.github.ledoyen.enjine.metamodel;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.ledoyen.enjine.metamodel.value.ValuePointer;

public abstract class ValueFinders {
    private ValueFinders() {
    }

    public static ValueFinder fields() {
        return model -> model
                .fields(field -> !Modifier.isStatic(field.getModifiers()))
                .map(ValuePointer::from)
                .collect(Collectors.toSet());
    }

    public static ValueFinder methodParameters() {
        return model -> model
                .methods(method -> !Modifier.isStatic(method.getModifiers()))
                .flatMap(method -> Arrays.stream(method.getParameters()))
                .map(ValuePointer::from)
                .collect(Collectors.toSet());
    }
}
