/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Abstraction for a method of storing data.
 * 
 * @author computerguy5
 * 
 */
public interface StorageProvider {

	/**
	 * Open the named resource for input.
	 * 
	 * @param resourceName
	 *            the name of the resource to open
	 * @return the resource opened as an input stream
	 * @throws NullPointerException
	 *             if the <code>resourceName</code> is <code>null</code>
	 * @throws IOException
	 *             if an error occurs while opening the resource
	 */
	public InputStream openInput(String resourceName) throws IOException;

	/**
	 * Open the named resource for output.
	 * 
	 * @param resourceName
	 *            the name of the resource to open
	 * @return the resource opened as an output stream
	 * @throws NullPointerException
	 *             if the <code>resourceName</code> is <code>null</code>
	 * @throws IOException
	 *             if an error occurs while opening the resource
	 */
	public OutputStream openOutput(String resourceName) throws IOException;

	/**
	 * Returns <code>true</code> if the resource with the specified
	 * <code>resourceName</code> exists.
	 * 
	 * @param resourceName
	 *            the name of the resource
	 * @return <code>true</code> if the resource exists
	 * @throws IOException
	 *             if an error occurs while checking if the resource exists
	 */
	public boolean exists(String resourceName) throws IOException;

	/**
	 * Delete the named resource.
	 * 
	 * @param resourceName
	 *            the name of the resource to delete
	 * @return <code>true</code> if the resource was deleted
	 * @throws NullPointerException
	 *             if the <code>resourceName</code> is <code>null</code>
	 * @throws IOException
	 *             if an error occurs while attempting to delete the resource
	 */
	public boolean delete(String resourceName) throws IOException;

}
