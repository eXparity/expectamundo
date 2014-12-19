
package org.exparity.expectamundo;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link CompareEqualsToExpectation} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoCompareEqualsToTest {

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
