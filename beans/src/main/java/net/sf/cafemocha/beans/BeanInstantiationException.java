/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

/**
 * Thrown by the {@link BeanFactory} to indicate an error creating an instance
 * of a bean.
 * 
 * @author computerguy5
 * 
 */
public class BeanInstantiationException extends RuntimeException {
	private static final long serialVersionUID = -7072666520721103711L;

	/**
	 * Construct a new {@link BeanInstantiationException} with the specified
	 * <code>cause</code>.
	 * 
	 * @param cause
	 *            the cause of the exception
	 */
	public BeanInstantiationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct a new {@link BeanInstantiationException} with the specified
	 * <code>detailMessage</code> and <code>cause</code>.
	 * 
	 * @param detailMessage
	 *            message describing the problem
	 * @param cause
	 *            the cause of the exception
	 */
	public BeanInstantiationException(String detailMessage, Throwable cause) {
		super(detailMessage, cause);
	}

}
