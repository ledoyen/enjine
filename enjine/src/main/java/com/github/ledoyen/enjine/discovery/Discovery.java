package com.github.ledoyen.enjine.discovery;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.ledoyen.enjine.tool.Classes;

public class Discovery {

    private static final String PREFIX = "META-INF/services/";

    private Source source;

    private Discovery(Source source) {
        this.source = source;
    }

    public static Discovery withSource(Source source) {
        return new Discovery(source);
    }

    public <T> Set<T> discover(Function<String, T> parser) {
        return source.get().stream().map(parser::apply).collect(Collectors.toSet());
    }

    public static <T> Set<Class<T>> fromJavaService(Class<T> serviceClass) {
        return Discovery
                .withSource(Source.fromClasspath(PREFIX + serviceClass.getName()))
                .discover(Parsers.removeComment("#").andThen(Classes::forName));
    }
}
