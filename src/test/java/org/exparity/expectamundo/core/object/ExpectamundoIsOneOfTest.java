
package org.exparity.expectamundo.core.object;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link IsEqualTo} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsOneOfTest {

	@Test
	public void canCheckForIsOneOf() {
		final String expectedValue = aRandomString(), expectedValue2 = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isOneOf(expectedValue, expectedValue2);
		expectThat(new SimpleType(expectedValue)).matches(expected);
		expectThat(new SimpleType(expectedValue2)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForEqualToNull() {
		final String expectedValue = aRandomString(), expectedValue2 = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isOneOf(expectedValue, expectedValue2);
		expectThat(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotEqualTo() {
		final String expectedValue = aRandomString(), expectedValue2 = aRandomString(), differentValue = expectedValue + expectedValue2;
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isOneOf(expectedValue, expectedValue2);
		expectThat(new SimpleType(differentValue)).matches(expected);
	}
}
