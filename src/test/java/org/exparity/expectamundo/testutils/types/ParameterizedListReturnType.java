package org.exparity.expectamundo.testutils.types;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stewart Bissett
 */
public class ParameterizedListReturnType<T> {

	private final List<T> value;

	public ParameterizedListReturnType(final List<T> value) {
		this.value = value;
	}

	@SafeVarargs
	public ParameterizedListReturnType(final T... values) {
		this(Arrays.asList(values));
	}

	public List<T> getValue() {
		return value;
	}
}
