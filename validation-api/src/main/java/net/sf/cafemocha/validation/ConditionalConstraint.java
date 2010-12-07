/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation;

/**
 * Performs validation of a {@link Constraint} if a condition is met.
 * 
 * @author computerguy5
 * 
 */
public abstract class ConditionalConstraint<T> implements Constraint<T> {

	/**
	 * Construct a new {@link ConditionalConstraint} that executes the
	 * {@link Constraint constraint} if the condition is <code>true</code>.
	 * 
	 * @param constraint
	 *            the constraint to be conditionally used if the condition is
	 *            <code>true</code>
	 * @throws NullPointerException
	 *             if <code>constraint</code> is <code>null</code>
	 */
	public ConditionalConstraint(Constraint<? super T> constraint) {
		this(constraint, null);
	}

	/**
	 * Construct a new {@link ConditionalConstraint} that executes either the
	 * {@link Constraint constraint} or the {@link Constraint elseConstraint} if
	 * the condition is <code>true</code> or <code>false</code>, respectively.
	 * 
	 * @param constraint
	 *            the constraint to be conditionally used if the condition is
	 *            <code>true</code>
	 * @param elseConstraint
	 *            the optional constraint to be conditionally used if the
	 *            condition is <code>false</code>
	 * @throws NullPointerException
	 *             if <code>constraint</code> is <code>null</code>
	 */
	public ConditionalConstraint(Constraint<? super T> constraint,
			Constraint<? super T> elseConstraint) {
		if (constraint == null) {
			throw new NullPointerException("constraint");
		}
		this.constraint = constraint;
		this.elseConstraint = elseConstraint;
	}

	protected final Constraint<? super T> constraint;

	protected final Constraint<? super T> elseConstraint;

	public final void validate(T t) throws IllegalArgumentException {
		if (predicate(t)) {
			constraint.validate(t);
		} else if (elseConstraint != null) {
			elseConstraint.validate(t);
		}
	}

	/**
	 * Returns <code>true</code> if the {@link Constraint} should be validated.
	 * 
	 * @param t
	 *            the object to be validated
	 * @return <code>true</code> if the {@link Constraint} should be validated
	 */
	protected abstract boolean predicate(T t);

}
