
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.core.expectations.IsComparableTo;

/**
 * @author Stewart Bissett
 */
public class PrototypeComparableExpectation<T extends Comparable<T>> extends PrototypeObjectExpectation<T> {

	public PrototypeComparableExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	/**
	 * Set an expectation that the property value is comparable to a value. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.number()).isComparableTo(new BigDecimal("1.01");;
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param expectedValue the type this property should be comparable to
	 */
	public void isComparableTo(final T expectedValue) {
		hasExpectation(new IsComparableTo<T>(expectedValue));
	}
}
