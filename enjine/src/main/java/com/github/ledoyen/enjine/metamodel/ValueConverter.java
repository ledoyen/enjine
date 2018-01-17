package com.github.ledoyen.enjine.metamodel;

import com.github.ledoyen.enjine.metamodel.value.ValuePointer;

public interface ValueConverter {

    Object convert(Object input, ValuePointer valuePointer);
}
