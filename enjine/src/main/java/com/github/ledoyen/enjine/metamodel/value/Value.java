package com.github.ledoyen.enjine.metamodel.value;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

public interface Value {
    String name();

    Type type();

    void apply(Object instance, Object value);

    static Value from(Field f) {
        return new FieldValue(f);
    }

    static Value from(Parameter p) {
        return new ParameterValue(p);
    }

}
