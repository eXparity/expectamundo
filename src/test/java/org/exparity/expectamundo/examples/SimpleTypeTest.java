package org.exparity.expectamundo.examples;

import java.util.Arrays;
import java.util.List;

import org.exparity.expectamundo.Expectamundo;
import org.junit.Test;

public class SimpleTypeTest {

	class SimpleType {

		private final String firstName, surname;
		private final List<String> values;

		public SimpleType(final String firstName, final String surname, final String... values) {
			this.firstName = firstName;
			this.surname = surname;
			this.values = Arrays.asList(values);
		}

		public List<String> getValues() {
			return values;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getSurname() {
			return surname;
		}

	}

	@Test
	public void canReturnTheCorrectValue() {
		SimpleType expected = Expectamundo.prototype(SimpleType.class);
		Expectamundo.expect(expected.getFirstName()).isEqualTo("Jane");
		Expectamundo.expect(expected.getSurname()).isEqualTo("Smith");
		Expectamundo.expect(expected.getValues()).contains("eXpectamundo lets me test this");
		SimpleType actual = new SimpleType("Jane", "Smith", "eXpectamundo lets me test this");
		Expectamundo.expectThat(actual).matches(expected);
	}

	@Test
	public void canMatchPrototypeInList() {
		SimpleType expected = Expectamundo.prototype(SimpleType.class);
		Expectamundo.expect(expected.getFirstName()).isEqualTo("Jane");
		Expectamundo.expect(expected.getSurname()).isEqualTo("Smith");
		Expectamundo.expect(expected.getValues()).contains("eXpectamundo lets me test this");
		SimpleType actual = new SimpleType("Jane", "Smith", "eXpectamundo lets me test this");
		List<SimpleType> actualList = Arrays.asList(actual);
		Expectamundo.expectThat(actualList).contains(expected);
	}

}
