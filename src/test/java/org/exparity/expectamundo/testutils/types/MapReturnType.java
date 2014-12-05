
package org.exparity.expectamundo.testutils.types;

import java.util.Map;

/**
 * @author Stewart Bissett
 */
public class MapReturnType {

	private final Map<String, String> value;

	public MapReturnType(final Map<String, String> value) {
		this.value = value;
	}

	public Map<String, String> getValue() {
		return value;
	}
}
