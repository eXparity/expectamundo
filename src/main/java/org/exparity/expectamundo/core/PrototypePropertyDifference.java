package org.exparity.expectamundo.core;

public class PrototypePropertyDifference {

	private final String path;
	private final Object value;

	public PrototypePropertyDifference(final String path, final Object value) {
		this.path = path;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public String getPath() {
		return path;
	}

}