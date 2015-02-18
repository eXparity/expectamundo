
package org.exparity.expectamundo;

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
public class ExpectamundoIsNotEqualToTest {

	@Test
	public void canCheckForNotEqualTo() {
		final String expectedValue = aRandomString(), differentValue = expectedValue + aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isNotEqualTo(expectedValue);
		expectThat(new SimpleType(differentValue)).matches(expected);
	}

	@Test
	public void canCheckForNotEqualToNull() {
		final String expectedValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isNotEqualTo(expectedValue);
		expectThat(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForEqualTo() {
		final String expectedValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isNotEqualTo(expectedValue);
		expectThat(new SimpleType(expectedValue)).matches(expected);
	}
}
