package com.github.ledoyen.enjine.metamodel.value;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

class FieldValue implements Value {

    private final Field _field;

    FieldValue(Field field) {
        _field = field;
    }

    @Override
    public String name() {
        return _field.getName();
    }

    @Override
    public Type type() {
        return _field.getGenericType();
    }

    @Override
    public void apply(Object instance, Object value) {
        if (!_field.isAccessible()) {
            _field.setAccessible(true);
        }
        try {
            _field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Unable to set value " + value + " in field " + _field, e);
        }
    }
}
