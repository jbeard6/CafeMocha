/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

/**
 * Thrown to indicate that a specified property is read-only while attempting to
 * set its value.
 * 
 * @author computerguy5
 * 
 */
public class ReadOnlyPropertyException extends PropertyException {
	private static final long serialVersionUID = -3950428868651275162L;

	/**
	 * Construct a {@link ReadOnlyPropertyException} to indicate that the
	 * specified <code>propertyName</code> of the specified
	 * <code>propertyClass</code> is read-only.
	 * 
	 * @param propertyClass
	 *            the class for which the property is read-only
	 * @param propertyName
	 *            the name of the property
	 */
	public ReadOnlyPropertyException(Class<?> propertyClass, String propertyName) {
		super(propertyClass, propertyName, String.format(
				"Property %s of %s is read-only.", propertyName, propertyClass));
	}

	/**
	 * Construct a {@link ReadOnlyPropertyException} to indicate that the
	 * specified <code>propertyName</code> of the specified
	 * <code>propertyClass</code> is read-only.
	 * 
	 * @param propertyClass
	 *            the class for which the property is read-only
	 * @param propertyName
	 *            the name of the property
	 * @param cause
	 *            the exception thrown that indicated that the property is
	 *            read-only
	 */
	public ReadOnlyPropertyException(Class<?> propertyClass,
			String propertyName, Throwable cause) {
		super(propertyClass, propertyName,
				String.format("Property %s of %s is read-only.", propertyName,
						propertyClass), cause);
	}

}
