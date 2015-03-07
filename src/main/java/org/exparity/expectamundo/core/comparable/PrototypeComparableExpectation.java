
package org.exparity.expectamundo.core.comparable;

import org.exparity.expectamundo.core.Prototype;
import org.exparity.expectamundo.core.PrototypeValue;
import org.exparity.expectamundo.core.object.PrototypeObjectExpectation;

/**
 * @author Stewart Bissett
 */
public class PrototypeComparableExpectation<T extends Comparable<T>> extends PrototypeObjectExpectation<T> {

	public PrototypeComparableExpectation(final Prototype<?> prototype, final PrototypeValue property) {
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
