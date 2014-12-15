eXpectamundo  [![Build Status](https://travis-ci.org/eXparity/expectamundo.svg?branch=master)](https://travis-ci.org/eXparity/expectamundo) [![Coverage Status](https://coveralls.io/repos/eXparity/expectamundo/badge.png?branch=master)](https://coveralls.io/r/eXparity/expectamundo?branch=master)
=============

A Java library for creating a prototype object with expected values and verifying the actual object has matching values

Licensed under [BSD License][].

What is eXpectamundo?
-----------------
eXpectamundo is a test library to facilitate testing the properties of any Java object to ensure they meet expectations. The expectations are setup by creating a prototype instance of the type you are expecting and defining the expecations on the prototype instance.  

Usage
-------------

Given a simple class as defined below:

    class SimpleType {
     private final List<String> value  = Arrays.asList("eXpectamundo lets me test this");
     public List<String> getValue() {
       return value;
     }
    }

You can set up a test to verify the expected outcome as follows:

    public class SimpleTypeTest {
    	@Test
    	public void canReturnTheCorrectValue() {
    	  SimpleType expected = Expectamundo.prototype(SimpleType.class);
    	  Expectamundo.expect(expected.getValue().get(0)).isEqualTo("eXpectamundo lets me test this");
    	  SimpleType actual = new SimpleType();
    	  Expectamundo.verify(actual).matches(expected);
      }
    }

This example captures the basics of what you can do with eXpectamundo. eXpectamundo allows you to set expectations on any non-final type or property on a object which returns a value.


Downloads
---------
You can obtain the expectamundo jar from [maven central][]. To include your project in:

A maven project

    <dependency>
        <groupId>org.exparity</groupId>
        <artifactId>expectamundo</artifactId>
        <version>0.9.1</version>
    </dependency>

A project which uses ivy for dependency management

    <dependency org="org.exparity" name="expectamundo" rev="0.9.1"/>
            
Acknowledgements
----------------
Developers:
  * Stewart Bissett
  * Simon Birt


[BSD License]: http://opensource.org/licenses/BSD-3-Clause
[Maven central]: http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22expectamundo%22
