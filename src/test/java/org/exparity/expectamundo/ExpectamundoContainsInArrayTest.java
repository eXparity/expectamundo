
package org.exparity.expectamundo;

import org.exparity.expectamundo.testutils.types.ArrayType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link Contains} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoContainsInArrayTest {

	@Test
	public void canCheckForContains() {
		String expectedValue = aRandomString();
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).contains(expectedValue);
		verify(new ArrayType(new String[] {
				expectedValue
		})).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotContains() {
		String expectedValue = aRandomString(), differentValue = aRandomString();
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).contains(expectedValue);
		verify(new ArrayType(new String[] {
				differentValue
		})).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotContainsIfNull() {
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).contains(aRandomString());
		verify(new ArrayType(null)).matches(expected);
	}

}
