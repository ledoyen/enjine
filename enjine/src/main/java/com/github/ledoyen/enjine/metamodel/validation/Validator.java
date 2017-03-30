package com.github.ledoyen.enjine.metamodel.validation;

import java.util.HashSet;
import java.util.Set;

import com.github.ledoyen.enjine.metamodel.MetaModel;

@FunctionalInterface
public interface Validator {

    Set<Violation> validate(MetaModel<?> metamodel);

    default Validator and(Validator other) {
        return model -> {
            Set<Violation> violations = new HashSet<>();
            violations.addAll(validate(model));
            violations.addAll(other.validate(model));
            return violations;
        };
    }
}
