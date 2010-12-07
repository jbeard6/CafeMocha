/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * An encapsulation of resources that can be obtained by name.
 * <p>
 * An individual {@link ResourceMap} provides access to all of the resources
 * contained within the individual {@link ResourceMap} as well as all of its
 * parent {@link ResourceMap ResourceMaps}. Resources are retrieved with the
 * {@link #getObject(String, Class)} method which requires both the name of the
 * resource and its expected type.
 * 
 * @author computerguy5
 */
public interface ResourceMap {

	/**
	 * Returns the parent {@link ResourceMap} from which resources will be
	 * obtained in they are unable to be located in this {@link ResourceMap}
	 * directly.
	 * 
	 * @return the parent {@link ResourceMap}
	 */
	public ResourceMap getParent();

	/**
	 * Returns <code>true</code> if this {@link ResourceMap} or its parent
	 * {@link ResourceMap} (recursively) contains the specified <code>key</code>
	 * .
	 * 
	 * @param key
	 *            the key for which to test existence
	 * @return <code>true</code> if the resource map contains the specified
	 *         <code>key</code>
	 */
	public boolean containsKey(String key);

	/**
	 * Returns the {@link Object} identified by the specified <code>key</code>
	 * as a <code>T</code>. The {@link Class} parameter is used to locate the
	 * appropriate {@link ResourceConverter}.
	 * 
	 * @param <T>
	 *            the type of object to obtain
	 * @param key
	 *            the key for which to obtain the object
	 * @param clazz
	 *            the {@link Class} for which to obtain an appropriate
	 *            {@link ResourceConverter}
	 * @return the {@link Object} identified by the specified <code>key</code>
	 * @throws MissingResourceException
	 *             if the resource is not present
	 * @throws ResourceConverterException
	 *             if the resource can not be converted into an object of type
	 *             <code>T</code>
	 */
	public <T> T getObject(String key, Class<? extends T> clazz)
			throws MissingResourceException, ResourceConverterException;

}
