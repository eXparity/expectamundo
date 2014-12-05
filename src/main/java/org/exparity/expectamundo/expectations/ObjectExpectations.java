package org.exparity.expectamundo.expectations;

import org.hamcrest.Matcher;

/**
 * Matching operations available to all property type
 */
public interface ObjectExpectations<T> {

	/**
	 * Verify the property is equal to the expected value
	 */
	public void equalTo(final T expectedValue);

	/**
	 * Verify the property matches the expected matcher
	 */
	public void matches(final Matcher<T> expected);

	/**
	 * Verify the property has a null value
	 */
	public void nullValue();

	/**
	 * Verify the property has a non-null value
	 */
	public void notNullValue();

}