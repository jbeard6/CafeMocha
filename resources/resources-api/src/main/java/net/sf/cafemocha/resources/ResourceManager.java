/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * Manages access to resources.
 * 
 * @author computerguy5
 * 
 */
public interface ResourceManager {

	// TODO Manage creation of ResourceConverterFactories, etc.

	/**
	 * Returns a {@link ResourceMap} appropriate for the specified
	 * {@link Object}.
	 * 
	 * @param object
	 *            the object for which to return a resource map
	 * @return a resource map appropriate for the object
	 */
	public ResourceMap getResources(Object object);

}
