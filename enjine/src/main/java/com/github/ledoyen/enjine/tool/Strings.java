package com.github.ledoyen.enjine.tool;

import java.util.Optional;

public abstract class Strings {
    private Strings() {}

    public static String removeComment(String line, String commentSequence) {
        return Optional
                .of(line.indexOf(commentSequence))
                .filter(index -> index > -1)
                .map(index -> line.substring(0, index))
                .orElse(line)
                .trim();
    }
}
