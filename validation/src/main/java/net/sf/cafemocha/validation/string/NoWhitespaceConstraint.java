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
public class NoWhitespaceConstraint implements Constraint<String> {

	private static final NoWhitespaceConstraint NO_WHITESPACE = new NoWhitespaceConstraint();

	public static NoWhitespaceConstraint noWhitespace() {
		return NO_WHITESPACE;
	}

	public void validate(String t) throws IllegalArgumentException {
		Validate.isTrue(StringUtils.equals(StringUtils.deleteWhitespace(t), t),
				"May not contain whitespace");
	}

}
