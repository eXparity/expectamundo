
package org.exparity.expectamundo.core.collection;

import java.util.Collection;
import org.exparity.expectamundo.Expectamundo;
import org.exparity.expectamundo.core.Prototype;
import org.exparity.expectamundo.core.PrototypeProperty;
import org.exparity.expectamundo.core.PrototypeValue;
import org.exparity.expectamundo.core.object.PrototypeObjectExpectation;

/**
 * @author Stewart Bissett
 */
public class PrototypeCollectionExpectation<E, T extends Collection<E>> extends PrototypeObjectExpectation<T> {

	public PrototypeCollectionExpectation(final Prototype<?> prototype, final PrototypeValue property) {
		super(prototype, property);
	}

	/**
	 * Set an expectation that the collection is empty. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.myCollection()).isEmpty();
	 * expectThat(actual).matches(expected);
	 * </pre>
	 */
	public void isEmpty() {
		hasExpectation(new IsEmpty<E, T>());
	}

	/**
	 * Set an expectation that the collection is not empty. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.myCollection()).isNotEmpty();
	 * expectThat(actual).matches(expected);
	 * </pre>
	 */
	public void isNotEmpty() {
		hasExpectation(new IsNotEmpty<E, T>());
	}

	/**
	 * Set an expectation that the collection is of the given size. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.myCollection()).hasSize(1);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param size the expected size of the collection
	 */
	public void hasSize(final int size) {
		hasExpectation(new HasSize<T>(size));
	}

	/**
	 * Set an expectation that the collection contains an item. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.myCollection()).contains(&quot;ABCD&quot;);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param element the item to find in the collection
	 */
	public void contains(final E element) {
		if (Expectamundo.isPrototype(element)) {
			containsExpected(element);
		} else {
			hasExpectation(new Contains<E, T>(element));
		}
	}

	/**
	 * Set an expectation that the collection contains an item which matches the expectation. For example</p>
	 * 
	 * <pre>
	 * MyChild child = prototype(MyChild.class);
	 * expect(child.getName()).isEqualTo(&quot;Bob&quot;);
	 * 
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.myCollection()).containsExpected(child);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param element the expectation to find in the collection
	 */
	public void containsExpected(final E element) {
		hasExpectation(new ContainsExpected<E, T>(element));
	}
}
