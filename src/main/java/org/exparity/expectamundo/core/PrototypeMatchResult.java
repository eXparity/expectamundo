package org.exparity.expectamundo.core;

import java.util.List;

public class PrototypeMatchResult<T> {

	private final List<PrototypeValueDifference> differences;
	private final T actual, expected;

	public PrototypeMatchResult(final T actual, final T expected, final List<PrototypeValueDifference> differences) {
		this.actual = actual;
		this.expected = expected;
		this.differences = differences;
	}

	public boolean isMismatch() {
		return !differences.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public Class<?> getExpectedType() {
		return ((Prototyped<T>) expected).getRawType();
	}

	public Class<?> getActualType() {
		return actual.getClass();
	}
	
	public boolean  isActualNull() {
		return actual == null;
	}

	public List<PrototypeValueDifference> getDifferences() {
		return differences;
	}

	@SuppressWarnings("unchecked")
	public List<PrototypeValueMatcher> getExpectations() {
		return ((Prototyped<T>) expected).getExpectations();
	}

}