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
public class NoLowercaseConstraint implements Constraint<String> {

	private static final NoLowercaseConstraint NO_LOWERCASE = new NoLowercaseConstraint();

	public static NoLowercaseConstraint noLowercase() {
		return NO_LOWERCASE;
	}

	/**
	 * Matches a lowercase alphabetic character.
	 */
	protected static final Pattern LOWERCASE_PATTERN = Pattern
			.compile("\\p{Lower}");

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(!LOWERCASE_PATTERN.matcher(t).find(),
					"May not contain lowercase characters");
		}
	}

}
