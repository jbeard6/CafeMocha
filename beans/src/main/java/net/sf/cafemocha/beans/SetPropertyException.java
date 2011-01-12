/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

/**
 * Thrown to indicate an error setting the value of a property.
 * 
 * @author computerguy5
 * 
 */
public class SetPropertyException extends PropertyException {
	private static final long serialVersionUID = 2772170290540936721L;

	/**
	 * Construct a {@link SetPropertyException}.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 * @param cause
	 *            the cause of the exception
	 */
	public SetPropertyException(Class<?> propertyClass, String propertyName,
			Throwable cause) {
		super(propertyClass, propertyName, String.format(
				"Error setting property value.", propertyName, propertyClass),
				cause);
	}

}
