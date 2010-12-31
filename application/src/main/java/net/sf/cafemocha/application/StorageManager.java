/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application;

import java.io.IOException;

import net.sf.cafemocha.persistence.StorageProvider;

/**
 * Manages the storage and retrieval of objects.
 * 
 * @author computerguy5
 * 
 */
public interface StorageManager {

	/**
	 * Returns the {@link StorageProvider} to which objects will be written and
	 * from which objects will be read.
	 * 
	 * @return the backing storage provider
	 */
	public StorageProvider getStorageProvider();

	/**
	 * Writes the object to the {@link StorageProvider} with the given
	 * <code>name</code>.
	 * 
	 * @param object
	 *            the object to be written
	 * @param name
	 *            the name with which the object should be written
	 * @throws IOException
	 *             if an error occurs while writing the object
	 */
	public void writeObject(Object object, String name) throws IOException;

	/**
	 * Read the object of type <code>T</code> with the specified
	 * <code>name</code>.
	 * 
	 * @param <T>
	 *            the type of object
	 * @param name
	 *            the name of the object to be read
	 * @return the object with the specified name
	 * @throws IOException
	 *             if an error occurs while reading the object
	 * @throws ClassCastException
	 *             if the object is not an instance of <code>T</code>
	 */
	public <T> T readObject(String name) throws IOException;

}
