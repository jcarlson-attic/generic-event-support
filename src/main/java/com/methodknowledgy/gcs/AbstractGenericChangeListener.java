package com.methodknowledgy.gcs;

public abstract class AbstractGenericChangeListener<S, P> implements GenericChangeListener<S, P> {

    private P property;
    
    public Class<?> clazz() {
        return property.getClass();
    }

}
