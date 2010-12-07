/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.string;

import net.sf.cafemocha.validation.Constraint;

/**
 * Modifies a {@link String} before sending it to another {@link Constraint} to
 * be validated.
 * 
 * @author computerguy5
 * 
 */
public abstract class ModifyStringConstraint implements Constraint<String> {

	/**
	 * Construct a new {@link ModifyStringConstraint} that modifies a
	 * {@link String} prior to validation.
	 * 
	 * @param constraint
	 *            the constraint to execute with the mutated value
	 * @throws NullPointerException
	 *             if the <code>constraint</code> is <code>null</code>
	 */
	public ModifyStringConstraint(Constraint<? super String> constraint) {
		if (constraint == null) {
			throw new NullPointerException("constraint");
		}

		this.constraint = constraint;
	}

	private final Constraint<? super String> constraint;

	public final void validate(String t) throws IllegalArgumentException {
		constraint.validate(mutate(t));
	}

	/**
	 * Modify the value before validation.
	 * 
	 * @param t
	 *            the value to be modified
	 * @return the modified value
	 */
	protected abstract String mutate(String t);

}
