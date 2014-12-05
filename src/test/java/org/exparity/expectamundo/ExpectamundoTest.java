
package org.exparity.expectamundo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.exparity.expectamundo.core.TypeReference;
import org.exparity.expectamundo.testutils.types.GraphType;
import org.exparity.expectamundo.testutils.types.HashCodeType;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.exparity.expectamundo.testutils.types.MapReturnType;
import org.exparity.expectamundo.testutils.types.SimpleType;
import org.exparity.expectamundo.testutils.types.ToStringType;
import org.exparity.stub.random.RandomBuilder;
import org.junit.Test;
import static java.util.Collections.singletonMap;
import static org.exparity.expectamundo.Expactamundo.expect;
import static org.exparity.expectamundo.Expactamundo.prototype;
import static org.exparity.expectamundo.Expactamundo.verify;
import static org.exparity.stub.random.RandomBuilder.aRandomString;

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

}