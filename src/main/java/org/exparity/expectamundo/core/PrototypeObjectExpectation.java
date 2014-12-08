
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.expectations.EqualToExpectation;
import org.exparity.expectamundo.expectations.HamcrestExpectation;
import org.exparity.expectamundo.expectations.NotNullExpectation;
import org.exparity.expectamundo.expectations.NullExpectation;
import org.hamcrest.Matcher;

/**
 * @author Stewart Bissett
 */
public class PrototypeObjectExpectation {

	private final Prototype<?> prototype;
	private final PrototypeProperty property;

	public PrototypeObjectExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		this.prototype = prototype;
		this.property = property;
	}

	public <T> void matches(final Matcher<T> expected) {
		setExpectation(new HamcrestExpectation<T>(expected));
	}

	public <T> void equalTo(final T expectedValue) {
		setExpectation(new EqualToExpectation(expectedValue));
	}

	public void nullValue() {
		setExpectation(new NullExpectation());
	}

	public void notNullValue() {
		setExpectation(new NotNullExpectation());
	}

	protected void setExpectation(final PropertyExpectation expectation) {
		prototype.addExpectation(new PrototypePropertyMatcher(property, expectation));
		prototype.setActiveProperty(null);
	}

}
