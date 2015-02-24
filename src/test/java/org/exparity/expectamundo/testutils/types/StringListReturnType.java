
package org.exparity.expectamundo.testutils.types;

import java.util.List;

/**
 * @author Stewart Bissett
 */
public class StringListReturnType extends ParameterizedListReturnType<String> {

	public StringListReturnType(final List<String> value) {
		super(value);
	}

}
