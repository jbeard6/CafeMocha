/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.date;

import java.util.Date;

import net.sf.cafemocha.validation.Validator;

import org.apache.commons.lang.Validate;

/**
 * Validates that a range of time, expressed by Date objects, is valid.
 * 
 * @author computerguy5
 * 
 */
public class DateRangeValidator implements Validator {

	public DateRangeValidator() {
		this.earliestDate = null;
		this.latestDate = null;
	}

	/**
	 * Constructs a new <code>DateRangeValidator</code>.
	 * 
	 * @param earliestDate
	 *            the earliest date that a Date may be, or null if the Date does
	 *            not have a lower bound
	 * @param latestDate
	 *            the latest date that a Date may be, or null if the Date does
	 *            not have an upper bound
	 */
	public DateRangeValidator(Date earliestDate, Date latestDate) {
		this.earliestDate = earliestDate;
		this.latestDate = latestDate;
	}

	private final Date earliestDate;

	private final Date latestDate;

	public void validate(Object obj) throws IllegalArgumentException {
		throw new IllegalArgumentException("Range not specified.");
	}

	/**
	 * Validates that a given range of <code>Date</code>s is valid.
	 * 
	 * @param beginDate
	 *            the start of the DateRange
	 * @param endDate
	 *            the end of the DateRange
	 * @throws IllegalArgumentException
	 *             if the <code>DateRange</code> is invalid
	 */
	public void validate(Date beginDate, Date endDate)
			throws IllegalArgumentException {
		DateRange range = new DateRange(beginDate, endDate);

		validate(range);
	}

	/**
	 * Validates that a DateRange complies with the validation rules.
	 * 
	 * @param range
	 *            the <code>DateRange</code> to validate
	 * @throws IllegalArgumentException
	 *             if the <code>DateRange</code> is invalid
	 */
	public void validate(DateRange range) throws IllegalArgumentException {
		if (range != null) {
			Date beginDate = range.getBeginDate();
			Date endDate = range.getEndDate();

			Validate.notNull(beginDate, "Range has no beginning.");
			Validate.notNull(endDate, "Range has no end.");
			Validate.isTrue(beginDate.compareTo(endDate) <= 0,
					"Start of range must be prior to end.");

			if (earliestDate != null) {
				Validate.isTrue(earliestDate.compareTo(beginDate) <= 0,
						"Range may not be prior to: ", earliestDate);
			}

			if (latestDate != null) {
				Validate.isTrue(latestDate.compareTo(endDate) >= 0,
						"Range may not be later than: ", latestDate);
			}
		}
	}

	/**
	 * An object that encapsulates a range of dates, specified as a beginning
	 * date and an ending date.
	 * 
	 * @author computerguy5
	 * 
	 */
	public static class DateRange {

		/**
		 * Construct a new DateRange with the given values.
		 * 
		 * @param beginDate
		 *            the start of the DateRange
		 * @param endDate
		 *            the end of the DateRange
		 */
		public DateRange(Date beginDate, Date endDate) {
			this.beginDate = beginDate;
			this.endDate = endDate;
		}

		private final Date beginDate;

		/**
		 * @return the start of the DateRange
		 */
		public final Date getBeginDate() {
			return beginDate;
		}

		private final Date endDate;

		/**
		 * @return the end of the DateRange
		 */
		public final Date getEndDate() {
			return endDate;
		}
	}
}
