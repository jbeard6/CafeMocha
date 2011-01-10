/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

/**
 * Thrown by the {@link Properties Property} utilities to indicate an error
 * getting or setting the value of a property.
 * 
 * @author computerguy5
 * 
 */
public class PropertyException extends RuntimeException {
	private static final long serialVersionUID = 2150600450327066400L;

	/**
	 * Construct a new {@link PropertyException}.
	 */
	public PropertyException() {
	}

	/**
	 * Construct a new {@link PropertyException} with the specified
	 * <code>message</code>.
	 * 
	 * @param message
	 *            message describing the exception
	 */
	public PropertyException(String message) {
		super(message);
	}

	/**
	 * Construct a new {@link PropertyException} with the specified
	 * <code>cause</code>.
	 * 
	 * @param cause
	 *            the cause of the exception
	 */
	public PropertyException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct a new {@link PropertyException} with the specified
	 * <code>detailMessage</code> and <code>cause</code>.
	 * 
	 * @param detailMessage
	 *            message describing the problem
	 * @param cause
	 *            the cause of the exception
	 */
	public PropertyException(String detailMessage, Throwable cause) {
		super(detailMessage, cause);
	}

}
