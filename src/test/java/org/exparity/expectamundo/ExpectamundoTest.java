package org.exparity.expectamundo;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.exparity.expectamundo.Expectamundo.cast;
import static org.exparity.expectamundo.Expectamundo.checkThat;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.exparity.expectamundo.Expectamundo.isPrototype;
import static org.exparity.expectamundo.Expectamundo.matchesPrototype;
import static org.exparity.expectamundo.Expectamundo.prototype;
import static org.exparity.stub.random.RandomBuilder.aRandomByteArray;
import static org.exparity.stub.random.RandomBuilder.aRandomInteger;
import static org.exparity.stub.random.RandomBuilder.aRandomString;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.exparity.expectamundo.core.PrototypeMatcherContext;
import org.exparity.expectamundo.core.TypeReference;
import org.exparity.expectamundo.testutils.types.GraphListReturnType;
import org.exparity.expectamundo.testutils.types.GraphType;
import org.exparity.expectamundo.testutils.types.HashCodeType;
import org.exparity.expectamundo.testutils.types.ListReturnType;
import org.exparity.expectamundo.testutils.types.MapReturnType;
import org.exparity.expectamundo.testutils.types.ParameterizedListReturnType;
import org.exparity.expectamundo.testutils.types.PolymorphicReturnType;
import org.exparity.expectamundo.testutils.types.PolymorphicSubtype1;
import org.exparity.expectamundo.testutils.types.PolymorphicSubtype2;
import org.exparity.expectamundo.testutils.types.PrimitiveArrayType;
import org.exparity.expectamundo.testutils.types.PrimitiveType;
import org.exparity.expectamundo.testutils.types.SimpleType;
import org.exparity.expectamundo.testutils.types.StringListReturnType;
import org.exparity.expectamundo.testutils.types.ToStringType;
import org.exparity.stub.random.RandomBuilder;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * @author Stewart Bissett
 */
public class ExpectamundoTest {

