
package org.exparity.expectamundo.core;


/**
 * @author Stewart Bissett
 */
@SuppressWarnings("rawtypes")
public class PrototypeValueMatcher {

	private final PrototypeValue property;
	private final PropertyExpectation expectation;

	public PrototypeValueMatcher(final PrototypeValue property, final PropertyExpectation expectation) {
		this.property = property;
		this.expectation = expectation;
	}

	@SuppressWarnings("unchecked")
	public boolean matches(final Object actual) {
		return expectation.matches(actual);
	}

	public String getPropertyPath() {
		return property.getLabel();
	}

	public Object getPropertyValue(final Object actual) {
		return property.getValue(actual);
	}

	public String getExpectation() {
		return expectation.describe();
	}

	// public PrototypeProperty getProperty() {
	// return property;
	// }

	@Override
	public String toString() {
		return "Expectation [" + getPropertyPath() + "=" + getExpectation() + "]";
	}
}
