package com.github.ledoyen.enjine.metamodel.value;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

class ParameterValue implements Value {

    private final Parameter _parameter;

    ParameterValue(Parameter parameter) {
        _parameter = parameter;
    }

    @Override
    public String name() {
        return _parameter.getName();
    }

    @Override
    public Type type() {
        return _parameter.getParameterizedType();
    }

    @Override
    public void apply(Object instance, Object value) {

    }
}
