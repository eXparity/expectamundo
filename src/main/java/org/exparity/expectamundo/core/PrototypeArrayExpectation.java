
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.core.expectations.ContainsInArray;
import org.exparity.expectamundo.core.expectations.HasLengthOfArray;
import org.exparity.expectamundo.core.expectations.IsEmptyArray;
import org.exparity.expectamundo.core.expectations.IsNotEmptyArray;

/**
 * @author Stewart Bissett
 */
public class PrototypeArrayExpectation<T> extends PrototypeObjectExpectation<T[]> {

	public PrototypeArrayExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	public void isEmpty() {
		hasExpectation(new IsEmptyArray<T>());
	}

	public void isNotEmpty() {
		hasExpectation(new IsNotEmptyArray<T>());
	}

	public void hasLength(final int size) {
		hasExpectation(new HasLengthOfArray<T>(size));
	}

	public void contains(final T element) {
		hasExpectation(new ContainsInArray<T>(element));
	}

}
