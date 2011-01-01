/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link StorageManager} that uses the {@link XMLDecoder} and
 * {@link XMLEncoder} classes to read and write objects. Objects written and
 * read by the {@link XMLStorageManager} must be supported by the
 * {@link XMLDecoder} and {@link XMLEncoder} classes: Java beans (default
 * constructor, getter/setter for all properties), primitives, and Collections.
 * 
 * TODO Figure out how to allow clients to use custom PersistenceDelegates
 * 
 * @author computerguy5
 * 
 */
public class XMLStorageManager implements StorageManager {

	private static final Logger LOG = LoggerFactory
			.getLogger(XMLStorageManager.class);

	public XMLStorageManager(StorageProvider storageProvider) {
		if (storageProvider == null) {
			throw new NullPointerException("storageProvider");
		}
		this.storageProvider = storageProvider;
	}

	private final StorageProvider storageProvider;

	public StorageProvider getStorageProvider() {
		return storageProvider;
	}

	private ThreadLocal<ExceptionHandler> exceptionHandler = new ThreadLocal<ExceptionHandler>() {

		@Override
		protected ExceptionHandler initialValue() {
			// Initialize on first call
			return new ExceptionHandler();
		}

	};

	public void writeObject(Object object, String name) throws IOException {
		// Get a handle to the output stream for the object
		OutputStream output = storageProvider.openOutput(name);

		// Use XMLEncoder to write object
		XMLEncoder encoder = new XMLEncoder(output);

		ExceptionHandler exceptionHandler = this.exceptionHandler.get();
		encoder.setExceptionListener(exceptionHandler);

		encoder.writeObject(object);
		encoder.close();

		// Take only the first exception off the list and reset for next time
		Exception ex = exceptionHandler.getFirstException();
		exceptionHandler.reset();

		if (ex != null) {
			// TODO More specific exception
			throw (IOException) new IOException("Write Exception")
					.initCause(ex);
		}
	}

	public <T> T readObject(String name) throws IOException {
		InputStream input = storageProvider.openInput(name);

		// Use XMLDecoder to read object
		XMLDecoder decoder = new XMLDecoder(input, this);

		Object object = decoder.readObject();
		decoder.close();

		// Will throw a ClassCastException which is permitted by the interface
		@SuppressWarnings("unchecked")
		T t = (T) object;

		return t;
	}

	private static class ExceptionHandler implements ExceptionListener {

		public ExceptionHandler() {
			// Use a linked list for speedy addition and access to first node
			this.exceptions = new LinkedList<Exception>();
		}

		private final List<Exception> exceptions;

		public Exception getFirstException() {
			return exceptions.isEmpty() ? null : exceptions.get(0);
		}

		public void reset() {
			exceptions.clear();
		}

		public void exceptionThrown(Exception ex) {
			LOG.debug("Received Error while Reading/Writing Object", ex);

			// Add it to the list
			exceptions.add(ex);
		}

	}
}
