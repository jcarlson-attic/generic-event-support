package com.methodknowledgy.gcs;

public interface GenericChangeListener<SourceType, PropertyType extends Object> {

    void onChange(GenericChangeEvent<SourceType, PropertyType> change);
    
    Class<PropertyType> clazz();
    
}
