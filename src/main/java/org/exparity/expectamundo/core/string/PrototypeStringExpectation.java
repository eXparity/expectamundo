
package org.exparity.expectamundo.core.string;

import org.exparity.expectamundo.core.Prototype;
import org.exparity.expectamundo.core.PrototypeProperty;
import org.exparity.expectamundo.core.comparable.PrototypeComparableExpectation;

/**
 * @author Stewart Bissett
 */
public class PrototypeStringExpectation extends PrototypeComparableExpectation<String> {

	public PrototypeStringExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	/**
	 * Set an expectation that the property value is of the given length. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).hasLength(52);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param expectedSize the expected size of the property value
	 */
	public void hasLength(final int expectedSize) {
		hasExpectation(new HasLength(expectedSize));
	}

	/**
	 * Set an expectation that the property value matches a regular expression. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).hasPattern(&quot;A.*&quot;);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param pattern the pattern to match against
	 */
	public void hasPattern(final String pattern) {
		hasExpectation(new HasPattern(pattern));
	}

	/**
	 * Set an expectation that the property value matches another property regardless of case. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isEqualToIgnoreCase(&quot;jANe&quot;);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param expected the value to check against
	 */
	public void isEqualToIgnoreCase(final String expected) {
		hasExpectation(new IsEqualToIgnoreCase(expected));
	}

}
