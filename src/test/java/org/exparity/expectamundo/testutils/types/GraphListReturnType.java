package org.exparity.expectamundo.testutils.types;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stewart Bissett
 */
public class GraphListReturnType {

	private final List<GraphType> value;

	public GraphListReturnType(final GraphType... values) {
		this(Arrays.asList(values));
	}

	public GraphListReturnType(final List<GraphType> value) {
		this.value = value;
	}

	public List<GraphType> getValue() {
		return value;
	}
}
