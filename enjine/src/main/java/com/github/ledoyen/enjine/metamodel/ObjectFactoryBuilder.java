package com.github.ledoyen.enjine.metamodel;

public class ObjectFactoryBuilder<RESULTING_TYPE> {

    private final MetaModel<RESULTING_TYPE> _metaModel;

    private ValueFinder<RESULTING_TYPE> _valueFinder;

    ObjectFactoryBuilder(MetaModel<RESULTING_TYPE> metaModel) {
        _metaModel = metaModel;
    }

    public ObjectFactoryBuilder<RESULTING_TYPE> valueFinder(ValueFinder<RESULTING_TYPE> finder) {
        this._valueFinder = finder;
        return this;
    }
}
