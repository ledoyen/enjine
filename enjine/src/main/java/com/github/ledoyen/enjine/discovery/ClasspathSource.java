package com.github.ledoyen.enjine.discovery;

import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.ledoyen.enjine.tool.URLs;
import com.github.ledoyen.enjine.tool.UncheckedClassLoaders;
import com.github.ledoyen.enjine.tool.UncheckedFiles;

class ClasspathSource implements Source {
    private final ClassLoader loader;
    private final String name;

    ClasspathSource(ClassLoader loader, String name) {
        this.loader = loader;
        this.name = name;
    }

    public Set<String> get() {
        return UncheckedClassLoaders
                .getResources(loader, name)
                .stream()
                .map(URLs::toURI)
                .flatMap(uri -> UncheckedFiles.lines(Paths.get(uri)))
                .collect(Collectors.toSet());
    }
}
