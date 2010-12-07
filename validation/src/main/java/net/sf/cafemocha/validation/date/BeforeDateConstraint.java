/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.date;

import java.util.Calendar;
import java.util.Date;

import net.sf.cafemocha.validation.Constraint;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.time.DateUtils;

/**
 * @author computerguy5
 * 
 */
public class BeforeDateConstraint implements Constraint<Date> {

	/**
	 * Constraint that a date must be no later than today. This is commonly
	 * used.
	 */
	private static BeforeDateConstraint noLaterThanToday;

	public static BeforeDateConstraint noLaterThanToday() {
		if (noLaterThanToday == null) {
			noLaterThanToday = new BeforeDateConstraint(DateUtils.truncate(
					Calendar.getInstance(), Calendar.DATE).getTime());
		}

		return noLaterThanToday;
	}

	public static BeforeDateConstraint before(Date beforeDate) {
		return new BeforeDateConstraint(beforeDate);
	}

	/**
	 * @param beforeDate
	 *            the validated date must be on or before this date
	 */
	public BeforeDateConstraint(Date beforeDate) {
		if (beforeDate == null) {
			throw new NullPointerException("beforeDate");
		}

		this.beforeDate = beforeDate;
	}

	private final Date beforeDate;

	public void validate(Date t) throws IllegalArgumentException {
		Validate.isTrue(beforeDate.compareTo(t) >= 0,
				"May not be later than: ", beforeDate);
	}

}
