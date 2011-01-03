/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence.xml;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import net.sf.cafemocha.persistence.AbstractStorageManager;
import net.sf.cafemocha.persistence.StorageManager;
import net.sf.cafemocha.persistence.StorageProvider;

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
public class XMLStorageManager extends AbstractStorageManager {

	private static final Logger LOG = LoggerFactory
			.getLogger(XMLStorageManager.class);

	/**
	 * Construct an {@link XMLStorageManager} that uses the specified
	 * {@link StorageProvider}.
	 * 
	 * @param storageProvider
	 *            the storage provider with which objects will be written and
	 *            read
	 * @throws NullPointerException
	 *             if <code>storageProvider</code> is <code>null</code>
	 */
	public XMLStorageManager(StorageProvider storageProvider) {
		super(storageProvider);
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

		try {
			encoder.writeObject(object);
			encoder.close();

			// Take only the first exception off the list
			Exception ex = exceptionHandler.getFirstException();

			if (ex != null) {
				throw new XMLEncodingException(
						"Failed to convert object to XML").initCause(ex)
						.initEncoder(encoder);
			}
		} finally {
			// Reset for next invocation
			encoder.setExceptionListener(null);
			exceptionHandler.reset();
		}
	}

	public <T> T readObject(String name) throws IOException {
		InputStream input = storageProvider.openInput(name);

		// Use XMLDecoder to read object
		XMLDecoder decoder = new XMLDecoder(input, this);

		ExceptionHandler exceptionHandler = this.exceptionHandler.get();
		decoder.setExceptionListener(exceptionHandler);

		try {
			Object object = decoder.readObject();
			decoder.close();

			// Take only the first exception off the list
			Exception ex = exceptionHandler.getFirstException();

			if (ex != null) {
				throw new XMLDecodingException("Failed to read object from XML")
						.initCause(ex).initDecoder(decoder);
			}

			// ClassCastException is permitted by the interface
			@SuppressWarnings("unchecked")
			T t = (T) object;

			return t;
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new XMLDecodingException("No object defined in resource")
					.initCause(ex).initDecoder(decoder);
		} catch (NoSuchElementException ex) {
			/*
			 * XMLDecoder Javadoc says ArrayIndexOutOfBounds exception will be
			 * thrown if no objects are defined, but actual experience indicates
			 * a NoSuchElementException is thrown.
			 */
			throw new XMLDecodingException("No object defined in resource")
					.initCause(ex).initDecoder(decoder);
		} finally {
			// Reset for next invocation
			decoder.setExceptionListener(null);
			exceptionHandler.reset();
		}
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
