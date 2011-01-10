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
		super(String.format("Property %s of %s is read-only.", propertyName,
				propertyClass));
		this.propertyClass = propertyClass;
		this.propertyName = propertyName;
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
		super(String.format("Property %s of %s is read-only.", propertyName,
				propertyClass), cause);
		this.propertyClass = propertyClass;
		this.propertyName = propertyName;
	}

	private final Class<?> propertyClass;

	/**
	 * @return the class for which the property is read-only
	 */
	public Class<?> getPropertyClass() {
		return propertyClass;
	}

	private final String propertyName;

	/**
	 * @return the name of the property that is read-only
	 */
	public String getPropertyName() {
		return propertyName;
	}

}
