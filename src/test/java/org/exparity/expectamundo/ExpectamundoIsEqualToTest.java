
package org.exparity.expectamundo;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link EqualToExpectation} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsEqualToTest {

	@Test
	public void canCheckForEqualTo() {
		final String expectedValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		verify(new SimpleType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotEqualTo() {
		final String expectedValue = aRandomString(), differentValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		verify(new SimpleType(differentValue)).matches(expected);
	}
}
