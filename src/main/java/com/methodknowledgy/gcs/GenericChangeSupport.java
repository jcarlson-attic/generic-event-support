package com.methodknowledgy.gcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericChangeSupport<Source> {

    private Source source;

    private Map<Class<?>, Map<String, List<GenericChangeListener<Source, ? extends Object>>>> map = new HashMap<Class<?>, Map<String, List<GenericChangeListener<Source, ? extends Object>>>>();

    public GenericChangeSupport(Source source) {
        this.source = source;
    }

    @SuppressWarnings("unchecked")
    public <T> void firePropertyChange(String propertyName, T oldValue,
            T newValue) {

        if (oldValue != newValue) {

            // Construct a change object
            GenericChangeEvent<Source, T> change = new GenericChangeEvent<Source, T>(
                    source, propertyName, oldValue, newValue);

            Class<? extends Object> key = (oldValue != null) ? oldValue
                    .getClass() : newValue.getClass();

            // Notify field-level listeners first
            if (map.containsKey(key)) {
                if (map.get(key).containsKey(propertyName)) {
                    List<GenericChangeListener<Source, ? extends Object>> listeners = map.get(
                            key).get(propertyName);
                    for (GenericChangeListener<Source, ?> listener : listeners) {
                        ((GenericChangeListener<Source, T>) listener).onChange(change);
                    }
                }
            }

            // Then notify general listeners
            if (map.containsKey(Object.class)) {
                if (map.get(Object.class).containsKey(null)) {
                    List<GenericChangeListener<Source, ? extends Object>> listeners = map
                            .get(Object.class).get(null);
                    for (GenericChangeListener<Source, ?> listener : listeners) {
                        ((GenericChangeListener<Source, T>) listener).onChange(change);
                    }
                }
            }
        }

    }

    public void addChangeListener(GenericChangeListener<Source, Object> listener) {
        // General purpose listeners will be stored in the map with a two-part
        // key of Object-null
        addChangeListener(null, listener);
    }

    public void addChangeListener(String propertyName,
            GenericChangeListener<Source, ?> listener) {
        if (!map.containsKey(listener.clazz())) {
            Map<String, List<GenericChangeListener<Source, ?>>> m = new HashMap<String, List<GenericChangeListener<Source, ?>>>();
            map.put(listener.clazz(), m);
        }
        if (!map.get(listener.clazz()).containsKey(propertyName)) {
            List<GenericChangeListener<Source, ?>> l = new ArrayList<GenericChangeListener<Source, ?>>();
            map.get(listener.clazz()).put(propertyName, l);
        }
        map.get(listener.clazz()).get(propertyName).add(listener);
    }

}
