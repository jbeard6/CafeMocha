/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

import java.util.Collections;

/**
 * Returns the object passed in with no transformations.
 * 
 * @author computerguy5
 */
public class NoOpConverter<T> implements ResourceConverter<T, T> {

	/**
	 * Singleton instance of the NoOpConverter that can handle all types. By not
	 * specifying any typing information, we can safely cast this to any type.
	 * This is the same way that the {@link Collections} utility class uses
	 * singleton empty collections to satisfy any type.
	 */
	@SuppressWarnings("rawtypes")
	private static final NoOpConverter INSTANCE = new NoOpConverter();

	public static <T> ResourceConverter<T, T> noOp() {
		// This is safe for all T
		@SuppressWarnings("unchecked")
		ResourceConverter<T, T> converter = INSTANCE;

		return converter;
	}

	public T convert(ResourceMap resourceMap, T object) {
		// Just returns the input
		return object;
	}

}
