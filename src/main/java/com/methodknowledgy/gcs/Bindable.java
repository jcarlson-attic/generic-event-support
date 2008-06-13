package com.methodknowledgy.gcs;

public interface Bindable<Source> {
	
	void addChangeListener(String propertyName, GenericChangeListener<Source, ?> listener);
	
	void addChangeListener(GenericChangeListener<Source, Object> listener);
	
	void removeChangeListener(String propertyName, GenericChangeListener<Source, ?> listener);
	
	void removeChangeListener(GenericChangeListener<Source, Object> listener);
}
