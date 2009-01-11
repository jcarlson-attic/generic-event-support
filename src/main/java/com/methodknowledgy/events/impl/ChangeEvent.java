package com.methodknowledgy.events.impl;

import com.methodknowledgy.events.Event;

public class ChangeEvent<SourceType, PropertyType> implements Event<SourceType, PropertyType> {

    // TODO Combine ChangeEvent with ChangeSupport to take advantage of redundant code
    // TODO ChangeSupport cancels the change if the old and new values are the same. This should also.
    // TODO Create a NotifyEvent that can be used for occurrence notifications
    
	private SourceType source;
	private String propertyName;
	private PropertyType oldValue;
	private PropertyType newValue;
	private String message;

	public ChangeEvent(SourceType source, String propertyName,
			PropertyType oldValue, PropertyType newValue) {
		this(source, propertyName, oldValue, newValue, null);
	}

	public ChangeEvent(SourceType source, String propertyName,
			PropertyType oldValue, PropertyType newValue, String message) {
		this.source = source;
		this.propertyName = propertyName;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.message = message;
	}

	public SourceType getSource() {
		return source;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public PropertyType getOldValue() {
		return oldValue;
	}

	public PropertyType getNewValue() {
		return newValue;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
	    if (message != null && message != "") {
	        return message;
	    }
        StringBuffer sb = new StringBuffer();
        sb.append("Source: " + getSource() + "; ");
        sb.append("Property: " + getPropertyName() + "; ");
        sb.append("Old Value: " + getOldValue() + "; ");
        sb.append("New Value: " + getNewValue() + "; ");
        return sb.toString();
	}

}
