/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.string;

import net.sf.cafemocha.validation.Constraint;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * Validates that a {@link String} is of a minimum length.
 * 
 * @author computerguy5
 * 
 */
public class MinimumLengthConstraint implements Constraint<String> {

	public static MinimumLengthConstraint minimumLength(int minimumLength) {
		return new MinimumLengthConstraint(minimumLength);
	}

	public MinimumLengthConstraint(int minimumLength) {
		if (minimumLength < 0) {
			throw new IllegalArgumentException(minimumLength + " < 0");
		}
		this.minimumLength = minimumLength;
	}

	private final int minimumLength;

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(StringUtils.length(t) >= minimumLength,
					"Minimum characters: ", minimumLength);
		}
	}

}
