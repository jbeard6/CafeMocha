/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.string;

import net.sf.cafemocha.validation.Constraint;

import org.apache.commons.lang.Validate;

/**
 * Validates that a {@link String} is of a minimum length.
 * 
 * @author computerguy5
 * 
 */
public class MaximumLengthConstraint implements Constraint<String> {

	public static MaximumLengthConstraint maximumLength(int maximumLength) {
		return new MaximumLengthConstraint(maximumLength);
	}

	public MaximumLengthConstraint(int maximumLength) {
		if (maximumLength < 0) {
			throw new IllegalArgumentException(maximumLength + " < 0");
		}
		this.maximumLength = maximumLength;
	}

	private final int maximumLength;

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(t.length() <= maximumLength,
					"Maximum characters: ", maximumLength);
		}
	}

}
