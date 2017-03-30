# enJine
Java tools to build frameworks

All tools are built on top of the **Meta-Model** abstraction.

## Meta-Model
The **Meta-Model** abstraction allows to manipulate code structures such as Class, Constructors, Methods, Fields, without the complexity of the `java.lang.reflect` API.

```java
MetaModel<MyClass> model = MetaModel.from(MyClass.class);

if(model.isValid(noStaticMethods().and(onlyPrivateFields()))) {
    ObjectFactory<Map<String, Object>, MyClass> factory = model.factoryBuilder()
        .valueFinder(fields().and(methodParameters()))
        .valueConverter(springConverter())
        .valueReader((input, valueData) -> Value.present(input.get(valueData.name())));

    try {
        MyClass instance = factory.create(new HashMap<>());
    } catch(InvalidInputException e) {
        LOGGER.warn("Unable to build MyClass because" + e.getReason());
    }
}
```

### Validation
Validation can be tailored to fit custom needs by implementing the `Validator` interface:
```java
public class CustomValidator implements Validator {
    public Set<Violation> validate(MetaModel metamodel) {
        return metamodel
            .constructors(c -> Modifier.isPrivate(c.getModifiers())
            .map(m -> new LocatedViolation(m, "should not be private"))
            .collect(Collectors.toSet());
    }
}
```

Validators can be composed:
```java
Validator validator = noStaticMethods().and(new CustomValidator());
```

Validation errors can be retrieved by calling the `validate` method:
```java
Validator validator = noStaticMethods().and(new CustomValidator());
Set<Violation> violations = validator.validate(metamodel);
```
