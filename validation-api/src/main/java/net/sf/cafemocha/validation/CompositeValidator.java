/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author computerguy5
 * @param <T>
 *            the type of objects being validated
 */
public abstract class CompositeValidator<T> implements Validator {

	public CompositeValidator() {
		// Use CopyOnWriteArrayList to make validation iteration easier/faster
		this.constraints = new CopyOnWriteArrayList<Constraint<? super T>>();
	}

	private final List<Constraint<? super T>> constraints;

	public CompositeValidator<T> addConstraint(Constraint<? super T> constraint) {
		constraints.add(constraint);

		return this;
	}

	public boolean removeConstraint(Constraint<? super T> constraint) {
		return constraints.remove(constraint);
	}

	public final void validate(Object obj) throws IllegalArgumentException {
		T t = convert(obj);

		// Allow each constraint to validate
		for (Constraint<? super T> constraint : constraints) {
			constraint.validate(t);
		}
	}

	/**
	 * Validate that the specified {@link Object} is an instance of
	 * <code>T</code> or can be converted to an instance of <code>T</code>.
	 * 
	 * @param object
	 *            the object to be validated as a T
	 * @return a T that represents the supplied object
	 * @throws IllegalArgumentException
	 *             if the object can not be converted to a T
	 */
	protected abstract T convert(Object object) throws IllegalArgumentException;

}
