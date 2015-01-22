
package org.exparity.expectamundo.core;

import java.util.List;
import org.exparity.expectamundo.core.PrototypeMatcher.PrototypeMatchResult;
import org.exparity.expectamundo.core.PrototypeMatcher.PrototypePropertyDifference;

/**
 * @author Stewart Bissett
 */
public class PrototypeMismatchReporter {

	private static final String TEMPLATE = "\nExpected a %s containing properties:%s\nbut actual is a %s containing properties:%s";
	private static final String LISTTEMPLATE = "\nExpected a list containing a %s with properties:%s\nbut actual list contains:%s";

	public <T> String describeListMismatch(final Prototyped<T> expected, final List<PrototypeMatchResult<T>> mismatches) {
		return String.format(LISTTEMPLATE,
				describeExpectedType(expected),
				describeExpectations(expected.getExpectations()),
				describeMismatches(mismatches));
	}

	public <T> String describeInstanceMismatch(final PrototypeMatchResult<T> result) {
		return String.format(TEMPLATE,
				describeExpectedType(result),
				describeExpectations(result.getExpectations()),
				describeActualType(result),
				describeDifferences(result.getDifferences()));
	}

	private <T> String describeActualType(final PrototypeMatchResult<T> result) {
		return result.getActualType().getSimpleName();
	}

	private <T> String describeExpectedType(final PrototypeMatchResult<T> result) {
		return result.getExpectedType().getSimpleName();
	}

	private <T> String describeExpectedType(final Prototyped<T> result) {
		return result.getRawType().getSimpleName();
	}

	private String describeExpectations(final List<PrototypePropertyMatcher> expectations) {
		StringBuffer buffer = new StringBuffer();
		for (PrototypePropertyMatcher expecation : expectations) {
			buffer.append("\n\t").append(expecation.getPropertyPath()).append(" is ").append(expecation.getExpectation());
		}
		return buffer.toString();
	}

	private String describeDifferences(final List<PrototypePropertyDifference> mismatches) {
		StringBuffer buffer = new StringBuffer();
		for (PrototypePropertyDifference mismatch : mismatches) {
			buffer.append("\n\t").append(mismatch.getPath()).append(" is ").append(mismatch.getValue());
		}
		return buffer.toString();
	}

	private <T> String describeMismatches(final List<PrototypeMatchResult<T>> mismatches) {
		if (mismatches.isEmpty()) {
			return "No entries";
		} else {
			StringBuffer buffer = new StringBuffer();
			for (PrototypeMatchResult<T> mismatch : mismatches) {
				buffer.append("\n\t").append(describeActualType(mismatch)).append(" containing properties");
				for (PrototypePropertyDifference difference : mismatch.getDifferences()) {
					buffer.append("\n\t\t").append(difference.getPath()).append(" is ").append(difference.getValue());
				}
			}
			return buffer.toString();
		}

	}
}
