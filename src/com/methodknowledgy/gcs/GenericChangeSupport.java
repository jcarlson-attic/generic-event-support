package com.methodknowledgy.gcs;

import java.util.ArrayList;
import java.util.List;

public class GenericChangeSupport<Source> {

    private Source source;

    private List<GenericChangeListener<Source, ? extends Object>> listeners = new ArrayList<GenericChangeListener<Source, ? extends Object>>();

    @SuppressWarnings("unchecked")
    public <T> void firePropertyChange(String propertyName, T oldValue,
            T newValue) {

        GenericChangeEvent<Source, T> event = new GenericChangeEvent<Source, T>(
                source, propertyName, oldValue, newValue);

        for (GenericChangeListener<Source, ? extends Object> listener : listeners) {
            ((GenericChangeListener<Source, T>) listener).onChange(event);
        }

    }

    public void addChangeListener(
            GenericChangeListener<Source, ? extends Object> listener) {
        listeners.add(listener);
    }

}
