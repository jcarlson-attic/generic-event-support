package com.methodknowledgy.gcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericChangeSupport<Source> implements Bindable<Source> {

	private Source source;

	private Map<Class<?>, Map<String, List<GenericChangeListener<Source, ? extends Object>>>> map = new HashMap<Class<?>, Map<String, List<GenericChangeListener<Source, ? extends Object>>>>();

	public GenericChangeSupport(Source source) {
		this.source = source;
	}

	public <T> void firePropertyChange(String propertyName, T oldValue,
			T newValue, String message) {
		GenericChangeEvent<Source, T> change = new GenericChangeEvent<Source, T>(
				source, propertyName, oldValue, newValue, message);
		firePropertyChange(change);
	}

	public <T> void firePropertyChange(String propertyName, T oldValue,
			T newValue) {
		GenericChangeEvent<Source, T> change = new GenericChangeEvent<Source, T>(
				source, propertyName, oldValue, newValue);
		firePropertyChange(change);
	}

	@SuppressWarnings("unchecked")
	protected <T> void firePropertyChange(GenericChangeEvent<Source, T> change) {

		if (change.getOldValue() != change.getNewValue()) {

			Class<? extends Object> key = (change.getOldValue() != null) ? change.getOldValue()
					.getClass() : change.getNewValue().getClass();

			// Notify field-level listeners first
			if (map.containsKey(key)) {
				if (map.get(key).containsKey(change.getPropertyName())) {
					List<GenericChangeListener<Source, ? extends Object>> listeners = map
							.get(key).get(change.getPropertyName());
					for (GenericChangeListener<Source, ?> listener : listeners) {
						((GenericChangeListener<Source, T>) listener)
								.onChange(change);
					}
				}
			}

			// Then notify general listeners
			if (map.containsKey(Object.class)) {
				if (map.get(Object.class).containsKey(null)) {
					List<GenericChangeListener<Source, ? extends Object>> listeners = map
							.get(Object.class).get(null);
					for (GenericChangeListener<Source, ?> listener : listeners) {
						((GenericChangeListener<Source, T>) listener)
								.onChange(change);
					}
				}
			}
		}

	}

	public void addChangeListener(GenericChangeListener<Source, Object> listener) {
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
		if (!map.get(listener.clazz()).get(propertyName).contains(listener)) {
			map.get(listener.clazz()).get(propertyName).add(listener);
		}
	}

	public void removeChangeListener(
			GenericChangeListener<Source, Object> listener) {
		removeChangeListener(null, listener);
	}

	public void removeChangeListener(String propertyName,
			GenericChangeListener<Source, ?> listener) {
		if (map.containsKey(listener.clazz())) {
			if (map.get(listener.clazz()).containsKey(propertyName)) {
				map.get(listener.clazz()).get(propertyName).remove(listener);
			}
		}
	}

}
