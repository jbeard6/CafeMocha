/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

/**
 * Thrown to indicate that a specified property is write-only while attempting
 * to read its value.
 * 
 * @author computerguy5
 * 
 */
public class WriteOnlyPropertyException extends PropertyException {
	private static final long serialVersionUID = 2381175447145558975L;

	/**
	 * Construct a {@link WriteOnlyPropertyException} to indicate that the
	 * specified <code>propertyName</code> of the specified
	 * <code>propertyClass</code> is write-only.
	 * 
	 * @param propertyClass
	 *            the class for which the property is write-only
	 * @param propertyName
	 *            the name of the property
	 */
	public WriteOnlyPropertyException(Class<?> propertyClass,
			String propertyName) {
		super(propertyClass, propertyName, String
				.format("Property %s of %s is write-only.", propertyName,
						propertyClass));
	}

	/**
	 * Construct a {@link WriteOnlyPropertyException} to indicate that the
	 * specified <code>propertyName</code> of the specified
	 * <code>propertyClass</code> is write-only.
	 * 
	 * @param propertyClass
	 *            the class for which the property is write-only
	 * @param propertyName
	 *            the name of the property
	 * @param cause
	 *            the exception thrown that indicated that the property is
	 *            write-only
	 */
	public WriteOnlyPropertyException(Class<?> propertyClass,
			String propertyName, Throwable cause) {
		super(propertyClass, propertyName, String
				.format("Property %s of %s is write-only.", propertyName,
						propertyClass), cause);
	}

}
