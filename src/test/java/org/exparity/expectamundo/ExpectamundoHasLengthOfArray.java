
package org.exparity.expectamundo;

import org.exparity.expectamundo.testutils.types.ArrayType;
import org.exparity.stub.random.RandomBuilder;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomArrayOf;
import static org.exparity.stub.random.RandomBuilder.aRandomInteger;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link org.exparity.expectamundo.core.expectations.HasSize} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoHasLengthOfArray {

	@Test
	public void canCheckForSize() {
		int expectedLength = RandomBuilder.aRandomInteger(1, 10);
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).hasLength(expectedLength);
		expectThat(new ArrayType(aRandomArrayOf(String.class, expectedLength, expectedLength))).matches(expected);
	}

	@Test
	public void canCheckForIsSizeIfNull() {
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).hasLength(0);
		expectThat(new ArrayType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSizeIfNull() {
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).hasLength(1);
		expectThat(new ArrayType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSize() {
		int expectedLength = aRandomInteger(1, 10), differnentLength = aRandomInteger(11, 20);
		ArrayType expected = prototype(ArrayType.class);
		expect(expected.getValue()).hasLength(expectedLength);
		expectThat(new ArrayType(aRandomArrayOf(String.class, differnentLength, differnentLength))).matches(expected);
	}
}
