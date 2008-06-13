package com.methodknowledgy.gcs;

import java.util.ArrayList;
import java.util.List;

public class GenericChangeSupport<Source> {

    private Source source;

    private List<GenericChangeListener<Source, ?>> listeners = new ArrayList<GenericChangeListener<Source, ?>>();

    @SuppressWarnings("unchecked")
    public <T> void firePropertyChange(String propertyName, T oldValue,
            T newValue) {

        GenericChangeEvent<Source, T> event = new GenericChangeEvent<Source, T>(
                source, propertyName, oldValue, newValue);

        for (GenericChangeListener<Source, ?> listener : listeners) {
            ((GenericChangeListener<Source, T>) listener).onChange(event);
        }

    }

    public void addChangeListener(
            GenericChangeListener<Source, ?> listener) {
        listeners.add(listener);
    }

}
