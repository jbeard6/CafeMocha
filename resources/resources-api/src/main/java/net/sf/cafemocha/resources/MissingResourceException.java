/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * Thrown to indicate that a requested resource does not exist.
 * 
 * @author computerguy5
 * 
 */
public class MissingResourceException extends RuntimeException {

	private static final long serialVersionUID = 5688656753875867474L;

	/**
	 * Construct a new {@link MissingResourceException}.
	 */
	public MissingResourceException() {
		super();
	}

	/**
	 * Construct a new {@link MissingResourceException} with the specified
	 * <code>message</code>.
	 * 
	 * @param message
	 *            the detail message
	 */
	public MissingResourceException(String message) {
		super(message);
	}

	/**
	 * Construct a new {@link MissingResourceException} with the specified
	 * <code>cause</code>.
	 * 
	 * @param cause
	 *            the cause of the exception
	 */
	public MissingResourceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct a new {@link MissingResourceException} with the specified
	 * <code>message</code> and <code>cause</code>.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause of the exception
	 */
	public MissingResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	private ResourceMap resourceMap;

	/**
	 * Returns the {@link ResourceMap} that did not contain the resource.
	 * 
	 * @return the resource map that did not contain the resource
	 */
	public ResourceMap getResourceMap() {
		return resourceMap;
	}

	/**
	 * Initializes the {@link ResourceMap} that did not contain the resource.
	 * 
	 * @param resourceMap
	 *            the resource map that did not contain the resource
	 * @return this exception
	 */
	public MissingResourceException initResourceMap(ResourceMap resourceMap) {
		if (this.resourceMap == null && resourceMap != null) {
			this.resourceMap = resourceMap;
		}

		return this;
	}

	private String resourceName;

	/**
	 * Returns the name of the missing resource.
	 * 
	 * @return the name of the missing resource
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Initializes the name of the missing resource if it has not yet been set.
	 * 
	 * @param resourceName
	 *            the name of the missing resource
	 * @return this exception
	 */
	public MissingResourceException initResourceName(String resourceName) {
		if (this.resourceName == null && resourceName != null) {
			this.resourceName = resourceName;
		}

		return this;
	}

}
