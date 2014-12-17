
package org.exparity.expectamundo;

import org.exparity.expectamundo.testutils.types.ArrayType;
import org.exparity.stub.random.RandomBuilder;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;
import static org.exparity.stub.random.RandomBuilder.aRandomArrayOf;
import static org.exparity.stub.random.RandomBuilder.aRandomInteger;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link org.exparity.expectamundo.expectations.HasSize} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoHasLengthOfArray {

	@Test
	public void canCheckForSize() {
		int expectedLength = RandomBuilder.aRandomInteger(1, 10);
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).hasLength(expectedLength);
		verify(new ArrayType(aRandomArrayOf(String.class, expectedLength, expectedLength))).matches(expected);
	}

	@Test
	public void canCheckForIsSizeIfNull() {
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).hasLength(0);
		verify(new ArrayType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSizeIfNull() {
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).hasLength(1);
		verify(new ArrayType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSize() {
		int expectedLength = aRandomInteger(1, 10), differnentLength = aRandomInteger(11, 20);
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).hasLength(expectedLength);
		verify(new ArrayType(aRandomArrayOf(String.class, differnentLength, differnentLength))).matches(expected);
	}
}