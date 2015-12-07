package org.exparity.expectamundo.testutils.types;

import java.util.Arrays;
import java.util.List;

public class SimpleTypeOldStyle {
	private final List<String> value = Arrays.asList("eXpectamundo lets me test this");

	public List<String> getValue() {
		return value;
	}
}
