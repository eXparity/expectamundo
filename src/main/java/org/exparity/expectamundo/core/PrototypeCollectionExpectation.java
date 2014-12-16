
package org.exparity.expectamundo.core;

import java.util.Collection;
import org.exparity.expectamundo.expectations.Contains;
import org.exparity.expectamundo.expectations.HasSize;
import org.exparity.expectamundo.expectations.IsEmpty;
import org.exparity.expectamundo.expectations.IsNotEmpty;

/**
 * @author Stewart Bissett
 */
public class PrototypeCollectionExpectation<E, T extends Collection<E>> extends PrototypeObjectExpectation {

	public PrototypeCollectionExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		super(prototype, property);
	}

	public void isEmpty() {
		setExpectation(new IsEmpty<E, T>());
	}

	public void isNotEmpty() {
		setExpectation(new IsNotEmpty<E, T>());
	}

	public void hasSize(final int size) {
		setExpectation(new HasSize<T>(size));
	}

	public void contains(final E element) {
		setExpectation(new Contains<E, T>(element));
	}

}
