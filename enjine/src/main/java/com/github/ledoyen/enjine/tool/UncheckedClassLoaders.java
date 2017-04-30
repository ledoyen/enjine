package com.github.ledoyen.enjine.tool;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class UncheckedClassLoaders {

    private UncheckedClassLoaders() {}

    public static Set<URL> getResources(ClassLoader cl, String name) {
        try {
            return new HashSet<URL>(Collections.list(cl.getResources(name)));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
