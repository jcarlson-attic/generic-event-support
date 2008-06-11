package com.methodknowledgy.gcs;

public interface GenericChangeListener<SourceType, PropertyType> {

    void onChange(GenericChangeEvent<SourceType, PropertyType> change);
    
}
