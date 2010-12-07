/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.string;

import net.sf.cafemocha.validation.Constraint;

import org.apache.commons.lang.StringUtils;

/**
 * Removes a {@link String} from the value prior to forwarding to another
 * {@link Constraint}.
 * 
 * @author computerguy5
 * 
 */
public class RemoveStringConstraint extends ModifyStringConstraint {

	public static final String WILDCARD = "%";

	/**
	 * Returns a {@link RemoveStringConstraint} that executes the specified
	 * {@link Constraint} with all occurrences of the {@link #WILDCARD} removed.
	 * 
	 * @param constraint
	 *            the constraint to use on the value without wildcards
	 * @return constraint that validates the value without wildcards
	 */
	public static RemoveStringConstraint removeWildcards(
			Constraint<? super String> constraint) {
		return new RemoveStringConstraint(constraint, WILDCARD);
	}

	public static RemoveStringConstraint remove(String remove,
			Constraint<? super String> constraint) {
		return new RemoveStringConstraint(constraint, remove);
	}

	/**
	 * Construct a new {@link RemoveStringConstraint}.
	 * 
	 * @param constraint
	 *            the constraint to execute with the mutated value
	 * @param remove
	 *            the string to remove from the value
	 * @throws NullPointerException
	 *             if the <code>constraint</code> is <code>null</code>
	 * @throws NullPointerException
	 *             if the <code>remove</code> string is <code>null</code>
	 */
	public RemoveStringConstraint(Constraint<? super String> constraint,
			String remove) {
		super(constraint);

		if (remove == null) {
			throw new NullPointerException("remove");
		}

		this.remove = remove;
	}

	private final String remove;

	@Override
	protected String mutate(String t) {
		return StringUtils.remove(t, remove);
	}

}
