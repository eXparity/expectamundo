
package org.exparity.expectamundo;

import java.util.Collection;
import org.exparity.expectamundo.core.PrototypeCollectionExpectation;
import org.exparity.expectamundo.core.PrototypeComparableExpectation;
import org.exparity.expectamundo.core.PrototypeFactory;
import org.exparity.expectamundo.core.PrototypeObjectExpectation;
import org.exparity.expectamundo.core.PrototypeVerifier;
import org.exparity.expectamundo.core.Prototyped;
import org.exparity.expectamundo.core.TypeReference;
import org.exparity.expectamundo.hamcrest.PrototypeMatcher;
import org.hamcrest.Matcher;
import static org.exparity.expectamundo.core.PrototypeMatcherContext.currentPrototype;

/**
 * Expectamundo is a test library for preparing a prototype instance of a non-final type containing expected values against which actual instances can be verified against. For
 * example:</p> <h5>To verifying object properties</h5>
 * 
 * <pre>
 * Person expected = Expectamundo.prototype(Person.class);
 * Expectamundo.expect(expected.getFirstName()).isEqualTo("Jane");
 * Expectamundo.expect(expected.getLastName()).isEqualTo("Doe");
 * Expectamundo.verify(new Person("Jane","Doe")).matches(expected));
 * </pre>
 * 
 * <h5>To verifying list properties</h5>
 * 
 * <pre>
 * Person expected = Expectamundo.prototype(Person.class);
 * Expectamundo.expect(expected.getSiblings().get(0).getFirstName()).isEqualTo("John");
 * Expectamundo.expect(expected.getSiblings().get(0).getLastName()).isEqualTo("Doe");
 * 
 * Person jane = new Person("Jane","Doe");
 * jane.addSibling(new Person("John","Doe"));
 * 
 * Expectamundo.verify(jane).matches(expected));
 * </pre>
 * 
 * To verifying map properties</p>
 * 
 * <pre>
 * Person expected = Expectamundo.prototype(Person.class);
 * Expectamundo.expect(expected.getSiblingMap().get("John").getFirstName()).isEqualTo("John");
 * Expectamundo.expect(expected.getSiblingMap().get("John").getLastName()).isEqualTo("Doe");
 * 
 * Person jane = new Person("Jane","Doe");
 * jane.addSibling(new Person("John","Doe"));
 * 
 * Expectamundo.verify(jane).matches(expected));
 * </pre>
 * 
 * 
 * @author Stewart Bissett
 */
public class Expactamundo {

	private static PrototypeFactory factory = new PrototypeFactory();

	/**
	 * Prepare a new prototype
	 */
	public static <T> T prototype(final Class<T> type) {
		return factory.createPrototype(type);
	}

	/**
	 * Prepare a new prototype for a generic type
	 */
	@SuppressWarnings("unchecked")
	public static <T> T prototype(final TypeReference<T> typeRef) {
		return (T) factory.createPrototype(typeRef.getType());
	}

	/**
	 * Setup an expecation for a collection property on a {@link Prototype}
	 */
	public static <E, T extends Collection<E>> PrototypeCollectionExpectation<E, T> expect(final T property) {
		checkActivePrototype();
		return new PrototypeCollectionExpectation<E, T>(currentPrototype(), currentPrototype().getActiveProperty());
	}

	/**
	 * Setup an expecation for a comparable property on a {@link Prototype}
	 */
	public static <T extends Comparable<T>> PrototypeComparableExpectation<T> expect(final T property) {
		checkActivePrototype();
		return new PrototypeComparableExpectation<T>(currentPrototype(), currentPrototype().getActiveProperty());
	}

	/**
	 * Setup an expecation for a property on a {@link Prototype}
	 */
	public static PrototypeObjectExpectation expect(final Object property) {
		checkActivePrototype();
		return new PrototypeObjectExpectation(currentPrototype(), currentPrototype().getActiveProperty());
	}

	public static <T> PrototypeVerifier<T> verify(final T test) {
		return new PrototypeVerifier<T>(test);
	}

	private static void checkActivePrototype() {
		if (currentPrototype() == null) {
			throw new IllegalArgumentException("You can only set an expectation for a property created with Expactamundo.prototype()");
		} else if (currentPrototype().getActiveProperty() == null) {
			throw new IllegalArgumentException("You can only set an expectation for a property created with Expactamundo.prototype()");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Matcher<T> matches(final T expected) {
		if (!Prototyped.class.isInstance(expected)) {
			throw new IllegalArgumentException("You can only match an expectation for a type created with Expactamundo.prototype()");
		} else {
			return new PrototypeMatcher<T>((Prototyped<T>) expected);
		}
	}

}
