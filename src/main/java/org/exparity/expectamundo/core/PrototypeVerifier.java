
package org.exparity.expectamundo.core;


/**
 * @author Stewart Bissett
 */
public class PrototypeVerifier<T> {

	private final PrototypeMatcher<T> matcher = new PrototypeMatcher<>();
	private final PrototypeMismatchReporter descriptor = new PrototypeMismatchReporter();
	private final T actual;

	public PrototypeVerifier(final T actual) {
		this.actual = actual;
	}

	/**
	 * Check the actual instance of the object matches the expectations set on the proptype.
	 * @param prototype the prototype object containing the expectations
	 */
	public void matches(final T prototype) {
		PrototypeMatchResult<T> result = matcher.compare(actual, prototype);
		if (result.isMismatch()) {
			throw new AssertionError(descriptor.describeInstanceMismatch(result));
		}
	}
}
