package com.github.ledoyen.enjine.metamodel;

import com.github.ledoyen.enjine.MyClass;
import com.github.ledoyen.enjine.metamodel.value.Value;
import com.github.ledoyen.enjine.metamodel.value.ValuePointer;
import org.junit.Test;

import java.util.Map;

import static com.github.ledoyen.enjine.metamodel.ValueFinders.fields;
import static com.github.ledoyen.enjine.metamodel.ValueFinders.methodParameters;
import static com.github.ledoyen.enjine.metamodel.validation.Validators.noStaticMethods;
import static com.github.ledoyen.enjine.metamodel.validation.Validators.onlyPrivateFields;
import static com.github.ledoyen.enjine.metamodel.ValueConverters.cast;

public class MetaModelTest {

    @Test
    public void type_resolution_for_non_parameterized_parameter() {
        MetaModel<MyClass> model = MetaModel.from(MyClass.class);

        if (model.isValid(noStaticMethods().and(onlyPrivateFields()))) {
            ObjectFactory<Map<String, Object>, MyClass> factory = model.factoryBuilder()
                    .valueFinder(fields().and(methodParameters()))
                    .valueConverter(cast())
                    .valueReader((input, valueData) -> Value.present(input.get(valueData.name())));
            try {
                MyClass instance = factory.create(new HashMap<>());
            } catch (InvalidInputException e) {

            }
        }
    }
}
