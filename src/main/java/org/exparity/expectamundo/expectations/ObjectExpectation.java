
package org.exparity.expectamundo.expectations;

import org.hamcrest.Matcher;

/**
 * Matching operations available to all property types
 */
public interface ObjectExpectation {

	/**
	 * Verify the property is equal to the expected value
	 */
	public <T> void equalTo(final T expectedValue);

	/**
	 * Verify the property matches the hamcrest {@link Matcher}
	 */
	public <T> void matches(final Matcher<T> expected);

	/**
	 * Verify the property has a null value
	 */
	public void nullValue();

	/**
	 * Verify the property has a non-null value
	 */
	public void notNullValue();

}