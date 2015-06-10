package org.exparity.expectamundo.testutils.types;

/**
 * @author Stewart Bissett
 */
public class ParameterizedReturnType<T> {

	private final T value;

	public ParameterizedReturnType(final T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
