
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.expectations.HasLength;
import org.exparity.expectamundo.expectations.HasPattern;

/**
 * @author Stewart Bissett
 */
public class PrototypeStringExpectation extends PrototypeComparableExpectation<String> {

	public PrototypeStringExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	public void hasLength(final int expectedSize) {
		setExpectation(new HasLength(expectedSize));
	}

	public void hasPattern(final String pattern) {
		setExpectation(new HasPattern(pattern));
	}
}
