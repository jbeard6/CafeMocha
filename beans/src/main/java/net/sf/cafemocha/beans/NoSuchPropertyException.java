/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

/**
 * Thrown to indicate that a specified property does not exist.
 * 
 * @author computerguy5
 * 
 */
public class NoSuchPropertyException extends PropertyException {
	private static final long serialVersionUID = 5857523263013054171L;

	/**
	 * Construct a {@link NoSuchPropertyException} to indicate that the
	 * specified <code>propertyName</code> of the specified
	 * <code>propertyClass</code> does not exist.
	 * 
	 * @param propertyClass
	 *            the class for which the property does not exist
	 * @param propertyName
	 *            the name of the property that does not exist
	 * @param cause
	 *            the exception thrown that indicated that the property does not
	 *            exist
	 */
	public NoSuchPropertyException(Class<?> propertyClass, String propertyName,
			Throwable cause) {
		super(String.format("Property %s of %s does not exist.", propertyName,
				propertyClass), cause);
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
