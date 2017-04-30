package com.github.ledoyen.enjine.tool;

public abstract class Classes {
    private Classes() {}

    public static <T> Class<T> forName(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
