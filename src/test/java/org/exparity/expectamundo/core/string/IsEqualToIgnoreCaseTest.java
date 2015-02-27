
package org.exparity.expectamundo.core.string;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link IsEqualTo} expectation
 * 
 * @author Stewart Bissett
 */
public class IsEqualToIgnoreCaseTest {

	@Test
	public void canCheckForEqualToIgnoreCase() {
		final String expectedValue = aRandomString().toUpperCase();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualToIgnoreCase(expectedValue);
		expectThat(new SimpleType(expectedValue.toLowerCase())).matches(expected);
	}

	@Test
	public void canCheckForEqualToNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualToIgnoreCase(null);
		expectThat(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotEqualToIgnoreCase() {
		final String expectedValue = aRandomString(), differentValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualToIgnoreCase(expectedValue);
		expectThat(new SimpleType(differentValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotEqualToValueVsNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualToIgnoreCase(aRandomString());
		expectThat(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotEqualToNullVsValue() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualToIgnoreCase(null);
		expectThat(new SimpleType(aRandomString())).matches(expected);
	}

}
