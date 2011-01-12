/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

/**
 * Thrown to indicate that a property is not accessible because it is not
 * public.
 * 
 * @author computerguy5
 * 
 */
public class InaccessiblePropertyException extends PropertyException {
	private static final long serialVersionUID = -21867774084868415L;

	/**
	 * Construct a new {@link InaccessiblePropertyException} about the specified
	 * property.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 */
	public InaccessiblePropertyException(Class<?> propertyClass,
			String propertyName) {
		super(propertyClass, propertyName);
	}

	/**
	 * Construct a new {@link InaccessiblePropertyException} about the specified
	 * property.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 * @param message
	 *            message describing the exception
	 */
	public InaccessiblePropertyException(Class<?> propertyClass,
			String propertyName, String message) {
		super(propertyClass, propertyName, message);
	}

	/**
	 * Construct a new {@link InaccessiblePropertyException} about the specified
	 * property.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 * @param cause
	 *            the cause of the exception
	 */
	public InaccessiblePropertyException(Class<?> propertyClass,
			String propertyName, Throwable cause) {
		super(propertyClass, propertyName, cause);
	}

	/**
	 * Construct a new {@link InaccessiblePropertyException} about the specified
	 * property.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 * @param detailMessage
	 *            message describing the exception
	 * @param cause
	 *            the cause of the exception
	 */
	public InaccessiblePropertyException(Class<?> propertyClass,
			String propertyName, String detailMessage, Throwable cause) {
		super(propertyClass, propertyName, detailMessage, cause);
	}

}
