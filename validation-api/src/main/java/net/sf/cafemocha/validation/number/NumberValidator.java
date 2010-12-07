/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.number;

import java.math.BigDecimal;

import javax.swing.text.JTextComponent;

import net.sf.cafemocha.validation.CompositeValidator;

import org.apache.commons.lang.StringUtils;

/**
 * Validates that an {@link Object} is a {@link Number}.
 * 
 * @author computerguy5
 * 
 */
public class NumberValidator extends CompositeValidator<Number> {

	private static final String NOT_A_NUMBER_MESSAGE = "Not a Number";

	/**
	 * Construct a new {@link NumberValidator} that validates that an
	 * {@link Object} is a {@link Number}.
	 */
	public NumberValidator() {
		this(NOT_A_NUMBER_MESSAGE);
	}

	/**
	 * Construct a new {@link NumberValidator} that validates that an
	 * {@link Object} is a {@link Number}.
	 * 
	 * @param notANumberMessage
	 *            message to indicate that the object is not a number
	 */
	public NumberValidator(String notANumberMessage) {
		this.notANumberMessage = notANumberMessage == null ? NOT_A_NUMBER_MESSAGE
				: notANumberMessage;
	}

	private final String notANumberMessage;

	@Override
	protected Number convert(Object obj) throws IllegalArgumentException {
		if (obj == null) {
			return null;
		} else if (obj instanceof Number) {
			return (Number) obj;
		} else if (obj instanceof String) {
			return getNumber((String) obj);
		} else if (obj instanceof JTextComponent) {
			return getNumber(((JTextComponent) obj).getText());
		}

		// Some other object altogether
		throw new IllegalArgumentException(notANumberMessage);
	}

	private Number getNumber(String string) throws IllegalArgumentException {
		if (StringUtils.isBlank(string)) {
			return null;
		}

		try {
			return new BigDecimal(string);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(notANumberMessage, ex);
		}
	}

}
