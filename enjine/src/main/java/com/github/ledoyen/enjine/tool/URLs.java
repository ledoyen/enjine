package com.github.ledoyen.enjine.tool;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class URLs {

    private URLs() {}

    public static URI toURI(URL url) {
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
