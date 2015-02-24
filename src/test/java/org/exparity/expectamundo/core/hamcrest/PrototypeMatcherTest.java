package org.exparity.expectamundo.core.hamcrest;

import org.exparity.expectamundo.testutils.types.SimpleType;
import org.junit.Test;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.expectamundo.core.hamcrest.PrototypeMatcher.matchesPrototype;
import static org.exparity.stub.random.RandomBuilder.aRandomString;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Stewart Bissett
 */
public class PrototypeMatcherTest {

	@Test
	public void canMatchAPrototype() {
		String expectedValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		assertThat(new SimpleType(expectedValue), matchesPrototype(expected));
	}

	@Test(expected = AssertionError.class)
	public void canMatchAPrototypeAndFail() {
		String expectedValue = aRandomString(), aDifferentValue = expectedValue + aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		assertThat(new SimpleType(aDifferentValue), matchesPrototype(expected));
	}

	@Test(expected = IllegalArgumentException.class)
	public void canNotMatchNonPrototype() {
		new PrototypeMatcher<>(new SimpleType(aRandomString()));
	}

}
