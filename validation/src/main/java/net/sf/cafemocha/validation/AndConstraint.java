/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation;

/**
 * Validates that all of the {@link Constraint Constraints} succeed. If any
 * fail, only the first generated {@link IllegalArgumentException} is thrown.
 * 
 * @author computerguy5
 * 
 */
public class AndConstraint<T> extends CompositeConstraint<T> {

	/**
	 * Validates that all of the {@link Constraint Constraints} succeed. If any
	 * fail, only the first generated {@link IllegalArgumentException} is
	 * thrown.
	 * 
	 * @see Constraint#validate(java.lang.Object)
	 */
	public void validate(T t) throws IllegalArgumentException {
		for (Constraint<? super T> constraint : constraints) {
			// Attempt validation
			constraint.validate(t);
		}
	}

	/**
	 * Convenience method for <em>AND</em>-ing {@link Constraint Constraints}.
	 * 
	 * @param <T>
	 *            the type of object being validated by the constraints
	 * @param constraint1
	 *            a constraint to be included in the AND
	 * @param constraint2
	 *            a constraint to be included in the AND
	 * @return a constraint that requires all of the constraint parameters to be
	 *         <code>true</code>
	 */
	public static <T> Constraint<T> and(Constraint<? super T> constraint1,
			Constraint<? super T> constraint2) {
		return new AndConstraint<T>().addConstraint(constraint1).addConstraint(
				constraint2);
	}

	/**
	 * Convenience method for <em>AND</em>-ing {@link Constraint Constraints}.
	 * 
	 * @param <T>
	 *            the type of object being validated by the constraints
	 * @param constraint1
	 *            a constraint to be included in the AND
	 * @param constraint2
	 *            a constraint to be included in the AND
	 * @param constraint3
	 *            a constraint to be included in the AND
	 * @return a constraint that requires all of the constraint parameters to be
	 *         <code>true</code>
	 */
	public static <T> Constraint<T> and(Constraint<? super T> constraint1,
			Constraint<? super T> constraint2, Constraint<? super T> constraint3) {
		return new AndConstraint<T>().addConstraint(constraint1).addConstraint(
				constraint2).addConstraint(constraint3);
	}

}
