
package org.exparity.expectamundo;

import java.util.Collection;
import org.exparity.expectamundo.core.PrototypeArrayExpectation;
import org.exparity.expectamundo.core.PrototypeCollectionExpectation;
import org.exparity.expectamundo.core.PrototypeComparableExpectation;
import org.exparity.expectamundo.core.PrototypeFactory;
import org.exparity.expectamundo.core.PrototypeObjectExpectation;
import org.exparity.expectamundo.core.PrototypeStringExpectation;
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
	 * Create a new prototype instance against which expectations can be set. For example</p>
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * </pre>
	 * @param type The class of type of the prototype to create.
	 * @param <T> The type of the instance to create.
	 * @return A new instance of the type against which expectations can be set
	 */
	public static <T> T prototype(final Class<T> type) {
		return factory.createPrototype(type);
	}

	/**
	 * Create a new prototype instance against a generic type against which expectations can be set. For example</p>
	 * 
	 * <pre>
	 * List&lt;String&gt; expected = Expectamundo.prototype(new TypeReference&lt;List&lt;String&gt;&gt;(){})
	 * </pre>
	 * @param typeRef An instance of a {@link TypeReference} parameterized with the type to prototype
	 * @param <T> The class of the prototype to create
	 * @return A new instance of the type against which expectations can be set
	 */
	@SuppressWarnings("unchecked")
	public static <T> T prototype(final TypeReference<T> typeRef) {
		return (T) factory.createPrototype(typeRef.getType());
	}

	/**
	 * Setup an expectation for a collection property on a {@link Prototype}. For example</p>
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * Expectamundo.expect(expected.getSiblings()).isEmpty();
	 * Expectamundo.verify(new Person()).matches(expected);
	 * </pre>
	 * @param property the value to set the expectation for.
	 * @param <T> the type of the collection
	 * @param <E> the type of elements in this collection
	 * @return A instance of a {@link PrototypeCollectionExpectation} to set collection expectations for the property
	 * */
	public static <E, T extends Collection<E>> PrototypeCollectionExpectation<E, T> expect(final T property) {
		checkActivePrototype();
		return new PrototypeCollectionExpectation<E, T>(currentPrototype(), currentPrototype().getActiveProperty());
	}

	/**
	 * Setup an expectation for a {@link Comparable} property on a {@link Prototype}. For example</p>
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * Expectamundo.expect(expected.getAge()).isComparableTo(29);
	 * Expectamundo.verify(new Person()).matches(expected);
	 * </pre>
	 * @param property the value to set the expectation for.
	 * @param <T> the type of the property
	 * @return A instance of a {@link PrototypeComparableExpectation} to set comparable expectations for the property
	 * */
	public static <T extends Comparable<T>> PrototypeComparableExpectation<T> expect(final T property) {
		checkActivePrototype();
		return new PrototypeComparableExpectation<T>(currentPrototype(), currentPrototype().getActiveProperty());
	}

	/**
	 * Setup an expectation for a array property on a {@link Prototype}. For example</p>
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * Expectamundo.expect(expected.getSiblings()).isEmpty();
	 * Expectamundo.verify(new Person()).matches(expected);
	 * </pre>
	 * @param property the value to set the expectation for.
	 * @param <T> the type of the array
	 * @return A instance of a {@link PrototypeArrayExpectation} to set array expectations for the property
	 * */
	public static <T> PrototypeArrayExpectation<T> expect(final T[] property) {
		checkActivePrototype();
		return new PrototypeArrayExpectation<T>(currentPrototype(), currentPrototype().getActiveProperty());
	}

	/**
	 * Setup an expectation for a string property on a {@link Prototype}. For example</p>
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * Expectamundo.expect(expected.getSurname()).hasPattern("Sm.*");
	 * Expectamundo.verify(new Person()).matches(expected);
	 * </pre>
	 * @param property the value to set the expectation for.
	 * @return A instance of a {@link PrototypeStringExpectation} to set String expectations for the property
	 * */
	public static PrototypeStringExpectation expect(final String property) {
		checkActivePrototype();
		return new PrototypeStringExpectation(currentPrototype(), currentPrototype().getActiveProperty());
	}

	/**
	 * Setup an expectation for a object property on a {@link Prototype}. For example</p>
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * Expectamundo.expect(expected.getSurname()).isEqualTo("Smith");
	 * Expectamundo.verify(new Person()).matches(expected);
	 * </pre>
	 * @param property the value to set the expectation for.
	 * @return A instance of a {@link PrototypeObjectExpectation} to set object expectations for the property
	 * */
	public static PrototypeObjectExpectation expect(final Object property) {
		checkActivePrototype();
		return new PrototypeObjectExpectation(currentPrototype(), currentPrototype().getActiveProperty());
	}

	/**
	 * Verify the actual value matches the expectation set up in the prototype. For example</p>
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * Expectamundo.expect(expected.getSurname()).isEqualTo("Smith");
	 * Expectamundo.verify(new Person()).matches(expected);
	 * </pre>
	 * @param actual the actual instance to test
	 * @return A instance of a {@link PrototypeVerifier} to allow the expectation to be supplied
	 */
	public static <T> PrototypeVerifier<T> verify(final T actual) {
		return new PrototypeVerifier<T>(actual);
	}

	/**
	 * Cast a polymorphic return type to the expected subtype whilst setting an expectation. For example</p>
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * Expectamundo.expect(Expectamundo.cast(expected.getProfession(), Doctor.class).getQualifications()).isNotEmpty())
	 * Expectamundo.verify(new Person()).matches(expected);
	 * </pre>
	 * @param value the property value.
	 * @param type the Class of the property value to cast to.
	 * @param <V> the value type
	 * @param <T> the expected type of the value
	 */
	public static <V, T extends V> T cast(final V value, final Class<T> type) {
		checkActivePrototype();
		T prototype = factory.createPrototype(type, currentPrototype().getActiveProperty(), currentPrototype());
		currentPrototype().addChild((Prototyped<?>) prototype);
		return prototype;
	}


	/**
	 * Return a hamcrest {@link Matcher} instance to support asserting the contents of an actual instance match the expected.
	 * 
	 * <pre>
	 * Person expected = Expectamundo.prototype(Person.class)
	 * Expectamundo.expect(expected.getSurname()).isEqualTo("Smith")
	 * MatcherAssert.assertThat(new Person(), Expectamundo.matches(expected))
	 * </pre>
	 * @param expected The prototype with expectations defined against it
	 * @param <T> The type of the prototype
	 * @return A hamcrest Matcher for the prototype which can be used to assert the contents of a test object
	 * */
	@SuppressWarnings("unchecked")
	public static <T> Matcher<T> matches(final T expected) {
		if (!Prototyped.class.isInstance(expected)) {
			throw new IllegalArgumentException("You can only match an expectation for a type created with Expactamundo.prototype()");
		} else {
			return new PrototypeMatcher<T>((Prototyped<T>) expected);
		}
	}

	private static void checkActivePrototype() {
		if (currentPrototype() == null) {
			throw new IllegalArgumentException("You can only set an expectation on an instance created with Expactamundo.prototype()");
		} else if (currentPrototype().getActiveProperty() == null) {
			throw new IllegalArgumentException("You can only set an expectation for a property");
		}
	}

}
