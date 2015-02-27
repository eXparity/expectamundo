
package org.exparity.expectamundo.core.object;

import java.util.Arrays;
import java.util.Collection;
import org.exparity.expectamundo.core.PropertyExpectation;
import org.exparity.expectamundo.core.Prototype;
import org.exparity.expectamundo.core.PrototypeProperty;
import org.exparity.expectamundo.core.PrototypePropertyMatcher;
import org.hamcrest.Matcher;

/**
 * @author Stewart Bissett
 */
public class PrototypeObjectExpectation<T> {

	private final Prototype<?> prototype;
	private final PrototypeProperty property;

	public PrototypeObjectExpectation(final Prototype<?> prototype, final PrototypeProperty property) {
		this.prototype = prototype;
		this.property = property;
	}

	/**
	 * Set an expectation that the property value matches a hamcrest matcher. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).matches(Matchers.equalTo(&quot;Jane&quot;));
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param matcher the hamcrest matcher to use to match the item
	 */
	public void matches(final Matcher<T> matcher) {
		hasExpectation(new Matches<T>(matcher));
	}

	/**
	 * Set an expectation that the property value is equal to another object using the objects equals method. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isEqualTo(&quot;Jane&quot;);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param expectedValue the value this property should be equal to
	 */
	public void isEqualTo(final T expectedValue) {
		hasExpectation(new IsEqualTo<T>(expectedValue));
	}

	/**
	 * Set an expectation that the property value is not equal to another object using the objects equals method. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isNotEqualTo(&quot;Jane&quot;);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param expectedValue the value this property should not be equal to
	 */
	public void isNotEqualTo(final T expectedValue) {
		hasExpectation(new IsNotEqualTo<T>(expectedValue));
	}

	/**
	 * Set an expectation that the property value is equal to one of the expected object using the object's equals method. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isOneOf(&quot;Jane&quot;, &quot;Bob&quot;);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param possibleValues the range of possible value this property should be one of
	 */
	@SuppressWarnings("unchecked")
	public void isOneOf(final T... possibleValues) {
		isOneOf(Arrays.asList(possibleValues));
	}

	/**
	 * Set an expectation that the property value is equal to one of the expected object using the object's equals method. For example</p>
	 * 
	 * <pre>
	 * List&lt;String&gt; options = Arrays.asList(&quot;Jane&quot;, &quot;Bob&quot;);
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isOneOf(options);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param possibleValues the range of possible value this property should be one of
	 */
	public void isOneOf(final Collection<T> possibleValues) {
		hasExpectation(new IsOneOf<T>(possibleValues));
	}

	/**
	 * Set an expectation that the property value is an instance of a type. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isInstanceOf(String.class);
	 * expectThat(actual).matches(expected);
	 * </pre>
	 * @param expectedValue the type this property should be an instance of
	 */
	public void isInstanceOf(final Class<? extends T> expectedValue) {
		hasExpectation(new IsInstanceOf<T>(expectedValue));
	}

	/**
	 * Set an expectation that the object should be null. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isNull();
	 * expectThat(actual).matches(expected);
	 * </pre>
	 */
	public void isNull() {
		hasExpectation(new IsNull<T>());
	}

	/**
	 * Set an expectation that the object should not be null. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isNotNull();
	 * expectThat(actual).matches(expected);
	 * </pre>
	 */
	public void isNotNull() {
		hasExpectation(new IsNotNull<T>());
	}

	/**
	 * Set an expectation that the object should not be null. For example</p>
	 * 
	 * <pre>
	 * MyObject expected = prototype(MyObject.class);
	 * expect(expected.name()).isNotNull();
	 * expectThat(actual).matches(expected);
	 * </pre>
	 */
	public void hasExpectation(final PropertyExpectation<T> expectation) {
		prototype.addExpectation(new PrototypePropertyMatcher(property, expectation));
		prototype.setActiveProperty(null);
	}

}
