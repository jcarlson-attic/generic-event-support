package com.methodknowledgy.events.impl;

public class IndexedSingleValueEvent<SourceType, PropertyType> extends SingleValueEvent<SourceType, PropertyType> {

    private int index;
    
    public IndexedSingleValueEvent(SourceType source, String propertyName,
            PropertyType value, int index) {
        this(source, propertyName, value, null, index);
    }
    
    public IndexedSingleValueEvent(SourceType source, String propertyName,
            PropertyType value, String message, int index) {
        super(source, propertyName, value, message);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
