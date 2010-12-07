/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A composite {@link Constraint}.
 * 
 * @author computerguy5
 * 
 */
public abstract class CompositeConstraint<T> implements Constraint<T> {

	public CompositeConstraint() {
		// Use CopyOnWriteArrayList to make validation iteration easier/faster
		this.constraints = new CopyOnWriteArrayList<Constraint<? super T>>();
	}

	/**
	 * Subclasses should not modify this list directly.
	 */
	protected final List<Constraint<? super T>> constraints;

	public CompositeConstraint<T> addConstraint(Constraint<? super T> constraint) {
		constraints.add(constraint);

		return this;
	}

	public boolean removeConstraint(Constraint<? super T> constraint) {
		return constraints.remove(constraint);
	}

	public CompositeConstraint<T> addAll(
			Collection<Constraint<? super T>> constraints) {
		constraints.addAll(constraints);

		return this;
	}

}
