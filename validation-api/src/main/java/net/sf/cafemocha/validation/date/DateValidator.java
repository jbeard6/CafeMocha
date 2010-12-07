/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.text.JTextComponent;

import net.sf.cafemocha.validation.CompositeValidator;

import org.apache.commons.lang.StringUtils;

/**
 * Validates that a <code>Date</code> is valid in accordance to the given rules.
 * 
 * @author computerguy5
 * 
 */
public class DateValidator extends CompositeValidator<Date> {

	public DateValidator() {
		this(null);
	}

	/**
	 * @param dateFormat
	 *            the format to use for interpreting Strings as Dates
	 */
	public DateValidator(DateFormat dateFormat) {
		if (dateFormat == null) {
			// Use default format
			dateFormat = DateFormat.getDateInstance();
		}

		this.dateFormat = dateFormat;
	}

	/**
	 * Use the current local's default date formatter by default.
	 */
	private final DateFormat dateFormat;

	/**
	 * @return the date formatter to use by default
	 */
	public final DateFormat getDateFormat() {
		return dateFormat;
	}

	@Override
	protected Date convert(Object object) throws IllegalArgumentException {
		if (object == null) {
			return null;
		} else if (object instanceof Date) {
			return (Date) object;
		} else if (object instanceof String) {
			return convert((String) object);
		} else if (object instanceof JTextComponent) {
			return convert(((JTextComponent) object).getText());
		}

		throw new IllegalArgumentException("Not a Date");
	}

	/**
	 * Validate a <code>String</code> as a date using the specified
	 * <code>DateFormat</code>.
	 * 
	 * @param dateString
	 *            the date to validate
	 * @throws IllegalArgumentException
	 *             if the <code>Date</code> is invalid
	 */
	public Date convert(String dateString) throws IllegalArgumentException {
		if (StringUtils.isBlank(dateString)) {
			return null;
		}

		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Unrecognizable date: "
					+ dateString);
		}
	}

}
