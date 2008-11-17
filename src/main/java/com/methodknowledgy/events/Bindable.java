package com.methodknowledgy.events;

public interface Bindable<Source> {
	
	void addChangeListener(String propertyName, ChangeListener<Source, ?> listener);
	
	void addChangeListener(ChangeListener<Source, Object> listener);
	
	void removeChangeListener(String propertyName, ChangeListener<Source, ?> listener);
	
	void removeChangeListener(ChangeListener<Source, Object> listener);
}
