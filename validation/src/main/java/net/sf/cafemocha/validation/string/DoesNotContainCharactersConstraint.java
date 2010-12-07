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
public class DoesNotContainCharactersConstraint implements Constraint<String> {

	public static DoesNotContainCharactersConstraint doesNotContain(
			String invalidCharacters) {
		return new DoesNotContainCharactersConstraint(invalidCharacters);
	}

	/**
	 * @param invalidCharacters
	 *            the characters that the string may not contain
	 */
	public DoesNotContainCharactersConstraint(String invalidCharacters) {
		this.invalidCharacters = invalidCharacters;
	}

	private final String invalidCharacters;

	public void validate(String t) throws IllegalArgumentException {
		Validate.isTrue(StringUtils.containsNone(t, invalidCharacters),
				"May not contain any of these characters: ", invalidCharacters);
	}

}
