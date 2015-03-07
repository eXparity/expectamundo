package org.exparity.expectamundo.core;

public class PrototypeValueDifference {

	private final String label;
	private final Object value;

	public PrototypeValueDifference(final String path, final Object value) {
		this.label = path;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public String getPath() {
		return label;
	}

}