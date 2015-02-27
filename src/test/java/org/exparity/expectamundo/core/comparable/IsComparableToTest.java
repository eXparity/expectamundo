
package org.exparity.expectamundo.core.comparable;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link CompareEqualsToExpectation} expectation
 * 
 * @author Stewart Bissett
 */
public class IsComparableToTest {

	@Test
	public void canCheckForCompareEqualsTo() {
		String expectedValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isComparableTo(expectedValue);
		expectThat(new SimpleType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForCompareNotEqualsTo() {
		String expectedValue = aRandomString(), differentValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isComparableTo(expectedValue);
		expectThat(new SimpleType(differentValue)).matches(expected);
	}
}
