package com.github.ledoyen.enjine.discovery;

import java.util.function.Function;

import com.github.ledoyen.enjine.tool.Strings;

public abstract class Parsers {
    private Parsers() {}

    public static Function<String, String> removeComment(String commentSequence) {
        return line -> Strings.removeComment(line, "#");
    }
}