	@Test
	public void canMatchSimpleProperty() {
		final String expectedValue = aRandomString(5);
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expectThat(new SimpleType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailSimpleProperty() {
		final String expectedValue = aRandomString(5), differentValue = expectedValue + aRandomString(5);
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expectThat(new SimpleType(differentValue)).matches(expected);
	}

	@Test
	public void canMatchPrimitiveProperty() {
		final int expectedValue = aRandomInteger();
		PrimitiveType expected = prototype(PrimitiveType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expectThat(new PrimitiveType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailPrimitiveProperty() {
		final int expectedValue = aRandomInteger(), differentValue = expectedValue + aRandomInteger();
		PrimitiveType expected = prototype(PrimitiveType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expectThat(new PrimitiveType(differentValue)).matches(expected);
	}

	@Test
	public void canMatchPrimitiveArrayProperty() {
		final byte[] expectedValue = aRandomByteArray();
		PrimitiveArrayType expected = prototype(PrimitiveArrayType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expectThat(new PrimitiveArrayType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailPrimitiveArrayProperty() {
		final byte[] expectedValue = aRandomByteArray(), differentValue = aRandomByteArray();
		PrimitiveArrayType expected = prototype(PrimitiveArrayType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expectThat(new PrimitiveArrayType(differentValue)).matches(expected);
	}

	@Test
	public void canMatchObjectProperty() {
		final SimpleType expectedValue = new SimpleType(aRandomString(5));
		GraphType expected = prototype(GraphType.class);
		expect(expected.getChild()).isEqualTo(expectedValue);
		expectThat(new GraphType(aRandomString(5), expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailObjectProperty() {
		final SimpleType expectedValue = new SimpleType(aRandomString(5)), differentValue = new SimpleType(
				aRandomString(5));
		GraphType expected = prototype(GraphType.class);
		expect(expected.getChild()).isEqualTo(expectedValue);
		expectThat(new GraphType(aRandomString(5), differentValue)).matches(expected);
	}

	@Test
	public void canMatchToStringProperty() {
		final String expectedValue = aRandomString(5);
		ToStringType expected = prototype(ToStringType.class);
		expect(expected.toString()).isEqualTo(expectedValue);
		expectThat(new ToStringType(expectedValue)).matches(expected);
	}

	@Test
	public void canMatchHashCodeProperty() {
		final int expectedValue = RandomBuilder.aRandomInteger();
		HashCodeType expected = prototype(HashCodeType.class);
		expect(expected.hashCode()).isEqualTo(expectedValue);
		expectThat(new HashCodeType(expectedValue)).matches(expected);
	}

	@Test
	public void canMatchGraphProperties() {
		final String expectedValue = aRandomString(5);
		GraphType expected = prototype(GraphType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expect(expected.getChild().getValue()).isEqualTo(expectedValue);
		expectThat(new GraphType(expectedValue, new SimpleType(expectedValue))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailGraphProperty() {
		final String expectedValue = aRandomString(5), differentValue = expectedValue + aRandomString(5);
		GraphType expected = prototype(GraphType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expect(expected.getChild().getValue()).isEqualTo(expectedValue);
		expectThat(new GraphType(expectedValue, new SimpleType(differentValue))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailNullGraphProperty() {
		final String expectedValue = aRandomString(5), differentValue = expectedValue + aRandomString(5);
		GraphType expected = prototype(GraphType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expect(expected.getChild().getValue()).isEqualTo(expectedValue);
		expectThat(new GraphType(differentValue, null)).matches(expected);
	}

	@Test
	public void canMatchGenericSubclass() {
		final String expectedValue = aRandomString(5);
		StringListReturnType expected = prototype(StringListReturnType.class);
		expect(expected.getValue().get(0)).isEqualTo(expectedValue);
		expectThat(new StringListReturnType(Arrays.asList(expectedValue))).matches(expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void canThrowExceptionIfPrototypingGenericType() {
		prototype(ParameterizedListReturnType.class);
	}

	@Test
	public void canMatchGenericProperties() {
		final String expectedValue = aRandomString(5);
		List<String> expected = prototype(new TypeReference<List<String>>() {});
		expect(expected.get(0)).isEqualTo(expectedValue);
		List<String> actual = Arrays.asList(expectedValue);
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailGenericProperties() {
		final String expectedValue = aRandomString(5), differentValue = expectedValue + aRandomString(5);
		List<String> expected = prototype(new TypeReference<List<String>>() {});
		expect(expected.get(0)).isEqualTo(expectedValue);
		List<String> actual = Arrays.asList(differentValue);
		expectThat(actual).matches(expected);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(expected = IllegalArgumentException.class)
	public void canFailGenericPropertiesIfTypeNotPassed() {
		prototype(new TypeReference() {});
	}

	@Test
	public void canMatchSimpleListProperty() {
		final List<String> expectedValue = Arrays.asList(aRandomString(5));
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue().get(0)).isEqualTo(expectedValue.get(0));
		expectThat(new ListReturnType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailSimpleListProperty() {
		final String expectedString = aRandomString(5);
		final List<String> expectedValue = Arrays.asList(expectedString), differentValue = Arrays
				.asList(expectedString + aRandomString(5));
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue().get(0)).isEqualTo(expectedValue.get(0));
		expectThat(new ListReturnType(differentValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfSimpleListPropertySmaller() {
		final String expectedString = aRandomString(5);
		final List<String> expectedValue = Arrays.asList(expectedString);
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue().get(0)).isEqualTo(expectedString);
		expect(expected.getValue().get(1)).isEqualTo(expectedString);
		expectThat(new ListReturnType(expectedValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfSimpleListPropertyEmpty() {
		final String expectedString = aRandomString(5);
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue().get(0)).isEqualTo(expectedString);
		expect(expected.getValue().get(1)).isEqualTo(expectedString);
		List<String> actualValue = new ArrayList<>();
		expectThat(new ListReturnType(actualValue)).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfSimpleListPropertyNull() {
		final String expectedString = aRandomString(5);
		ListReturnType expected = prototype(ListReturnType.class);
		expect(expected.getValue().get(0)).isEqualTo(expectedString);
		expect(expected.getValue().get(1)).isEqualTo(expectedString);
		List<String> actualValue = null;
		expectThat(new ListReturnType(actualValue)).matches(expected);
	}

	@Test
	public void canMatchGraphListProperty() {
		final String expectedValue = aRandomString(5);
		GraphListReturnType expected = prototype(GraphListReturnType.class);
		expect(expected.getValue().get(0).getValue()).isEqualTo(expectedValue);
		expect(expected.getValue().get(0).getChild().getValue()).isEqualTo(expectedValue);
		GraphListReturnType actual = new GraphListReturnType(
				new GraphType(expectedValue, new SimpleType(expectedValue)));
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailGraphListProperty() {
		final String expectedValue = aRandomString(5), otherValue = expectedValue + expectedValue;
		GraphListReturnType expected = prototype(GraphListReturnType.class);
		expect(expected.getValue().get(0).getValue()).isEqualTo(expectedValue);
		expect(expected.getValue().get(0).getChild().getValue()).isEqualTo(expectedValue);
		GraphListReturnType actual = new GraphListReturnType(new GraphType(otherValue, new SimpleType(otherValue)));
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfGraphListPropertySmaller() {
		final String expectedValue = aRandomString(5), expectedValue2 = expectedValue + expectedValue;
		GraphListReturnType expected = prototype(GraphListReturnType.class);
		expect(expected.getValue().get(0).getValue()).isEqualTo(expectedValue);
		expect(expected.getValue().get(0).getChild().getValue()).isEqualTo(expectedValue);
		expect(expected.getValue().get(1).getValue()).isEqualTo(expectedValue2);
		expect(expected.getValue().get(1).getChild().getValue()).isEqualTo(expectedValue2);
		GraphListReturnType actual = new GraphListReturnType(
				new GraphType(expectedValue, new SimpleType(expectedValue)));
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfGraphListPropertyEmpty() {
		final String expectedValue = aRandomString(5);
		GraphListReturnType expected = prototype(GraphListReturnType.class);
		expect(expected.getValue().get(0).getValue()).isEqualTo(expectedValue);
		expect(expected.getValue().get(0).getChild().getValue()).isEqualTo(expectedValue);
		GraphListReturnType actual = new GraphListReturnType();
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfGraphListPropertyNull() {
		final String expectedValue = aRandomString(5);
		GraphListReturnType expected = prototype(GraphListReturnType.class);
		expect(expected.getValue().get(0).getValue()).isEqualTo(expectedValue);
		expect(expected.getValue().get(0).getChild().getValue()).isEqualTo(expectedValue);
		GraphListReturnType actual = null;
		expectThat(actual).matches(expected);
	}

	@Test
	public void canMatchParameterizedListProperty() {
		final String expectedValue = aRandomString(5);
		ParameterizedListReturnType<String> expected = prototype(new TypeReference<ParameterizedListReturnType<String>>() {});
		expect(expected.getValue().get(0)).isEqualTo(expectedValue);
		ParameterizedListReturnType<String> actual = new ParameterizedListReturnType<String>(expectedValue);
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailParameterizedListProperty() {
		final String expectedValue = aRandomString(5), otherValue = expectedValue + expectedValue;
		ParameterizedListReturnType<String> expected = prototype(new TypeReference<ParameterizedListReturnType<String>>() {});
		expect(expected.getValue().get(0)).isEqualTo(expectedValue);
		ParameterizedListReturnType<String> actual = new ParameterizedListReturnType<String>(otherValue);
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfParameterizedListPropertySmaller() {
		final String expectedValue = aRandomString(5), expectedValue2 = expectedValue + expectedValue;
		ParameterizedListReturnType<String> expected = prototype(new TypeReference<ParameterizedListReturnType<String>>() {});
		expect(expected.getValue().get(0)).isEqualTo(expectedValue);
		expect(expected.getValue().get(1)).isEqualTo(expectedValue2);
		ParameterizedListReturnType<String> actual = new ParameterizedListReturnType<String>(expectedValue);
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfParameterizedListPropertyEmpty() {
		final String expectedValue = aRandomString(5);
		ParameterizedListReturnType<String> expected = prototype(new TypeReference<ParameterizedListReturnType<String>>() {});
		expect(expected.getValue().get(0)).isEqualTo(expectedValue);
		ParameterizedListReturnType<String> actual = new ParameterizedListReturnType<String>();
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfParameterizedListPropertyNull() {
		final String expectedValue = aRandomString(5);
		ParameterizedListReturnType<String> expected = prototype(new TypeReference<ParameterizedListReturnType<String>>() {});
		expect(expected.getValue().get(0)).isEqualTo(expectedValue);
		ParameterizedListReturnType<String> actual = null;
		expectThat(actual).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfListOfObjectsSmaller() {
		final String expectedString = aRandomString(5), secondString = aRandomString(5);
		final List<SimpleType> expectedList = Arrays.asList(new SimpleType(expectedString));
		final List<SimpleType> expected = prototype(new TypeReference<List<SimpleType>>() {});
		expect(expected.get(0).getValue()).isEqualTo(expectedString);
		expect(expected.get(1).getValue()).isEqualTo(secondString);
		expectThat(expectedList).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfListOfObjectsEmpty() {
		final String expectedString = aRandomString(5), secondString = aRandomString(5);
		final List<SimpleType> expected = prototype(new TypeReference<List<SimpleType>>() {});
		expect(expected.get(0).getValue()).isEqualTo(expectedString);
		expect(expected.get(1).getValue()).isEqualTo(secondString);
		List<SimpleType> actualList = new ArrayList<>();
		expectThat(actualList).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfListOfGraphObjectsEmpty() {
		String expectedString = aRandomString(5), secondString = aRandomString(5);
		List<GraphType> expected = prototype(new TypeReference<List<GraphType>>() {});
		expect(expected.get(0).getValue()).isEqualTo(expectedString);
		expect(expected.get(0).getChild().getValue()).isEqualTo(expectedString);
		expect(expected.get(1).getValue()).isEqualTo(secondString);
		expect(expected.get(1).getChild().getValue()).isEqualTo(secondString);
		List<GraphType> actualList = new ArrayList<>();
		expectThat(actualList).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailIfListOfGraphObjectsSmaller() {
		final String expectedString = aRandomString(5), secondString = aRandomString(5);
		final List<GraphType> expected = prototype(new TypeReference<List<GraphType>>() {});
		expect(expected.get(0).getValue()).isEqualTo(expectedString);
		expect(expected.get(0).getChild().getValue()).isEqualTo(expectedString);
		expect(expected.get(1).getValue()).isEqualTo(secondString);
		expect(expected.get(1).getChild().getValue()).isEqualTo(secondString);
		List<GraphType> actualList = asList(new GraphType(expectedString, new SimpleType(expectedString)));
		expectThat(actualList).matches(expected);
	}

	@Test
	public void canMatchMapProperties() {
		String key = aRandomString(5), value = aRandomString(5);
		MapReturnType expected = prototype(MapReturnType.class);
		expect(expected.getValue().get(key)).isEqualTo(value);
		expectThat(new MapReturnType(Collections.singletonMap(key, value))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailMapPropertiesBadKey() {
		String key = aRandomString(5), value = aRandomString(5), aDifferentKey = aRandomString(5);
		MapReturnType expected = prototype(MapReturnType.class);
		expect(expected.getValue().get(key)).isEqualTo(value);
		expectThat(new MapReturnType(singletonMap(aDifferentKey, value))).matches(expected);
	}

	@Test(expected = AssertionError.class)
	public void canFailMapPropertiesBadValue() {
		String key = aRandomString(5), value = aRandomString(5), aDifferentValue = aRandomString(5);
		MapReturnType expected = prototype(MapReturnType.class);
		expect(expected.getValue().get(key)).isEqualTo(value);
		expectThat(new MapReturnType(singletonMap(key, aDifferentValue))).matches(expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailIfSettingExpectationOnNormalInstance() {
		PrototypeMatcherContext.setCurrentPrototype(null);
		final String expectedValue = aRandomString(5);
		SimpleType expected = new SimpleType(expectedValue);
		expect(expected.getValue()).isEqualTo(expectedValue);
	}

	@Test
	public void canMatchUsingHamcrest() {
		String expectedValue = aRandomString(5);
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		SimpleType actual = new SimpleType(expectedValue);
		MatcherAssert.assertThat(actual, matchesPrototype(expected));
	}

	@Test(expected = AssertionError.class)
	public void canFailUsingHamcrest() {
		String expectedValue = aRandomString(5), differentValue = expectedValue + aRandomString(5);
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		SimpleType actual = new SimpleType(differentValue);
		MatcherAssert.assertThat(actual, matchesPrototype(expected));
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailUsingHamcrestOnNormalInstance() {
		SimpleType actual = new SimpleType(aRandomString(5));
		MatcherAssert.assertThat(actual, matchesPrototype(new SimpleType(aRandomString(5))));
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailIfVerifyingExpectationOnNormalInstance() {
		SimpleType expected = new SimpleType(aRandomString(5));
		expectThat(expected).matches(expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailToMatchPrimitiveType() {
		prototype(byte.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void canFailIfPropertyExpectationNotSet() {
		SimpleType actual = new SimpleType(aRandomString(5));
		expect(actual).isNull();
		expectThat(actual).matches(new SimpleType(aRandomString(5)));
	}

	@Test
	public void canMatchIfPrototypeOverSeveralLines() {
		String expectedValue = aRandomString(5);
		GraphType expected = prototype(GraphType.class);
		SimpleType expectedProperty = expected.getChild();
		expect(expectedProperty.getValue()).isEqualTo(expectedValue);
		expectThat(new GraphType(expectedValue, new SimpleType(expectedValue))).matches(expected);
	}

	@Test
	public void canMatchIfDifferentPrototypeOverSeveralLines() {
		String expectedValue = aRandomString(5);
		GraphType expected = prototype(GraphType.class);
		SimpleType expectedProperty = prototype(SimpleType.class);
		expect(expected.getChild().getValue()).isEqualTo(expectedValue);
		expect(expectedProperty.getValue()).isEqualTo(expectedValue);
		expect(expected.getValue()).isEqualTo(expectedValue);
		expectThat(new GraphType(expectedValue, new SimpleType(expectedValue))).matches(expected);
		expectThat(new SimpleType(expectedValue)).matches(expectedProperty);
	}

	@Test
	public void canMatchPolymorphicTypes() {
		Integer expectedValue = aRandomInteger();
		PolymorphicReturnType expected = prototype(PolymorphicReturnType.class);
		expect(cast(expected.getValue(), PolymorphicSubtype1.class).getValue()).isEqualTo(expectedValue);
		expectThat(new PolymorphicReturnType(new PolymorphicSubtype1(expectedValue))).matches(expected);
	}

	@Test(expected = ClassCastException.class)
	public void canFailIfPolymorphicTypes() {
		Integer expectedValue = aRandomInteger();
		String differnentValue = aRandomString(5);
		PolymorphicReturnType expected = prototype(PolymorphicReturnType.class);
		expect(cast(expected.getValue(), PolymorphicSubtype1.class).getValue()).isEqualTo(expectedValue);
		expectThat(new PolymorphicReturnType(new PolymorphicSubtype2(differnentValue))).matches(expected);
	}

	@Test
	public void canVerifySimpleProperty() {
		final String expectedValue = aRandomString(5);
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		assertEquals(checkThat(new SimpleType(expectedValue)).matches(expected), true);
	}

	@Test
	public void canFailVerificationOfSimpleProperty() {
		final String expectedValue = aRandomString(5), differentValue = expectedValue + aRandomString(5);
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(expectedValue);
		assertEquals(checkThat(new SimpleType(differentValue)).matches(expected), false);
	}

	@Test
	public void canTestForAPrototype() {
		assertEquals(isPrototype(prototype(SimpleType.class)), true);
	}

	@Test
	public void canTestForNotAPrototype() {
		assertEquals(isPrototype(new SimpleType(aRandomString(5))), false);
	}

	@Test
	public void canTestForExpectationInACollection() {
		String value1 = aRandomString(5), value2 = value1 + aRandomString(5);
		List<SimpleType> listOfTypes = Arrays.asList(new SimpleType(value1), new SimpleType(value2));
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(value1);
		expectThat(listOfTypes).contains(expected);
	}

	@Test(expected = AssertionError.class)
	public void canTestForExpectationNotInACollection() {
		String value1 = aRandomString(5), value2 = value1 + aRandomString(2), value3 = value2 + aRandomString(3);
		List<SimpleType> listOfTypes = Arrays.asList(new SimpleType(value1), new SimpleType(value2));
		SimpleType expected = prototype(SimpleType.class);
		expect(expected.getValue()).isEqualTo(value3);
		expectThat(listOfTypes).contains(expected);
	}

	@Test
	public void canMatchAPrototype() {
		String value = aRandomString();
		List<String> expected = prototype(new TypeReference<List<String>>() {});
		expect(expected).contains(value);
		expectThat(Arrays.asList(value)).matches(expected);
	}
}
