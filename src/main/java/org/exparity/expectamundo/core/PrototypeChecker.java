
package org.exparity.expectamundo.core;

/**
 * @author Stewart Bissett
 */
public class PrototypeChecker<T> {

	private final T actual;

	public PrototypeChecker(final T actual) {
		this.actual = actual;
	}

	/**
	 * Check the actual instance of the object matches the expectations set on the proptype.
	 * @param prototype the prototype object containing the expectations
	 */
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public boolean matches(final T prototype) {
		if (!Prototyped.class.isInstance(prototype)) {
			throw new IllegalArgumentException("Object does not implement Prototyped. Please construct using PrototypeMatcher.expected");
		}
		StringBuffer mismatchDescription = new StringBuffer();
		Prototyped<T> prototyped = (Prototyped) prototype;
		return matchesPrototype(prototyped, mismatchDescription);
	}

	protected boolean matchesPrototype(final Prototyped<?> prototyped, final StringBuffer mismatchDescription) {
		mismatchDescription.append("a ").append(actual.getClass().getSimpleName()).append(" containing properties :\n");
		boolean matches = true;
		for (PrototypePropertyMatcher expectation : prototyped.getExpectations()) {
			Object actualValue = expectation.getPropertyValue(actual);
			if (!expectation.matches(actualValue)) {
				mismatchDescription.append("\t").append(expectation.getPropertyPath()).append(" is ").append(actualValue);
				matches = false;
			}
		}
		return matches;
	}
}
