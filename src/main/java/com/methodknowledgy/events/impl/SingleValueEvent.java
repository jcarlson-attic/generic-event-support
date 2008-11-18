package com.methodknowledgy.events.impl;

public class SingleValueEvent<SourceType, PropertyType> extends ChangeEvent<SourceType, PropertyType> {

	public SingleValueEvent(SourceType source, String propertyName,
			PropertyType value) {
		this(source, propertyName, value, null);
	}

	public SingleValueEvent(SourceType source, String propertyName,
			PropertyType value, String message) {
	    super(source, propertyName, value, value, message);
	}

	public PropertyType getValue() {
		return getNewValue();
	}
}
