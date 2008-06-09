package com.methodknowledgy.pcs;

public interface PropertyChangeListener<SourceType, PropertyType> {

    void onChange(PropertyChangeEvent<SourceType, PropertyType> change);
    
}
