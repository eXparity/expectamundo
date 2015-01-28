package org.exparity.expectamundo.core;

import java.util.List;

public class PrototypeMatchResult<T> {

	private final List<PrototypePropertyDifference> differences;
	private final T actual, expected;

	public PrototypeMatchResult(final T actual, final T expected, final List<PrototypePropertyDifference> differences) {
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

	public List<PrototypePropertyDifference> getDifferences() {
		return differences;
	}

	@SuppressWarnings("unchecked")
	public List<PrototypePropertyMatcher> getExpectations() {
		return ((Prototyped<T>) expected).getExpectations();
	}

}