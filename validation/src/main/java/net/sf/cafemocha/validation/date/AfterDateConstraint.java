/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.date;

import java.util.Date;

import net.sf.cafemocha.validation.Constraint;

import org.apache.commons.lang.Validate;

/**
 * @author computerguy5
 * 
 */
public class AfterDateConstraint implements Constraint<Date> {

	public static AfterDateConstraint after(Date afterDate) {
		return new AfterDateConstraint(afterDate);
	}

	/**
	 * @param afterDate
	 *            the validated date must be on or before this date
	 */
	public AfterDateConstraint(Date afterDate) {
		if (afterDate == null) {
			throw new NullPointerException("afterDate");
		}

		this.afterDate = afterDate;
	}

	private final Date afterDate;

	public void validate(Date t) throws IllegalArgumentException {
		Validate.isTrue(afterDate.compareTo(t) <= 0, "May not be prior to: ",
				afterDate);
	}

}
