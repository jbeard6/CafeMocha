/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation;

/**
 * Validates that the object is not <code>null</code>.
 * 
 * @author computerguy5
 * 
 */
public class RequiredConstraint implements Constraint<Object> {

	private static final RequiredConstraint INSTANCE = new RequiredConstraint();

	public static RequiredConstraint required() {
		return INSTANCE;
	}

	/**
	 * Construct a new {@link RequiredConstraint}.
	 */
	public RequiredConstraint() {
		this.invalidMessage = "A value is required.";
	}

	/**
	 * Construct a new {@link RequiredConstraint} that uses the provided message
	 * in the {@link IllegalArgumentException} if the object to be validated is
	 * <code>null</code>.
	 * 
	 * @param invalidMessage
	 *            the exception message if the object to be validated is
	 *            <code>null</code>
	 */
	public RequiredConstraint(String invalidMessage) {
		this.invalidMessage = invalidMessage;
	}

	private final String invalidMessage;

	public void validate(Object t) throws IllegalArgumentException {
		if (t == null) {
			throw new IllegalArgumentException(invalidMessage);
		}
	}

}
