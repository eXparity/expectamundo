
package org.exparity.expectamundo;

import org.exparity.expectamundo.testutils.types.ArrayType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link IsEmpty} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsNotEmptyArrayTest {

	@Test
	public void canCheckForIsNotEmpty() {
		String[] expectedValue = new String[] {
				aRandomString()
		};
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).isNotEmpty();
		expectThat(new ArrayType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsEmpty() {
		String[] expectedValue = new String[0];
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).isNotEmpty();
		expectThat(new ArrayType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsEmptyForNull() {
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).isNotEmpty();
		expectThat(new ArrayType(null)).matches(expected);
	}
}
