package com.github.ledoyen.enjine.metamodel.value;

public class Value {
    private static final Value MISSING = new Value(null) {
        public boolean isPresent() {
            return false;
        }
    };
    private final Object value;

    public Value(Object value) {
        this.value = value;
    }

    public static Value present(Object value) {
        return new Value(value);
    }

    public static Value missing() {
        return MISSING;
    }

    public boolean isPresent() {
        return true;
    }
}
