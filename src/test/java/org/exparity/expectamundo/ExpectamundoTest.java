
package org.exparity.expectamundo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.exparity.expectamundo.core.TypeReference;
import org.exparity.expectamundo.testutils.types.GraphType;
import org.exparity.expectamundo.testutils.types.HashCodeType;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.exparity.expectamundo.testutils.types.MapReturnType;
import org.exparity.expectamundo.testutils.types.PrimitiveArrayType;
import org.exparity.expectamundo.testutils.types.PrimitiveType;
import org.exparity.expectamundo.testutils.types.SimpleType;
import org.exparity.expectamundo.testutils.types.ToStringType;
import org.exparity.stub.random.RandomBuilder;
import org.junit.Test;
import static java.util.Collections.singletonMap;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.matches;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;
import static org.exparity.stub.random.RandomBuilder.aRandomByteArray;
import static org.exparity.stub.random.RandomBuilder.aRandomInteger;
import static org.exparity.stub.random.RandomBuilder.aRandomString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Stewart Bissett
 */
public class ExpectamundoTest {

	@Test
	public void canMatchSimpleProperty() {
		final String expectedValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		verify(new SimpleType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailSimpleProperty() {
		final String expectedValue = aRandomString(), differentValue = expectedValue + aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		verify(new SimpleType(differentValue)).matches(expected);
	}

	@Test
	public void canMatchPrimitiveProperty() {
		final int expectedValue = aRandomInteger();
		PrimitiveType expected = prototype(PrimitiveType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		verify(new PrimitiveType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailPrimitiveProperty() {
		final int expectedValue = aRandomInteger(), differentValue = expectedValue + aRandomInteger();
		PrimitiveType expected = prototype(PrimitiveType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		verify(new PrimitiveType(differentValue)).matches(expected);
	}

	@Test
	public void canMatchPrimitiveArrayProperty() {
		final byte[] expectedValue = aRandomByteArray();
		PrimitiveArrayType expected = prototype(PrimitiveArrayType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		verify(new PrimitiveArrayType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailPrimitiveArrayProperty() {
		final byte[] expectedValue = aRandomByteArray(), differentValue = aRandomByteArray();
		PrimitiveArrayType expected = prototype(PrimitiveArrayType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		verify(new PrimitiveArrayType(differentValue)).matches(expected);
	}

	@Test
	public void canMatchObjectProperty() {
		final SimpleType expectedValue = new SimpleType(aRandomString());
		GraphType expected = prototype(GraphType.class);
		expect(expected.getChild()).equalTo(expectedValue);
		verify(new GraphType(aRandomString(), expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailObjectProperty() {
		final SimpleType expectedValue = new SimpleType(aRandomString()), differentValue = new SimpleType(aRandomString());
		GraphType expected = prototype(GraphType.class);
		expect(expected.getChild()).equalTo(expectedValue);
		verify(new GraphType(aRandomString(), differentValue)).matches(expected);
	}

	@Test
	public void canMatchToStringProperty() {
		final String expectedValue = aRandomString();
		ToStringType expected = prototype(ToStringType.class);
		expect(expected.toString()).equalTo(expectedValue);
		verify(new ToStringType(expectedValue)).matches(expected);
	}

	@Test
	public void canMatchHashCodeProperty() {
		final int expectedValue = RandomBuilder.aRandomInteger();
		HashCodeType expected = prototype(HashCodeType.class);
		expect(expected.hashCode()).equalTo(expectedValue);
		verify(new HashCodeType(expectedValue)).matches(expected);
	}

	@Test
	public void canMatchGraphProperties() {
		final String expectedValue = aRandomString();
		GraphType expected = prototype(GraphType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		expect(expected.getChild().getValue()).equalTo(expectedValue);
		verify(new GraphType(expectedValue, new SimpleType(expectedValue))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailGraphProperty() {
		final String expectedValue = aRandomString(), differentValue = expectedValue + aRandomString();
		GraphType expected = prototype(GraphType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		expect(expected.getChild().getValue()).equalTo(expectedValue);
		verify(new GraphType(expectedValue, new SimpleType(differentValue))).matches(expected);
	}

	@Test
	public void canMatchGenericProperties() {
		final String expectedValue = aRandomString();
		List<String> expected = prototype(new TypeReference<List<String>>() {});
		expect(expected.get(0)).equalTo(expectedValue);
		List<String> actual = Arrays.asList(expectedValue);
		verify(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailGenericProperties() {
		final String expectedValue = aRandomString(), differentValue = expectedValue + aRandomString();
		List<String> expected = prototype(new TypeReference<List<String>>() {});
		expect(expected.get(0)).equalTo(expectedValue);
		List<String> actual = Arrays.asList(differentValue);
		verify(actual).matches(expected);
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	@Test(expected = IllegalArgumentException.class)
	public void canFailGenericPropertiesIfTypeNotPassed() {
		final String expectedValue = aRandomString(), differentValue = expectedValue + aRandomString();
		Object expected = prototype(new TypeReference() {});
	}

	@Test
	public void canMatchListProperties() {
		final List<String> expectedValue = Arrays.asList(aRandomString());
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue().get(0)).equalTo(expectedValue.get(0));
		verify(new ListReturnType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailListProperties() {
		final String expectedString = aRandomString();
		final List<String> expectedValue = Arrays.asList(expectedString), differentValue = Arrays.asList(expectedString + aRandomString());
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue().get(0)).equalTo(expectedValue.get(0));
		verify(new ListReturnType(differentValue)).matches(expected);
	}

	@Test
	public void canMatchMapProperties() {
		String key = aRandomString(), value = aRandomString();
		MapReturnType expected = prototype(MapReturnType.class);
		expect(expected.getValue().get(key)).equalTo(value);
		verify(new MapReturnType(Collections.singletonMap(key, value))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailMapPropertiesBadKey() {
		String key = aRandomString(), value = aRandomString(), aDifferentKey = aRandomString();
		MapReturnType expected = prototype(MapReturnType.class);
		expect(expected.getValue().get(key)).equalTo(value);
		verify(new MapReturnType(singletonMap(aDifferentKey, value))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailMapPropertiesBadValue() {
		String key = aRandomString(), value = aRandomString(), aDifferentValue = aRandomString();
		MapReturnType expected = prototype(MapReturnType.class);
		expect(expected.getValue().get(key)).equalTo(value);
		verify(new MapReturnType(singletonMap(key, aDifferentValue))).matches(expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailIfSettingExpectationOnNormalInstance() {
		final String expectedValue = aRandomString();
		SimpleType expected = new SimpleType(expectedValue);
		expect(expected.getValue()).equalTo(expectedValue);
	}

	@Test
	public void canMatchUsingHamcrest() {
		String expectedValue = aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		SimpleType actual = new SimpleType(expectedValue);
		assertThat(actual, matches(expected));
	}

	@Test(expected = AssertionError.class)
	public void canFailUsingHamcrest() {
		String expectedValue = aRandomString(), differentValue = expectedValue + aRandomString();
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).equalTo(expectedValue);
		SimpleType actual = new SimpleType(differentValue);
		assertThat(actual, matches(expected));
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailUsingHamcrestOnNormalInstance() {
		SimpleType actual = new SimpleType(aRandomString());
		assertThat(actual, matches(new SimpleType(aRandomString())));
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailIfVerifyingExpectationOnNormalInstance() {
		SimpleType expected = new SimpleType(aRandomString());
		verify(expected).matches(expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailToMatchPrimitiveType() {
		prototype(byte.class);
	}
}
