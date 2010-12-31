/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Storage that persists for the lifetime of the {@link VolatileStorageProvider}
 * object.
 * 
 * @author computerguy5
 * 
 */
public class VolatileStorageProvider implements StorageProvider {

	public VolatileStorageProvider() {
		resources = new HashMap<String, Resource>();
	}

	private final Map<String, Resource> resources;

	private Resource getOrCreateResource(String resourceName) {
		Resource resource;

		// Ensure atomic fetching of resource
		synchronized (resources) {
			resource = resources.get(resourceName);

			if (resource == null) {
				resource = new Resource();
				resources.put(resourceName, resource);
			}
		}

		return resource;
	}

	public InputStream openInput(String resourceName) {
		return getOrCreateResource(resourceName).openInput();
	}

	public OutputStream openOutput(String resourceName) {
		return getOrCreateResource(resourceName).openOutput();
	}

	public boolean exists(String resourceName) {
		synchronized (resources) {
			return resources.containsKey(resourceName);
		}
	}

	public boolean delete(String resourceName) {
		Resource resource;

		// Ensure atomic removal of resource
		synchronized (resources) {
			resource = resources.remove(resourceName);
		}

		if (resource != null) {
			resource.free();
			return true;
		}

		return false;
	}

	public void deleteAll() {
		synchronized (resources) {
			for (Iterator<Resource> i = resources.values().iterator(); i
					.hasNext();) {
				Resource resource = i.next();

				// Free the resource data
				resource.free();

				// And remove the mapping
				i.remove();
			}
		}
	}

	private static class Resource {

		public Resource() {
			// Initialize data to empty array for reading
			data = new byte[0];
		}

		private byte[] data;

		public void free() {
			// Clean up data resources
			data = null;
		}

		public InputStream openInput() {
			// Open an InputStream for data
			return new ByteArrayInputStream(data);
		}

		public OutputStream openOutput() {
			// Open OutputStream
			return new ResourceOutputStream(this);
		}

	}

	/**
	 * A specialized {@link ByteArrayOutputStream} that writes data for a
	 * {@link Resource}.
	 * 
	 * @author computerguy5
	 * 
	 */
	private static class ResourceOutputStream extends ByteArrayOutputStream {

		/**
		 * Construct a new {@link ResourceOutputStream} that writes to the
		 * specified {@link Resource}.
		 * 
		 * @param resource
		 *            the resource being written to
		 */
		public ResourceOutputStream(Resource resource) {
			this.resource = resource;
		}

		private final Resource resource;

		/**
		 * Closing a ResourceOutputStream saves the written data.
		 * 
		 * @see java.io.ByteArrayOutputStream#close()
		 */
		@Override
		public void close() throws IOException {
			resource.data = toByteArray();
		}

	}

}
