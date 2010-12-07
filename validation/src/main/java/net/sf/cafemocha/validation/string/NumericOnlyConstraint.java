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
 * @author computerguy5
 * 
 */
public class NumericOnlyConstraint implements Constraint<String> {

	private static final NumericOnlyConstraint NUMERIC_ONLY = new NumericOnlyConstraint();

	public static NumericOnlyConstraint numericOnly() {
		return NUMERIC_ONLY;
	}

	public NumericOnlyConstraint() {
		this.allowedCharacters = "0123456789";
	}

	private final String allowedCharacters;

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(StringUtils.containsOnly(t, allowedCharacters),
					"May contain only these characters: ", allowedCharacters);
		}
	}

}
