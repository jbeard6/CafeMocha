/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * Thrown to indicate a failure by a {@link ResourceConverter} in converting an
 * object.
 * 
 * @author computerguy5
 */
public class ResourceConverterException extends RuntimeException {

	private static final long serialVersionUID = 1588184805227886522L;

	/**
	 * Construct a new {@link ResourceConverterException} with no detail message
	 * or cause.
	 */
	public ResourceConverterException() {
		super();
	}

	/**
	 * Construct a new {@link ResourceConverterException} with the specified
	 * detail <code>message</code>.
	 * 
	 * @param message
	 *            the detail message
	 */
	public ResourceConverterException(String message) {
		super(message);
	}

	/**
	 * Construct a new {@link ResourceConverterException} with the specified
	 * <code>cause</code>.
	 * 
	 * @param cause
	 *            the cause of the exception
	 */
	public ResourceConverterException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct a new {@link ResourceConverterException} with the specified
	 * detail <code>message</code> and <code>cause</code>.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause of the exception
	 */
	public ResourceConverterException(String message, Throwable cause) {
		super(message, cause);
	}

}
