/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

/**
 * Thrown to indicate an error retrieving the value of a property.
 * 
 * @author computerguy5
 * 
 */
public class GetPropertyException extends PropertyException {
	private static final long serialVersionUID = 7649414989769834485L;

	/**
	 * Construct a {@link GetPropertyException}.
	 * 
	 * @param propertyClass
	 *            the class in which the property exists
	 * @param propertyName
	 *            the name of the property
	 * @param cause
	 *            the cause of the exception
	 */
	public GetPropertyException(Class<?> propertyClass, String propertyName,
			Throwable cause) {
		super(propertyClass, propertyName, String
				.format("Error retrieving property value.", propertyName,
						propertyClass), cause);
	}

}
