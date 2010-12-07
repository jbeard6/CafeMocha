/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.validation;

/**
 * Describes a constraint that an object being validated must meet.
 * 
 * @author computerguy5
 * @param <T>
 *            the type of object being validated
 * 
 */
public interface Constraint<T> {

	public void validate(T t) throws IllegalArgumentException;

}
