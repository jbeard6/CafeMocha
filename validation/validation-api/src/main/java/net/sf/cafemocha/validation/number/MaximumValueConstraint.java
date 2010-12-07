/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.number;

import net.sf.cafemocha.validation.Constraint;

import org.apache.commons.lang.Validate;

/**
 * Validates that a {@link Number} is less than a maximum value (inclusive).
 * 
 * @author computerguy5
 * 
 */
public class MaximumValueConstraint<T extends Number & Comparable<T>>
		implements Constraint<T> {

	public static <T extends Number & Comparable<T>> MaximumValueConstraint<T> maximumValue(
			T maximum) {
		return new MaximumValueConstraint<T>(maximum);
	}

	/**
	 * @param maximum
	 */
	public MaximumValueConstraint(T maximum) {
		if (maximum == null) {
			throw new NullPointerException("maximum");
		}
		this.maximum = maximum;
	}

	private final T maximum;

	public void validate(T t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(maximum.compareTo(t) >= 0, "Maximum Value: ",
					maximum);
		}
	}

}
