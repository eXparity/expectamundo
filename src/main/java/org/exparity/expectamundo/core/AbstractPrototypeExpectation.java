
package org.exparity.expectamundo.core;

/**
 * @author Stewart Bissett
 */
public class AbstractPrototypeExpectation<T> {

	public final Prototype<?> prototype;
	public final PrototypeProperty property;

	protected AbstractPrototypeExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		this.prototype = prototype;
		this.property = property;
	}

	protected void appendExpectationToPrototype(final PropertyExpectation<?> expectation) {
		prototype.addExpectation(new PrototypePropertyMatcher(property, expectation));
		prototype.setActiveProperty(null);
	}

}