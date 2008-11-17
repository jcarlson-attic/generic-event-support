package com.methodknowledgy.gcs;

public class IndexedGenericChangeEvent<SourceType, PropertyType> extends GenericChangeEvent<SourceType, PropertyType> {

    private int index;
    
    public IndexedGenericChangeEvent(SourceType source, String propertyName,
            PropertyType oldValue, PropertyType newValue, int index) {
        this(source, propertyName, oldValue, newValue, null, index);
    }
    
    public IndexedGenericChangeEvent(SourceType source, String propertyName,
            PropertyType oldValue, PropertyType newValue, String message, int index) {
        super(source, propertyName, oldValue, newValue, message);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
