/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Uses buffered input and output to an underlying {@link StorageProvider}.
 * 
 * @author computerguy5
 * 
 */
public class BufferedStorageProvider implements StorageProvider {

	/**
	 * @param storageProvider
	 *            the underlying storage provider
	 * @throws NullPointerException
	 *             if <code>storageProvider</code> is <code>null</code>
	 */
	public BufferedStorageProvider(StorageProvider storageProvider) {
		this.storageProvider = storageProvider;
	}

	private final StorageProvider storageProvider;

	/**
	 * Returns the underlying {@link StorageProvider}.
	 * 
	 * @return the underlying storage provider
	 */
	public StorageProvider getStorageProvider() {
		return storageProvider;
	}

	public BufferedInputStream openInput(String resourceName)
			throws IOException {
		InputStream input = storageProvider.openInput(resourceName);

		return new BufferedInputStream(input);
	}

	public BufferedOutputStream openOutput(String resourceName)
			throws IOException {
		OutputStream output = storageProvider.openOutput(resourceName);

		return new BufferedOutputStream(output);
	}

	public boolean exists(String resourceName) throws IOException {
		return storageProvider.exists(resourceName);
	}

	public boolean delete(String resourceName) throws IOException {
		return storageProvider.delete(resourceName);
	}

}
