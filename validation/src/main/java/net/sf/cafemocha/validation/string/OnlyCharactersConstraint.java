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
public class OnlyCharactersConstraint implements Constraint<String> {

	public static OnlyCharactersConstraint onlyContains(String allowedCharacters) {
		return new OnlyCharactersConstraint(allowedCharacters);
	}

	/**
	 * @param allowedCharacters
	 *            the only permissable characters that the string may contain
	 */
	public OnlyCharactersConstraint(String allowedCharacters) {
		this.allowedCharacters = allowedCharacters;
	}

	private final String allowedCharacters;

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(StringUtils.containsOnly(t, allowedCharacters),
					"May contain only these characters: ", allowedCharacters);
		}
	}

}
