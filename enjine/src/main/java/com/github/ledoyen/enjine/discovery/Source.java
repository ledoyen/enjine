package com.github.ledoyen.enjine.discovery;

import java.util.Set;
import java.util.function.Supplier;

public interface Source extends Supplier<Set<String>> {

    static Source fromClasspath(String name) {
        return new ClasspathSource(Thread.currentThread().getContextClassLoader(), name);
    }

    static Source fromClasspath(ClassLoader loader, String name) {
        return new ClasspathSource(loader, name);
    }
}
