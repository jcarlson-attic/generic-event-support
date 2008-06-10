package com.methodknowledgy.gcs;

import java.util.ArrayList;
import java.util.List;

public class PropertyChangeSupport<Source> {

    private Source source;

    private List<PropertyChangeListener<Source, ? extends Object>> listeners = new ArrayList<PropertyChangeListener<Source, ? extends Object>>();

    @SuppressWarnings("unchecked")
    public <T> void firePropertyChange(String propertyName, T oldValue,
            T newValue) {

        PropertyChangeEvent<Source, T> event = new PropertyChangeEvent<Source, T>(
                source, propertyName, oldValue, newValue);

        for (PropertyChangeListener<Source, ? extends Object> listener : listeners) {
            ((PropertyChangeListener<Source, T>) listener).onChange(event);
        }

    }

    public void addChangeListener(
            PropertyChangeListener<Source, ? extends Object> listener) {
        listeners.add(listener);
    }

}
