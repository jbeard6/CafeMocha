/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.number;

import java.math.BigInteger;

import javax.swing.text.JTextComponent;

import net.sf.cafemocha.validation.CompositeValidator;

import org.apache.commons.lang.StringUtils;

/**
 * Validates that an {@link Object} is a {@link Number}.
 * 
 * @author computerguy5
 * 
 */
public class BigIntegerValidator extends CompositeValidator<BigInteger> {

	private static final String NOT_A_NUMBER_MESSAGE = "Not an Integer";

	/**
	 * Construct a new {@link BigIntegerValidator} that validates that an
	 * {@link Object} is a {@link BigInteger}.
	 */
	public BigIntegerValidator() {
		this(NOT_A_NUMBER_MESSAGE);
	}

	/**
	 * Construct a new {@link BigIntegerValidator} that validates that an
	 * {@link Object} is a {@link BigInteger}.
	 * 
	 * @param notANumberMessage
	 *            message to indicate that the object is not a BigInteger
	 */
	public BigIntegerValidator(String notANumberMessage) {
		this.notANumberMessage = notANumberMessage == null ? NOT_A_NUMBER_MESSAGE
				: notANumberMessage;
	}

	private final String notANumberMessage;

	@Override
	protected BigInteger convert(Object obj) throws IllegalArgumentException {
		if (obj == null) {
			return null;
		} else if (obj instanceof BigInteger) {
			return (BigInteger) obj;
		} else if (obj instanceof String) {
			return getNumber((String) obj);
		} else if (obj instanceof JTextComponent) {
			return getNumber(((JTextComponent) obj).getText());
		}

		// Some other object altogether
		throw new IllegalArgumentException(notANumberMessage);
	}

	private BigInteger getNumber(String string) throws IllegalArgumentException {
		if (StringUtils.isBlank(string)) {
			return null;
		}

		try {
			return new BigInteger(string);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(notANumberMessage, ex);
		}
	}

}
