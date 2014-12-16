
package org.exparity.expectamundo;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;
import static org.exparity.stub.random.RandomBuilder.aRandomInteger;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expactamundo} invocations of the {@link org.exparity.expectamundo.expectations.HasLength} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoHasLength {

	@Test
	public void canCheckForSize() {
		int expectedLength = aRandomInteger(10, 50);
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasLength(expectedLength);
		verify(new SimpleType(aRandomString(expectedLength))).matches(expected);
	}

	@Test
	public void canCheckForIsSizeIfNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasLength(0);
		verify(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSizeIfNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasLength(aRandomInteger(1, 50));
		verify(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSize() {
		int expectedLength = 10, differentLength = 20;
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasLength(expectedLength);
		verify(new SimpleType(aRandomString(differentLength))).matches(expected);
	}
}
