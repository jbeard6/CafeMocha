/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * Thrown to indicate that no appropriate {@link ResourceConverter} was
 * available to convert a given resource.
 * 
 * @author computerguy5
 */
public class MissingConverterException extends ResourceConverterException {

	private static final long serialVersionUID = -2096430740916066108L;

	/**
	 * Construct a new {@link MissingConverterException} with no detail message
	 * or cause.
	 */
	public MissingConverterException() {
	}

	/**
	 * Construct a new {@link MissingConverterException} with the specified
	 * <code>message</code>.
	 * 
	 * @param message
	 *            the detail message
	 */
	public MissingConverterException(String message) {
		super(message);
	}

	/**
	 * Construct a new {@link MissingConverterException} with the specified
	 * <code>cause</code>.
	 * 
	 * @param cause
	 *            the cause of the exception
	 */
	public MissingConverterException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct a new {@link MissingConverterException} with the specified
	 * detail <code>message</code> and <code>cause</code>.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause of the exception
	 */
	public MissingConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	private Class<?> fromClass;

	/**
	 * @return the fromClass
	 */
	public Class<?> getFromClass() {
		return fromClass;
	}

	/**
	 * Set the <code>fromClass</code> if it has not yet been set.
	 * 
	 * @param fromClass
	 *            the fromClass to set
	 * @return this {@link MissingConverterException}
	 */
	public MissingConverterException initFromClass(Class<?> fromClass) {
		if (this.fromClass == null && fromClass != null) {
			this.fromClass = fromClass;
		}

		return this;
	}

	private Class<?> toClass;

	/**
	 * @return the toClass
	 */
	public Class<?> getToClass() {
		return toClass;
	}

	/**
	 * Set the <code>toClass</code> if it has not yet been set.
	 * 
	 * @param toClass
	 *            the toClass to set
	 * @return this {@link MissingConverterException}
	 */
	public MissingConverterException initToClass(Class<?> toClass) {
		if (this.toClass == null && toClass != null) {
			this.toClass = toClass;
		}

		return this;
	}

}
