/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.string;

import java.util.regex.Pattern;

import net.sf.cafemocha.validation.Constraint;

import org.apache.commons.lang.Validate;

/**
 * @author computerguy5
 * 
 */
public class NoDigitsConstraint implements Constraint<String> {

	private static final NoDigitsConstraint INSTANCE = new NoDigitsConstraint();

	public static final NoDigitsConstraint noDigits() {
		return INSTANCE;
	}

	/**
	 * Matches a numeric character.
	 */
	protected static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d");

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(!NUMERIC_PATTERN.matcher(t).find(),
					"May not contain numeric characters");
		}
	}

}
