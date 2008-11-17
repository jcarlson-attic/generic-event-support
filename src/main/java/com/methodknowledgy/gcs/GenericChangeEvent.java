package com.methodknowledgy.gcs;

public class GenericChangeEvent<SourceType, PropertyType> {

	private SourceType source;
	private String propertyName;
	private PropertyType oldValue;
	private PropertyType newValue;
	private String message;

	public GenericChangeEvent(SourceType source, String propertyName,
			PropertyType oldValue, PropertyType newValue) {
		this(source, propertyName, oldValue, newValue, null);
	}

	public GenericChangeEvent(SourceType source, String propertyName,
			PropertyType oldValue, PropertyType newValue, String message) {
		this.source = source;
		this.propertyName = propertyName;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.message = message == null ? "" : message;
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

}
