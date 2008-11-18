package com.methodknowledgy.events.bean;

import com.methodknowledgy.events.Event;


public interface ChangeListener<SourceType, PropertyType extends Object> {

    void onChange(Event<SourceType, PropertyType> change);
    
    Class<PropertyType> clazz();
    
}
