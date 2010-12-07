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
 * Validates that a {@link String} does not contain leading whitespace.
 * 
 * @author computerguy5
 * 
 */
public class NoLeadingWhitespaceConstraint implements Constraint<String> {

	private static final NoLeadingWhitespaceConstraint INSTANCE = new NoLeadingWhitespaceConstraint();

	public static NoLeadingWhitespaceConstraint noLeadingWhitespace() {
		return INSTANCE;
	}

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			String noWhitespace = StringUtils.stripStart(t, null);
			Validate.isTrue(t.equals(noWhitespace),
					"May not contain leading whitespace");
		}
	}

}
