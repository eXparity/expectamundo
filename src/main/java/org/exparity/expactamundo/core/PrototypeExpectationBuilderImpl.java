
package org.exparity.expactamundo.core;

import org.exparity.expactamundo.expectations.ObjectExpectations;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * @author Stewart Bissett
 */
public class PrototypeExpectationBuilderImpl<T> implements ObjectExpectations<T> {

	private final Prototype<?> prototype;
	private final PrototypeProperty property;

	public PrototypeExpectationBuilderImpl(final Prototype<?> prototype, final PrototypeProperty property) {
		this.prototype = prototype;
		this.property = property;
	}

	@Override
	public void matches(final Matcher<T> expected) {
		prototype.addExpectation(new PrototypePropertyMatcher(property, expected));
		prototype.setActiveProperty(null);
	}

	@Override
	public void equalTo(final T expectedValue) {
		matches(Matchers.equalTo(expectedValue));
	}

	@Override
	public void nullValue() {
		matches((Matcher<T>) Matchers.nullValue());
	}

	@Override
	public void notNullValue() {
		matches((Matcher<T>) Matchers.notNullValue());
	}

}
