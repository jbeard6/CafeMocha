/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A {@link StorageProvider} that persists data in named {@link File files}.
 * 
 * TODO Ponder good resource naming strategy; for now, names treated directly
 * 
 * @author computerguy5
 * 
 */
public class FileStorageProvider implements StorageProvider {

	/**
	 * Construct a new {@link FileStorageProvider} that persists in the
	 * specified {@link File directory}.
	 * 
	 * @param root
	 *            the root directory
	 * @throws IllegalArgumentException
	 *             i <code>root</code> {@link File#exists() exists} but is not a
	 *             {@link File#isDirectory() directory}
	 */
	public FileStorageProvider(File root) {
		if (root != null && root.exists() && !root.isDirectory()) {
			throw new IllegalArgumentException("Not a directory");
		}
		this.root = root;
	}

	private final File root;

	private File getFile(String resourceName) {
		return new File(root, resourceName);
	}

	public InputStream openInput(String resourceName) throws IOException {
		return new FileInputStream(getFile(resourceName));
	}

	public OutputStream openOutput(String resourceName) throws IOException {
		return new FileOutputStream(getFile(resourceName));
	}

	public boolean exists(String resourceName) throws IOException {
		return getFile(resourceName).exists();
	}

	public boolean delete(String resourceName) throws IOException {
		File resource = getFile(resourceName);

		return resource.delete();
	}

}
