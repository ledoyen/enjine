package com.github.ledoyen.enjine.metamodel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.github.ledoyen.enjine.metamodel.validation.Validator;

/**
 * Represent the structure of a Java class.
 *
 * @param <T> type wrapped by the current {@link MetaModel}
 */
public class MetaModel<T> {

    private final Class<T> _class;
    private final Set<Constructor<T>> _constructors;
    private final Set<Method> _methods;
    private final Set<Field> _fields;

    private MetaModel(Class<T> clazz) {
        _class = clazz;
        _constructors = new HashSet<>(Arrays.asList((Constructor<T>[]) _class.getDeclaredConstructors()));
        // TODO improve this to get methods from parent Objects
        _methods = new HashSet<>(Arrays.asList(_class.getDeclaredMethods()));
        // TODO improve this to get fields from parent Objects
        _fields = new HashSet<>(Arrays.asList(_class.getDeclaredFields()));
    }

    public static <T> MetaModel<T> from(Class<T> clazz) {
        return new MetaModel<>(clazz);
    }

    public Class<T> getJavaClass() {
        return _class;
    }

    public boolean isValid(Validator validator) {
        return validator.validate(this).size() == 0;
    }

    public Stream<Method> methods(Predicate<Method> filter) {
        return _methods.stream().filter(filter);
    }

    public Stream<Field> fields(Predicate<Field> filter) {
        return _fields.stream().filter(filter);
    }

    public Stream<Constructor<T>> constructors(Predicate<Constructor<T>> filter) {
        return _constructors.stream().filter(filter);
    }

    public ObjectFactoryBuilder<T> factoryBuilder() {
        return new ObjectFactoryBuilder<>(this);
    }
}
