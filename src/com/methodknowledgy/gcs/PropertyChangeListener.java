package com.methodknowledgy.gcs;

public interface PropertyChangeListener<SourceType, PropertyType> {

    void onChange(PropertyChangeEvent<SourceType, PropertyType> change);
    
}
