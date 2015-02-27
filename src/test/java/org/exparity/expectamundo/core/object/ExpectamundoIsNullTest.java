
package org.exparity.expectamundo.core.object;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

/**
 * Unit Test for {@link Expectamundo} invocations of the {@link IsNull} expectation
 * 
 * @author Stewart Bissett
 */
public class ExpectamundoIsNullTest {

	@Test
	public void canCheckForIsNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isNull();
		expectThat(new SimpleType(null)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canCheckForIsNotNull() {
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isNull();
		expectThat(new SimpleType(aRandomString())).matches(expected);
	}

}
