/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

/**
 * Common base class for a {@link StorageManager}.
 * 
 * @author computerguy5
 * 
 */
public abstract class AbstractStorageManager implements StorageManager {

	/**
	 * Construct an {@link AbstractStorageManager} that uses the specified
	 * {@link StorageProvider}.
	 * 
	 * @param storageProvider
	 *            the storage provider with which objects will be written and
	 *            read
	 * @throws NullPointerException
	 *             if <code>storageProvider</code> is <code>null</code>
	 */
	public AbstractStorageManager(StorageProvider storageProvider) {
		if (storageProvider == null) {
			throw new NullPointerException("storageProvider");
		}
		this.storageProvider = storageProvider;
	}

	protected final StorageProvider storageProvider;

	public StorageProvider getStorageProvider() {
		return storageProvider;
	}

}
