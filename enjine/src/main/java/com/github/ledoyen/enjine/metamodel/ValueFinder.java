package com.github.ledoyen.enjine.metamodel;

import java.util.HashSet;
import java.util.Set;

import com.github.ledoyen.enjine.metamodel.value.Value;

@FunctionalInterface
public interface ValueFinder<T> {

    Set<Value> find(MetaModel<T> model);

    default ValueFinder<T> and(ValueFinder<T> other) {
        return model -> {
            Set<Value> values = new HashSet<>();
            values.addAll(find(model));
            values.addAll(other.find(model));
            return values;
        };
    }
}
