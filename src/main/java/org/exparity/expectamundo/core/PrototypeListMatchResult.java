
package org.exparity.expectamundo.core;

import java.util.List;

/**
 * @author Stewart Bissett
 */
public class PrototypeListMatchResult<T> {

	private final T expectation;
	private final Prototyped<T> prototyped;
	private List<PrototypeMatchResult<T>> mismatches;

	@SuppressWarnings("unchecked")
	public PrototypeListMatchResult(final T expectation, final List<PrototypeMatchResult<T>> mismatches) {
		this.expectation = expectation;
		if (!Prototyped.class.isInstance(expectation)) {
			throw new IllegalArgumentException("Object does not implement Prototyped. Please construct using Expectamundo.prototype");
		}
		this.prototyped = (Prototyped<T>) expectation;
		this.mismatches = mismatches;
	}

	public boolean isMismatch() {
		return !mismatches.isEmpty();
	}

	public List<PrototypeMatchResult<T>> getMismatches() {
		return mismatches;
	}

	public T getExpectation() {
		return expectation;
	}

	public Prototyped<T> getExpectationAsPrototype() {
		return prototyped;
	}

	public List<PrototypeValueMatcher> getExpectations() {
		return prototyped.getExpectations();
	}

}
