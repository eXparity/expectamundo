
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.core.expectations.HasLength;
import org.exparity.expectamundo.core.expectations.HasPattern;

/**
 * @author Stewart Bissett
 */
public class PrototypeStringExpectation extends PrototypeComparableExpectation<String> {

	public PrototypeStringExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	public void hasLength(final int expectedSize) {
		hasExpectation(new HasLength(expectedSize));
	}

	public void hasPattern(final String pattern) {
		hasExpectation(new HasPattern(pattern));
	}
}
