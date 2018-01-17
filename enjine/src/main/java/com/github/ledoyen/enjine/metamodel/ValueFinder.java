package com.github.ledoyen.enjine.metamodel;

import java.util.HashSet;
import java.util.Set;

import com.github.ledoyen.enjine.metamodel.value.ValuePointer;

@FunctionalInterface
public interface ValueFinder {

    Set<ValuePointer> find(MetaModel<?> model);

    default ValueFinder and(ValueFinder other) {
        return model -> {
            Set<ValuePointer> valuePointers = new HashSet<>();
            valuePointers.addAll(find(model));
            valuePointers.addAll(other.find(model));
            return valuePointers;
        };
    }
}
