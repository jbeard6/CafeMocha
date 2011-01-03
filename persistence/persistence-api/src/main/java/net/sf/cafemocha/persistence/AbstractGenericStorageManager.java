/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

/**
 * Common base class for a {@link StorageManager} that allows only specific
 * breeds of {@link StorageProvider} instances.
 * 
 * @author computerguy5
 * 
 */
public abstract class AbstractGenericStorageManager<T extends StorageProvider>
		implements StorageManager {

	/**
	 * Construct an {@link AbstractGenericStorageManager} that uses the
	 * specified {@link StorageProvider}.
	 * 
	 * @param storageProvider
	 *            the storage provider with which objects will be written and
	 *            read
	 * @throws NullPointerException
	 *             if <code>storageProvider</code> is <code>null</code>
	 */
	public AbstractGenericStorageManager(T storageProvider) {
		if (storageProvider == null) {
			throw new NullPointerException("storageProvider");
		}
		this.storageProvider = storageProvider;
	}

	protected final T storageProvider;

	public T getStorageProvider() {
		return storageProvider;
	}

}
