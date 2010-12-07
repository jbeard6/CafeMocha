/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation;

/**
 * Validates if any of the {@link Constraint Constraints} succeed. If all fail,
 * only the first generated {@link IllegalArgumentException} is thrown.
 * 
 * @author computerguy5
 * 
 */
public class OrConstraint<T> extends CompositeConstraint<T> {

	/**
	 * Validates if any of the {@link Constraint Constraints} succeed. If all
	 * fail, only the first generated {@link IllegalArgumentException} is
	 * thrown.
	 * 
	 * @see Constraint#validate(java.lang.Object)
	 */
	public void validate(T t) throws IllegalArgumentException {
		IllegalArgumentException ex = null;

		for (Constraint<? super T> constraint : constraints) {
			try {
				// Attempt validation
				constraint.validate(t);

				// Validation succeeded, so we're valid
				return;
			} catch (IllegalArgumentException e) {
				// Save the first exception
				if (ex == null) {
					ex = e;
				}
			}
		}

		// If all validations failed, return the first one to the caller
		if (ex != null) {
			throw ex;
		}

		/*
		 * Only an empty constraints list would get here, in which case we
		 * consider validation successful.
		 */
	}

	/**
	 * Convenience method for <em>OR</em>-ing {@link Constraint Constraints}.
	 * 
	 * @param <T>
	 *            the type of object being validated by the constraints
	 * @param constraint1
	 *            a constraint to be included in the OR
	 * @param constraint2
	 *            a constraint to be included in the OR
	 * @return a constraint that validates if any of the constraint parameters
	 *         are <code>true</code>
	 */
	public static <T> Constraint<T> or(Constraint<? super T> constraint1,
			Constraint<? super T> constraint2) {
		return new OrConstraint<T>().addConstraint(constraint1).addConstraint(
				constraint2);
	}

	/**
	 * Convenience method for <em>OR</em>-ing {@link Constraint Constraints}.
	 * 
	 * @param <T>
	 *            the type of object being validated by the constraints
	 * @param constraint1
	 *            a constraint to be included in the OR
	 * @param constraint2
	 *            a constraint to be included in the OR
	 * @param constraint3
	 *            a constraint to be included in the OR
	 * @return a constraint that validates if any of the constraint parameters
	 *         are <code>true</code>
	 */
	public static <T> Constraint<T> or(Constraint<? super T> constraint1,
			Constraint<? super T> constraint2, Constraint<? super T> constraint3) {
		return new OrConstraint<T>().addConstraint(constraint1).addConstraint(
				constraint2).addConstraint(constraint3);
	}

}
