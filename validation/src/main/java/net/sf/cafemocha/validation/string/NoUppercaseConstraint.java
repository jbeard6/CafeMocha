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
public class NoUppercaseConstraint implements Constraint<String> {

	private static final NoUppercaseConstraint INSTANCE = new NoUppercaseConstraint();

	public static NoUppercaseConstraint noUppercase() {
		return INSTANCE;
	}

	/**
	 * Matches an uppercase alphabetic character.
	 */
	protected static final Pattern UPPERCASE_PATTERN = Pattern
			.compile("\\p{Upper}");

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(!UPPERCASE_PATTERN.matcher(t).find(),
					"May not contain uppercase characters");
		}
	}

}
