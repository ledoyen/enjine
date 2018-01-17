package com.github.ledoyen.enjine.metamodel;

public abstract class ValueConverters {
    private ValueConverters() {
    }

    public static ValueConverter cast() {
        return (input, value) -> input;
    }
}
