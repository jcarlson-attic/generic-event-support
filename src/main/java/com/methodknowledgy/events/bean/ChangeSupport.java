package com.methodknowledgy.events.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.methodknowledgy.events.Event;
import com.methodknowledgy.events.impl.ChangeEvent;


public class ChangeSupport<Source> implements Bindable<Source> {

	private Source source;

	private Map<Class<?>, Map<String, List<ChangeListener<Source, ? extends Object>>>> map = new HashMap<Class<?>, Map<String, List<ChangeListener<Source, ? extends Object>>>>();

	public ChangeSupport(Source source) {
		this.source = source;
	}

	public <T> void firePropertyChange(String propertyName, T oldValue,
			T newValue, String message) {
		ChangeEvent<Source, T> change = new ChangeEvent<Source, T>(
				source, propertyName, oldValue, newValue, message);
		firePropertyChange(change);
	}

	public <T> void firePropertyChange(String propertyName, T oldValue,
			T newValue) {
		ChangeEvent<Source, T> change = new ChangeEvent<Source, T>(
				source, propertyName, oldValue, newValue);
		firePropertyChange(change);
	}

	@SuppressWarnings("unchecked")
	protected <T> void firePropertyChange(Event<Source, T> change) {

		if (change.getOldValue() != change.getNewValue()) {

			Class<? extends Object> key = (change.getOldValue() != null) ? change.getOldValue()
					.getClass() : change.getNewValue().getClass();

			// Notify field-level listeners first
			if (map.containsKey(key)) {
				if (map.get(key).containsKey(change.getPropertyName())) {
					List<ChangeListener<Source, ? extends Object>> listeners = map
							.get(key).get(change.getPropertyName());
					for (ChangeListener<Source, ?> listener : listeners) {
						((ChangeListener<Source, T>) listener)
								.onChange(change);
					}
				}
			}

			// Then notify general listeners
			if (map.containsKey(Object.class)) {
				if (map.get(Object.class).containsKey(null)) {
					List<ChangeListener<Source, ? extends Object>> listeners = map
							.get(Object.class).get(null);
					for (ChangeListener<Source, ?> listener : listeners) {
						((ChangeListener<Source, T>) listener)
								.onChange(change);
					}
				}
			}
		}

	}

	public void addChangeListener(ChangeListener<Source, Object> listener) {
		addChangeListener(null, listener);
	}

	public void addChangeListener(String propertyName,
			ChangeListener<Source, ?> listener) {
		if (!map.containsKey(listener.clazz())) {
			Map<String, List<ChangeListener<Source, ?>>> m = new HashMap<String, List<ChangeListener<Source, ?>>>();
			map.put(listener.clazz(), m);
		}
		if (!map.get(listener.clazz()).containsKey(propertyName)) {
			List<ChangeListener<Source, ?>> l = new ArrayList<ChangeListener<Source, ?>>();
			map.get(listener.clazz()).put(propertyName, l);
		}
		if (!map.get(listener.clazz()).get(propertyName).contains(listener)) {
			map.get(listener.clazz()).get(propertyName).add(listener);
		}
	}

	public void removeChangeListener(
			ChangeListener<Source, Object> listener) {
		removeChangeListener(null, listener);
	}

	public void removeChangeListener(String propertyName,
			ChangeListener<Source, ?> listener) {
		if (map.containsKey(listener.clazz())) {
			if (map.get(listener.clazz()).containsKey(propertyName)) {
				map.get(listener.clazz()).get(propertyName).remove(listener);
			}
		}
	}

}
