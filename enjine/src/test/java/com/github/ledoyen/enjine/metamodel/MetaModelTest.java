package com.github.ledoyen.enjine.metamodel;

import org.junit.Test;

import com.github.ledoyen.enjine.MyClass;

public class MetaModelTest {

    @Test
    public void type_resolution_for_non_parameterized_parameter() {
        MetaModel<MyClass> model = MetaModel.from(MyClass.class);

//        if(model.isValid(noStaticMethods().and(onlyPrivateFields()))) {
//            ObjectFactory<Map<String, Object>, MyClass> factory = model.factoryBuilder()
//                    .valueFinder(fields().and(methodParameters()))
//                    .valueConverter(springConverter())
//                    .valueReader((input, valueData) -> Value.present(input.get(valueData.name())));
//            try {
//                MyClass instance = factory.create(new HashMap<>());
//            } catch(InvalidInputException e) {
//
//            }
//        }
    }
}
