
package org.exparity.expectamundo.core.string;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link org.exparity.expectamundo.core.object.HasPattern} expectation
 * 
 * @author Stewart Bissett
 */
public class HasPatternTest {

	@Test
	public void canCheckForHasPattern() {
		String pattern = ".*match";
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasPattern(pattern);
		expectThat(new SimpleType("A string to match")).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongPattern() {
		String pattern = ".*no match";
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasPattern(pattern);
		expectThat(new SimpleType("A string to match")).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNoMatchIfNull() {
		String pattern = ".*no match";
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasPattern(pattern);
		expectThat(new SimpleType(null)).matches(expected);
	}
}
