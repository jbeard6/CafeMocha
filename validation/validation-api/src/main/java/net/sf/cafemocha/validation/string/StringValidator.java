/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.string;

import javax.swing.text.JTextComponent;

import net.sf.cafemocha.validation.CompositeValidator;

/**
 * Validates that an {@link Object} is a {@link String}.
 * 
 * @author computerguy5
 * 
 */
public class StringValidator extends CompositeValidator<String> {

	 @Override
	protected String convert(Object object) throws IllegalArgumentException {
		if (object == null) {
			return null;
		} else if (object instanceof String) {
			return (String) object;
		} else if (object instanceof JTextComponent) {
			JTextComponent control = (JTextComponent) object;
			return control.getText();
		}

		// TBD Obtain string from other objects using toString()?
		throw new IllegalArgumentException("Not a String");
	}

}
