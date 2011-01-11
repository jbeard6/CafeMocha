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
		this.propertyClass = null;
		this.propertyName = null;
	}

	/**
	 * Construct a new {@link PropertyException} about the specified property.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 */
	public PropertyException(Class<?> propertyClass, String propertyName) {
		this.propertyClass = propertyClass;
		this.propertyName = propertyName;
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
		this.propertyClass = null;
		this.propertyName = null;
	}

	/**
	 * Construct a new {@link PropertyException} about the specified property
	 * with the specified <code>message</code>.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 * @param message
	 *            message describing the exception
	 */
	public PropertyException(Class<?> propertyClass, String propertyName,
			String message) {
		super(message);
		this.propertyClass = propertyClass;
		this.propertyName = propertyName;
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
		this.propertyClass = null;
		this.propertyName = null;
	}

	/**
	 * Construct a new {@link PropertyException} about the specified property
	 * with the specified <code>cause</code>.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 * @param cause
	 *            the cause of the exception
	 */
	public PropertyException(Class<?> propertyClass, String propertyName,
			Throwable cause) {
		super(cause);
		this.propertyClass = propertyClass;
		this.propertyName = propertyName;
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
		this.propertyClass = null;
		this.propertyName = null;
	}

	/**
	 * Construct a new {@link PropertyException} about the specified property
	 * with the specified <code>detailMessage</code> and <code>cause</code>.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 * @param detailMessage
	 *            message describing the problem
	 * @param cause
	 *            the cause of the exception
	 */
	public PropertyException(Class<?> propertyClass, String propertyName,
			String detailMessage, Throwable cause) {
		super(detailMessage, cause);
		this.propertyClass = propertyClass;
		this.propertyName = propertyName;
	}

	private final Class<?> propertyClass;

	/**
	 * @return the class for which the property does not exist
	 */
	public Class<?> getPropertyClass() {
		return propertyClass;
	}

	private final String propertyName;

	/**
	 * @return the name of the property that does not exist
	 */
	public String getPropertyName() {
		return propertyName;
	}

}
