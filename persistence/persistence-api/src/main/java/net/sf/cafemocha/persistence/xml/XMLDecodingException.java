/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence.xml;

import java.beans.XMLDecoder;
import java.io.IOException;

/**
 * Thrown to indicate an error while attempting to encode an object as XML using
 * an {@link XMLDecoder}.
 * 
 * @author computerguy5
 * 
 */
public class XMLDecodingException extends IOException {
	private static final long serialVersionUID = -4130540146887991280L;

	/**
	 * Construct a new {@link XMLDecodingException}.
	 */
	public XMLDecodingException() {
	}

	/**
	 * Construct a new {@link XMLDecodingException} with the specified
	 * <code>message</code>.
	 * 
	 * @param message
	 *            the message describing the cause
	 */
	public XMLDecodingException(String message) {
		super(message);
	}

	/*
	 * Overridden to specify covariant return type.
	 * 
	 * @see java.lang.Throwable#initCause(java.lang.Throwable)
	 */
	@Override
	public synchronized XMLDecodingException initCause(Throwable cause) {
		super.initCause(cause);

		return this;
	}

	private XMLDecoder decoder;

	public XMLDecoder getDecoder() {
		return decoder;
	}

	public synchronized XMLDecodingException initDecoder(XMLDecoder decoder) {
		if (this.decoder != null) {
			throw new IllegalStateException("Can't overwrite decoder");
		} else if (decoder == null) {
			throw new NullPointerException("decoder");
		}

		this.decoder = decoder;

		return this;
	}

}
