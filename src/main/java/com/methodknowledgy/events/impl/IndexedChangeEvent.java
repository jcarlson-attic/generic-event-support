package com.methodknowledgy.events.impl;

public class IndexedChangeEvent<SourceType, PropertyType> extends ChangeEvent<SourceType, PropertyType> {

    private int index;
    
    public IndexedChangeEvent(SourceType source, String propertyName,
            PropertyType oldValue, PropertyType newValue, int index) {
        this(source, propertyName, oldValue, newValue, null, index);
    }
    
    public IndexedChangeEvent(SourceType source, String propertyName,
            PropertyType oldValue, PropertyType newValue, String message, int index) {
        super(source, propertyName, oldValue, newValue, message);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
