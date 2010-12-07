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
public class AlphaOnlyConstraint implements Constraint<String> {

	private static final AlphaOnlyConstraint INSTANCE = new AlphaOnlyConstraint();

	public static AlphaOnlyConstraint alphaOnly() {
		return INSTANCE;
	}

	public void validate(String t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(StringUtils.isAlpha(t),
					"Only alphabetic characters allowed.");
		}
	}

}
