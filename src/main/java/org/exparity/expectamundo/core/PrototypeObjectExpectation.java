
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.core.expectations.IsEqualTo;
import org.exparity.expectamundo.core.expectations.IsInstanceOf;
import org.exparity.expectamundo.core.expectations.IsNotNull;
import org.exparity.expectamundo.core.expectations.IsNull;
import org.exparity.expectamundo.core.expectations.Matches;
import org.hamcrest.Matcher;

/**
 * @author Stewart Bissett
 */
public class PrototypeObjectExpectation<T> {

	private final Prototype<?> prototype;
	private final PrototypeProperty property;

	public PrototypeObjectExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		this.prototype = prototype;
		this.property = property;
	}

	public void matches(final Matcher<T> expected) {
		hasExpectation(new Matches<T>(expected));
	}

	public void isEqualTo(final T expectedValue) {
		hasExpectation(new IsEqualTo<T>(expectedValue));
	}

	public void isInstanceOf(final Class<? extends T> expectedValue) {
		hasExpectation(new IsInstanceOf<T>(expectedValue));
	}

	public void isNull() {
		hasExpectation(new IsNull<T>());
	}

	public void isNotNull() {
		hasExpectation(new IsNotNull<T>());
	}

	public void hasExpectation(final PropertyExpectation<T> expectation) {
		prototype.addExpectation(new PrototypePropertyMatcher(property, expectation));
		prototype.setActiveProperty(null);
	}

}
