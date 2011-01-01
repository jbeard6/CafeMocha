/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence.xml;

import java.beans.XMLEncoder;
import java.io.IOException;

/**
 * Thrown to indicate an error while attempting to encode an object as XML using
 * an {@link XMLEncoder}.
 * 
 * @author computerguy5
 * 
 */
public class XMLEncodingException extends IOException {
	private static final long serialVersionUID = -806493407843064058L;

	/**
	 * Construct a new {@link XMLEncodingException}.
	 */
	public XMLEncodingException() {
	}

	/**
	 * Construct a new {@link XMLEncodingException} with the specified
	 * <code>message</code>.
	 * 
	 * @param message
	 *            the message describing the cause
	 */
	public XMLEncodingException(String message) {
		super(message);
	}

	/*
	 * Overridden to specify covariant return type.
	 * 
	 * @see java.lang.Throwable#initCause(java.lang.Throwable)
	 */
	@Override
	public synchronized XMLEncodingException initCause(Throwable cause) {
		super.initCause(cause);

		return this;
	}

	private XMLEncoder encoder;

	public XMLEncoder getEncoder() {
		return encoder;
	}

	public synchronized XMLEncodingException initEncoder(XMLEncoder encoder) {
		if (this.encoder != null) {
			throw new IllegalStateException("Can't overwrite encoder");
		} else if (encoder == null) {
			throw new NullPointerException("encoder");
		}

		this.encoder = encoder;

		return this;
	}

}
