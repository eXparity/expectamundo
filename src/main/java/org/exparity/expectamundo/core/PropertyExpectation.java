
package org.exparity.expectamundo.core;

/**
 * An expectation for the value of a property.
 * 
 * @author Stewart Bissett
 */
public interface PropertyExpectation<T> {

	/**
	 * Check if this expectation is met
	 */
	public boolean matches(final T actual);

	/**
	 * Return a description of expectation
	 */
	public String describe();

}
