
package org.exparity.expectamundo.testutils.types;

import java.util.List;

/**
 * @author Stewart Bissett
 */
public class ListReturnType {

	private final List<String> value;

	public ListReturnType(final List<String> value) {
		this.value = value;
	}

	public List<String> getValue() {
		return value;
	}
}
