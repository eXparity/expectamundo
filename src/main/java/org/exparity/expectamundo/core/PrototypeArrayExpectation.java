
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.expectations.ContainsInArray;
import org.exparity.expectamundo.expectations.HasLengthOfArray;
import org.exparity.expectamundo.expectations.IsEmptyArray;
import org.exparity.expectamundo.expectations.IsNotEmptyArray;

/**
 * @author Stewart Bissett
 */
public class PrototypeArrayExpectation<T> extends PrototypeObjectExpectation {

	public PrototypeArrayExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	public void isEmpty() {
		setExpectation(new IsEmptyArray<T>());
	}

	public void isNotEmpty() {
		setExpectation(new IsNotEmptyArray<T>());
	}

	public void hasLength(final int size) {
		setExpectation(new HasLengthOfArray<T>(size));
	}

	public void contains(final T element) {
		setExpectation(new ContainsInArray<T>(element));
	}

}
