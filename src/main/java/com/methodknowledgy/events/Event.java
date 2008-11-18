package com.methodknowledgy.events;

public interface Event<SourceType, PropertyType> {
	
	SourceType getSource();
	
	String getPropertyName();
	
	String getMessage();
	
	PropertyType getOldValue();
	
    PropertyType getNewValue();
    
}
