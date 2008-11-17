package com.methodknowledgy.events;

public class SingleValueEvent<SourceType, PropertyType> {

	private SourceType source;
	private String propertyName;
	private PropertyType value;
	private String message;

	public SingleValueEvent(SourceType source, String propertyName,
			PropertyType value) {
		this(source, propertyName, value, null);
	}

	public SingleValueEvent(SourceType source, String propertyName,
			PropertyType value, String message) {
		this.source = source;
		this.propertyName = propertyName;
		this.value = value;
		this.message = message == null ? "" : message;
	}

	public SourceType getSource() {
		return source;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public PropertyType getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

}
