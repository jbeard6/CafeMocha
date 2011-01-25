/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

import static net.sf.cafemocha.resources.CastResourceConverter.cast;

/**
 * This is a mocked {@link ResourceConverterFactory} that returns a
 * {@link ResourceConverter} that performs a simple cast.
 * 
 * @author computerguy5
 * 
 */
public class MockResourceConverterFactory implements ResourceConverterFactory {

	public <F, T> ResourceConverter<F, T> getConverter(
			Class<? extends F> fromClass, Class<? extends T> toClass)
			throws MissingConverterException {
		// Always returns the casting converter
		return cast();
	}

}
