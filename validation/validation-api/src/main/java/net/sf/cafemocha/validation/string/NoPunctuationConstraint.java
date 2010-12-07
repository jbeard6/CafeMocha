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
public class NoPunctuationConstraint implements Constraint<String> {

	private static final NoPunctuationConstraint INSTANCE = new NoPunctuationConstraint();

	public static NoPunctuationConstraint noPunctuation() {
		return INSTANCE;
	}

	/**
	 * Matches a punctuation character.
	 */
	protected static final Pattern PUNCTUATION_PATTERN = Pattern
			.compile("\\p{Punct}");

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(!PUNCTUATION_PATTERN.matcher(t).find(),
					"May not contain punctuation");
		}
	}

}
