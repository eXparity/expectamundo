
package org.exparity.expactamundo.core;

import java.util.List;

/**
 * @author Stewart Bissett
 */
public class PrototypeVerifier<T> {

	private final T actual;

	public PrototypeVerifier(final T actual) {
		this.actual = actual;
	}

	public void matches(final T expected) {

		if (!Prototyped.class.isInstance(expected)) {
			throw new IllegalArgumentException("Object does not implement ExceptionList. Please construct using PrototypeMatcher.expected");
		}

		StringBuffer mismatchDescription = new StringBuffer();
		mismatchDescription.append("a ").append(actual.getClass().getSimpleName()).append(" containing properties :\n");
		boolean matches = true;
		Prototyped<T> prototype = (Prototyped<T>) expected;
		List<PrototypePropertyMatcher> expectations = prototype.getExpectations();
		for (PrototypePropertyMatcher expectation : expectations) {
			Object actualValue = expectation.getPropertyValue(actual);
			if (!expectation.matches(actualValue)) {
				mismatchDescription.append("\t").append(expectation.getPropertyPath()).append(" is ").append(actualValue);
				matches = false;
			}
		}

		if (!matches) {
			StringBuffer buffer = new StringBuffer("\nExpected ");
			describeTo(buffer, prototype.getRawType(), expectations);
			buffer.append("\nbut actual is ").append(mismatchDescription);
			throw new AssertionError(buffer.toString());
		}
	}

	private void describeTo(final StringBuffer description, final Class<?> rawType, final List<PrototypePropertyMatcher> expectations) {
		description.append("a ").append(rawType.getSimpleName());
		if (!expectations.isEmpty()) {
			description.append(" containing properties :");
			for (PrototypePropertyMatcher expecation : expectations) {
				description.append("\n\t").append(expecation.getPropertyPath()).append(" is ").append(expecation.getExpectation());
			}
		}
	}

}
