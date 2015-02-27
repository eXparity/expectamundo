
package org.exparity.expectamundo.core.string;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomInteger;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link org.exparity.expectamundo.core.object.HasLength} expectation
 * 
 * @author Stewart Bissett
 */
public class HasLengthTest {

	@Test
	public void canCheckForSize() {
		int expectedLength = aRandomInteger(10, 50);
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasLength(expectedLength);
		expectThat(new SimpleType(aRandomString(expectedLength))).matches(expected);
	}

	@Test
	public void canCheckForIsSizeIfNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasLength(0);
		expectThat(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSizeIfNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasLength(aRandomInteger(1, 50));
		expectThat(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForWrongSize() {
		int expectedLength = 10, differentLength = 20;
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).hasLength(expectedLength);
		expectThat(new SimpleType(aRandomString(differentLength))).matches(expected);
	}
}
