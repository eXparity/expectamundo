
package org.exparity.expectamundo;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link org.exparity.expectamundo.expectations.HasPattern} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoHasPattern {

	@Test
	public void canCheckForHasPattern() {
		String pattern = ".*match";
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasPattern(pattern);
		verify(new SimpleType("A string to match")).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongPattern() {
		String pattern = ".*no match";
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasPattern(pattern);
		verify(new SimpleType("A string to match")).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNoMatchIfNull() {
		String pattern = ".*no match";
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasPattern(pattern);
		verify(new SimpleType(null)).matches(expected);
	}
}
