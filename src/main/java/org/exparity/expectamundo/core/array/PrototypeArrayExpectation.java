
package org.exparity.expectamundo.core.array;

import org.exparity.expectamundo.core.Prototype;
import org.exparity.expectamundo.core.PrototypeProperty;
import org.exparity.expectamundo.core.object.PrototypeObjectExpectation;

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
