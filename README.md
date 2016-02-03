eXpectamundo  [![Build Status](https://travis-ci.org/eXparity/expectamundo.svg?branch=master)](https://travis-ci.org/eXparity/expectamundo) [![Coverage Status](https://coveralls.io/repos/eXparity/expectamundo/badge.png?branch=master)](https://coveralls.io/r/eXparity/expectamundo?branch=master) [![Maven Central](https://img.shields.io/maven-central/v/org.exparity/expectamundo.svg)](https://img.shields.io/maven-central/v/org.exparity/expectamundo.svg)
=============

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.jcandksolutions.gradle/android-unit-test/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.jcandksolutions.gradle/android-unit-test)


A Java library for creating a prototype object with expected values and verifying the actual object has matching values

Licensed under [BSD License][].

What is eXpectamundo?
-----------------
eXpectamundo is a test library to facilitate testing the properties of any Java object to ensure they meet expectations. The expectations are setup by creating a prototype instance of the type you are expecting and defining the expecations on the prototype instance.  

Downloads
---------
You can obtain the expectamundo jar from [maven central][]. To include your project in:

A maven project

    <dependency>
        <groupId>org.exparity</groupId>
        <artifactId>expectamundo</artifactId>
        <version>0.9.20<version>
    </dependency>

Usage
-------------

Given a simple class as defined below:

    class SimpleType {
    
     private final String firstName, surname;
     private final List<String> values;
     
     public SimpleType(String firstName, String surname, String ... values) {
       this.firstName = firstName;
       this.surname = surname;
       this.values = Arrays.asList(values);
     }
       
     public List<String> getValues() { return values; }
     public String getFirstName() { return firstName; }
     public String getSurname() { return surname; }
     
    }

You can set up a test to verify the expected outcome as follows:

    public class SimpleTypeTest {
		@Test
		public void canReturnTheCorrectValue() {
			SimpleType expected = Expectamundo.prototype(SimpleType.class);
			Expectamundo.expect(expected.getFirstName()).isEqualTo("Jane");
			Expectamundo.expect(expected.getSurname()).isEqualTo("Smith");
			Expectamundo.expect(expected.getValues()).contains("eXpectamundo lets me test this");
			SimpleType actual = new SimpleType("Jane", "Smith", "eXpectamundo lets me test this");
			Expectamundo.expectThat(actual).matches(expected);
		}
    }

You can also set up a test to verify the prototype directly, for example setting expectations on a list as follows:

    public class SimpleTypeTest {
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

These examples capture the basics of what you can do with eXpectamundo. eXpectamundo allows you to set expectations on any non-final type or property on a object which returns a value.

The library includes expectations for all Object property types:

* __isEqualTo__ - Set the expectation that the property value should be equal to an explicit value
* __isNotEqualTo__ - Set the expectation that the property value should not be equal to an explicit value
* __isNull__ - Set the expectation that the property should be null
* __isNotNull__ - Set the expectation that the property should not be null
* __matches__ - Set the expectation that the property matches a Hamcrest matcher
* __isInstanceOf__ - Set the expectation that the property is an instance of a type
* __isOneOf__ - Set the expectation that the property one of a number of values

The library includes expectations for Collection properties:

* __contains__ - Set the expectation that the collection property contains an object which is equal to the explicit value
* __containsExpected__ - Set the expectation that the collection property contains an a prototype with the defined expectations
* __isEmpty__ - Set the expectation that the collection property is empty
* __isNotEmpty__ - Set the expectation that the collection property is not empty
* __hasSize__ - Set the expectation that the collection property is of an explicit size

The libary includes expectations for array properties:

* __contains__ - Set the expectation that the collection property contains an object which is equal to the explicit value
* __isEmpty__ - Set the expectation that the collection property is empty
* __isNotEmpty__ - Set the expectation that the collection property is not empty
* __hasSize__ - Set the expectation that the collection property is of an explicit size

The libary includes expectations for Comparable properties

* __isComparableTo__ - Set the expectation that the colllection property is comparable to an explicit value

The libary includes expectations for String properties

* __hasPattern__ - Set the expectation that the String matches the regular expression
* __hasLength__ - Set the expectation that the String is of the given length
* __isEqualToIgnoreCase__ - Set the expectation that the String is the same as another regardless of case

Contributions are welcome to extend the list of expectations to match types.

Source
------
The source is structured along the lines of the maven standard folder structure for a jar project.

  * Core classes [src/main/java]
  * Unit tests [src/test/java]

The source includes a pom.xml for building with Maven 

Release Notes
-------------

Changes 0.9.18 -> 0.9.20
  * Fix issue with expectations on BigDecimal and BigInteger

Changes 0.9.16 -> 0.9.18
  * Handle IndexOutOfBounds and NullPointerException by returning a null proxy to improve error printing

Changes 0.9.15 -> 0.9.16
  * Catch IndexOutOfBounds

Changes 0.9.11 -> 0.9.15
  * Add IsEqualsIgnoreCase to Strings
  * Add IsSameDay to Date  
  * Add ContainsExpected to collections
  * Improve handling of array index out of range  

Changes 0.9.10 -> 0.9.11
  * IsEqualTo can check expected null == null (Issue #3)

Changes 0.9.9 -> 0.9.10
  * Include arguments as strings when printing assertions
  * Add line break after mismatching property in assertion

Changes 0.9.3 -> 0.9.4
  * Add support for casting down to subtypes

Changes 0.9.2 -> 0.9.3
  * Expand expectation options

Changes 0.9.1 -> 0.9.2
  * Correct typo of expactomundo in package name

Changes 0.9.0 -> 0.9.0
  * Introduce static Expectamundo class
            
Acknowledgements
----------------
Developers:
  * Stewart Bissett
  * Simon Birt


[BSD License]: http://opensource.org/licenses/BSD-3-Clause
[Maven central]: http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22expectamundo%22
