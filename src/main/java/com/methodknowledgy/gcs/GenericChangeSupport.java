package com.methodknowledgy.gcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenericChangeSupport<Source> {

    private static Log log = LogFactory.getLog(GenericChangeSupport.class);

    private Source source;

    private Map<Class<?>, Map<String, List<GenericChangeListener<Source, ?>>>> map = new HashMap<Class<?>, Map<String, List<GenericChangeListener<Source, ?>>>>();

    public GenericChangeSupport(Source source) {
        this.source = source;
    }

    public <T> void firePropertyChange(String propertyName, T oldValue,
            T newValue) {

        if (oldValue != newValue) {
            // Both new and old are the same

            // Construct a change object
            GenericChangeEvent<Source, T> change = new GenericChangeEvent<Source, T>(
                    source, propertyName, oldValue, newValue);

            Class<? extends Object> key = (oldValue != null) ? oldValue
                    .getClass() : newValue.getClass();

            // Notify field-level listeners first
            if (map.containsKey(key)) {
                if (map.get(key).containsKey(propertyName)) {
                    // TODO: notify Class/property listeners
                    log
                            .info("Field-level listeners are being notified with change: "
                                    + change.toString());
                }
            }

            // Then notify general listeners
            if (map.containsKey(Object.class)) {
                if (map.get(Object.class).containsKey(null)) {
                    // TODO: notify Object/null listeners
                    log
                            .info("Object-level listeners are being notified with change: "
                                    + change.toString());
                }
            }
        }

    }

    public void addChangeListener(GenericChangeListener<Source, Object> listener) {
        // General purpose listeners will be stored in the map with a two-part
        // key of Object-null
        addChangeListener(null, listener);
    }

    public <T> void addChangeListener(String propertyName,
            GenericChangeListener<Source, T> listener) {
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
