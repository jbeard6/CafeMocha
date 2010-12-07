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
 * Validates that a {@link Number} is greater than a minimum value (inclusive).
 * 
 * @author computerguy5
 * 
 */
public class MinimumValueConstraint<T extends Number & Comparable<T>>
		implements Constraint<T> {

	public static <T extends Number & Comparable<T>> MinimumValueConstraint<T> minimumValue(
			T minimum) {
		return new MinimumValueConstraint<T>(minimum);
	}

	/**
	 * @param minimum
	 */
	public MinimumValueConstraint(T minimum) {
		if (minimum == null) {
			throw new NullPointerException("minimum");
		}
		this.minimum = minimum;
	}

	private final T minimum;

	public void validate(T t) throws IllegalArgumentException {
		if (t != null) {
			Validate.isTrue(minimum.compareTo(t) <= 0, "Minimum Value: ",
					minimum);
		}
	}

}
