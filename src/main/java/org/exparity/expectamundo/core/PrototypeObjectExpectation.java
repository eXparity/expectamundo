
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.expectations.EqualToExpectation;
import org.exparity.expectamundo.expectations.HamcrestExpectation;
import org.exparity.expectamundo.expectations.InstanceOfExpectation;
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

	public <T> void isEqualTo(final T expectedValue) {
		setExpectation(new EqualToExpectation<T>(expectedValue));
	}

	public <T> void isInstanceOf(final Class<T> expectedValue) {
		setExpectation(new InstanceOfExpectation<T>(expectedValue));
	}

	public void isNullValue() {
		setExpectation(new NullExpectation());
	}

	public void isNotNullValue() {
		setExpectation(new NotNullExpectation());
	}

	protected void setExpectation(final PropertyExpectation<?> expectation) {
		prototype.addExpectation(new PrototypePropertyMatcher(property, expectation));
		prototype.setActiveProperty(null);
	}

}
