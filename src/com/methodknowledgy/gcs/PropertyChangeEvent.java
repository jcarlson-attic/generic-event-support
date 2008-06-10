package com.methodknowledgy.gcs;

public class PropertyChangeEvent<SourceType, PropertyType> {

    private SourceType source;
    private String propertyName;
    private PropertyType oldValue;
    private PropertyType newValue;

    public PropertyChangeEvent(SourceType source, String propertyName,
            PropertyType oldValue, PropertyType newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public SourceType getSource() {
        return source;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public PropertyType getOldValue() {
        return oldValue;
    }

    public PropertyType getNewValue() {
        return newValue;
    }

}
