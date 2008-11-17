package com.methodknowledgy.events;


public interface ChangeListener<SourceType, PropertyType extends Object> {

    void onChange(ChangeEvent<SourceType, PropertyType> change);
    
    Class<PropertyType> clazz();
    
}
