
package org.exparity.expectamundo.core.object;

import org.exparity.expectamundo.testutils.types.PolymorphicReturnType;
import org.exparity.expectamundo.testutils.types.PolymorphicSubtype1;
import org.exparity.expectamundo.testutils.types.PolymorphicSubtype2;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.stub.random.RandomBuilder.aRandomInteger;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link IsEqualTo} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsInstanceOfTest {

	@Test
	public void canCheckForInstanceOf() {
		PolymorphicReturnType expected = prototype(PolymorphicReturnType.class);
		expect(expected.getValue()).isInstanceOf(PolymorphicSubtype1.class);
		expectThat(new PolymorphicReturnType(new PolymorphicSubtype1(aRandomInteger()))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForNotInstanceOf() {
		PolymorphicReturnType expected = prototype(PolymorphicReturnType.class);
		expect(expected.getValue()).isInstanceOf(PolymorphicSubtype1.class);
		expectThat(new PolymorphicReturnType(new PolymorphicSubtype2(aRandomString()))).matches(expected);
	}
}
