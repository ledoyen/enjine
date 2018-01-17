package com.github.ledoyen.enjine.metamodel.value;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

public interface ValuePointer {
    String name();

    Type type();

    void apply(Object instance, Object value);

    static ValuePointer from(Field f) {
        return new FieldValuePointer(f);
    }

    static ValuePointer from(Parameter p) {
        return new ParameterValuePointer(p);
    }

}
