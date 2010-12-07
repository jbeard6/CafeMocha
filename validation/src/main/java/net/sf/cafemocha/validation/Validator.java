/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation;

/**
 * Provides methods for validating user supplied input.
 * 
 * @author computerguy5
 * 
 */
public interface Validator {

	/**
	 * Performs validation of the provided object.
	 * 
	 * @param obj
	 *            the <code>Object</code> to be validated
	 * @throws IllegalArgumentException
	 *             when validation fails
	 */
	public abstract void validate(Object obj) throws IllegalArgumentException;
}
