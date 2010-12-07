/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation.string;

import net.sf.cafemocha.validation.ConditionalConstraint;
import net.sf.cafemocha.validation.Constraint;

import org.apache.commons.lang.StringUtils;

/**
 * Conditionally executes a {@link Constraint} if the supplied {@link String}
 * contains a specified search {@link String}.
 * 
 * @author computerguy5
 * 
 */
public class ContainsStringConstraint extends ConditionalConstraint<String> {

	public static final String WILDCARD = "%";

	/**
	 * Returns a {@link ConditionalConstraint} that executes the specified
	 * {@link Constraint} if the value contains the {@link #WILDCARD}.
	 * 
	 * @param constraint
	 *            the constraint to use if the search contains the wildcard
	 * @return conditional constraint for the wildcard
	 */
	public static ContainsStringConstraint containsWildcard(
			Constraint<? super String> constraint) {
		return new ContainsStringConstraint(constraint, WILDCARD);
	}

	/**
	 * Returns a {@link ConditionalConstraint} that executes either the
	 * {@link Constraint constraint} or the {@link Constraint elseConstraint} if
	 * the value contains the {@link #WILDCARD} or not, respectively.
	 * 
	 * @param constraint
	 *            the constraint to use if the search contains the wildcard
	 * @return conditional constraint for the wildcard
	 */
	public static ContainsStringConstraint containsWildcard(
			Constraint<? super String> constraint,
			Constraint<? super String> elseConstraint) {
		return new ContainsStringConstraint(constraint, elseConstraint,
				WILDCARD);
	}

	public static ContainsStringConstraint contains(String searchString,
			Constraint<? super String> constraint,
			Constraint<? super String> elseConstraint) {
		return new ContainsStringConstraint(constraint, elseConstraint,
				searchString);
	}

	/**
	 * Construct a new {@link ContainsStringConstraint} that executes the
	 * {@link Constraint constraint} if the condition is <code>true</code>.
	 * 
	 * @param constraint
	 *            the constraint to execute if the value contains the search
	 *            string
	 * @param searchString
	 *            the string to search for
	 * @throws NullPointerException
	 *             if <code>constraint</code> is <code>null</code>
	 * @throws NullPointerException
	 *             if <code>searchString</code> is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if <code>searchString</code> is the empty string
	 */
	public ContainsStringConstraint(Constraint<? super String> constraint,
			String searchString) {
		this(constraint, null, searchString);
	}

	/**
	 * Construct a new {@link ContainsStringConstraint} that executes the
	 * {@link Constraint constraint} if the condition is <code>true</code>.
	 * 
	 * @param constraint
	 *            the constraint to execute if the value contains the search
	 *            string
	 * @param elseConstraint
	 *            the constraint to execute if the value does not contain the
	 *            search string
	 * @param searchString
	 *            the string to search for
	 * @throws NullPointerException
	 *             if <code>constraint</code> is <code>null</code>
	 * @throws NullPointerException
	 *             if <code>searchString</code> is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if <code>searchString</code> is the empty string
	 */
	public ContainsStringConstraint(Constraint<? super String> constraint,
			Constraint<? super String> elseConstraint, String searchString) {
		super(constraint, elseConstraint);

		if (searchString == null) {
			throw new NullPointerException("searchString");
		} else if (searchString.length() == 0) {
			throw new IllegalArgumentException("searchString may not be empty");
		}

		this.searchString = searchString;
	}

	private final String searchString;

	@Override
	protected boolean predicate(String t) {
		return StringUtils.contains(t, searchString);
	}

}
