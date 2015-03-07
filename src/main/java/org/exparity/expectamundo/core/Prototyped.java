
package org.exparity.expectamundo.core;

import java.util.List;

/**
 * @author Stewart Bissett
 */
public interface Prototyped<T> {

	/**
	 * Return the collection of expectations relevant to this protype
	 */
	public List<PrototypeValueMatcher> getExpectations();

	/**
	 * Return
	 */
	public Class<T> getRawType();

}
