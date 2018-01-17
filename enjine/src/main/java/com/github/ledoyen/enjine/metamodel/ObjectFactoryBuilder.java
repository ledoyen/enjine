package com.github.ledoyen.enjine.metamodel;

public class ObjectFactoryBuilder<RESULTING_TYPE> {

    private final MetaModel<RESULTING_TYPE> _metaModel;

    private ValueFinder _valueFinder;
    private ValueConverter _valueConverter;

    ObjectFactoryBuilder(MetaModel<RESULTING_TYPE> metaModel) {
        _metaModel = metaModel;
    }

    public ObjectFactoryBuilder<RESULTING_TYPE> valueFinder(ValueFinder finder) {
        this._valueFinder = finder;
        return this;
    }

    public ObjectFactoryBuilder<RESULTING_TYPE> valueConverter(ValueConverter valueConverter) {
        this._valueConverter = valueConverter;
        return this;
    }
}
