
package org.exparity.expectamundo.core;

import org.exparity.expectamundo.expectations.ContainsExpectation;
import org.exparity.expectamundo.expectations.IsEmptyExpectation;

/**
 * @author Stewart Bissett
 */
public class PrototypeCollectionExpectation<E> extends PrototypeObjectExpectation {

	public PrototypeCollectionExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	public void isEmpty() {
		setExpectation(new IsEmptyExpectation());
	}

	public void contains(final E element) {
		setExpectation(new ContainsExpectation<E>(element));
	}
}
