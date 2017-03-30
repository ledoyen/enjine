package com.github.ledoyen.enjine.metamodel.validation;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class Validators {
    private Validators() {}

    public static Validator noStaticMethods() {
        return model -> model
                .methods(method -> Modifier.isStatic(method.getModifiers()))
                .map(m -> new LocatedViolation(m, "should not be static"))
                .collect(Collectors.toSet());
    }

    public static Validator onlyPrivateFields() {
        return model -> model.fields(field -> !Modifier.isPrivate(field.getModifiers()))
                .map(m -> new LocatedViolation(m, "should be private"))
                .collect(Collectors.toSet());
    }

    public static Validator publicNoArgsConstructor() {
        return model -> model.constructors(
                constructor -> constructor.getParameters().length == 0
                        && Modifier.isPublic(constructor.getModifiers())
        ).count() == 1 ? Collections.emptySet() : Collections.singleton(new SimpleViolation("Missing no args public constructor"));
    }
}
