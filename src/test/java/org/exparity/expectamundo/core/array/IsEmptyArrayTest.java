
package org.exparity.expectamundo.core.array;

import org.exparity.expectamundo.testutils.types.ArrayType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link IsEmpty} expectation
 * 
 * @author Stewart Bissett
 */
public class IsEmptyArrayTest {

	@Test
	public void canCheckForIsEmpty() {
		String[] expectedValue = new String[0];
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).isEmpty();
		expectThat(new ArrayType(expectedValue)).matches(expected);
	}

	@Test
	public void canCheckForIsEmptyForNull() {
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).isEmpty();
		expectThat(new ArrayType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsNotEmpty() {
		String[] expectedValue = new String[] {
				aRandomString()
		};
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).isEmpty();
		expectThat(new ArrayType(expectedValue)).matches(expected);
	}
}
