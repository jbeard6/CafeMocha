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
public class AsciiOnlyConstraint implements Constraint<String> {

	private static final AsciiOnlyConstraint INSTANCE = new AsciiOnlyConstraint();

	public static AsciiOnlyConstraint alphaOnly() {
		return INSTANCE;
	}

	public void validate(String t) throws IllegalArgumentException {
		Validate.isTrue(StringUtils.isAsciiPrintable(t),
				"May contain only ASCII printable characters.");
	}

}
