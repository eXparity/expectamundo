
package org.exparity.expectamundo.core;

/**
 * @author Stewart Bissett
 */
public class PrototypeVerifier<T> {

	private final T actual;

	public PrototypeVerifier(final T actual) {
		this.actual = actual;
	}

	/**
	 * Check the actual instance of the object matches the expectations set on the proptype.
	 * @param prototype the prototype object containing the expectations
	 */
	@SuppressWarnings("unchecked")
	public void matches(final T prototype) {
		if (!Prototyped.class.isInstance(prototype)) {
			throw new IllegalArgumentException("Object does not implement Prototyped. Please construct using PrototypeMatcher.expected");
		}
		StringBuffer mismatchDescription = new StringBuffer();
		Prototyped<T> prototyped = (Prototyped<T>) prototype;
		if (!matchesPrototype(prototyped, mismatchDescription)) {
			StringBuffer buffer = new StringBuffer("\nExpected ");
			describeTo(buffer, prototyped);
			buffer.append("\nbut actual is ").append(mismatchDescription);
			throw new AssertionError(buffer.toString());
		}
	}

	protected boolean matchesPrototype(final Prototyped<?> prototyped, final StringBuffer mismatchDescription) {
		mismatchDescription.append("a ").append(actual.getClass().getSimpleName()).append(" containing properties :\n");
		boolean matches = true;
		for (PrototypePropertyMatcher expectation : prototyped.getExpectations()) {
			Object actualValue = expectation.getPropertyValue(actual);
			if (!expectation.matches(actualValue)) {
				mismatchDescription.append("\t").append(expectation.getPropertyPath()).append(" is ").append(actualValue).append("\n");
				matches = false;
			}
		}
		return matches;
	}

	private void describeTo(final StringBuffer description, final Prototyped<?> prototyped) {
		Class<?> rawType = prototyped.getRawType();
		description.append("a ").append(rawType.getSimpleName());
		if (!prototyped.getExpectations().isEmpty()) {
			description.append(" containing properties :");
			for (PrototypePropertyMatcher expecation : prototyped.getExpectations()) {
				description.append("\n\t").append(expecation.getPropertyPath()).append(" is ").append(expecation.getExpectation());
			}
		}
	}

}
