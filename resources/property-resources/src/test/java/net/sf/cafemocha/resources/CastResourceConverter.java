/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * A mock {@link ResourceConverter} that performs a naive cast.
 * 
 * @author BEARDJ1
 * 
 */
public class CastResourceConverter<F, T> implements ResourceConverter<F, T> {

	@SuppressWarnings("rawtypes")
	private static CastResourceConverter INSTANCE = new CastResourceConverter();

	public static <F, T> ResourceConverter<F, T> cast() {
		@SuppressWarnings("unchecked")
		ResourceConverter<F, T> converter = INSTANCE;

		return converter;
	}

	@SuppressWarnings("unchecked")
	public T convert(ResourceMap resourceMap, F object)
			throws ResourceConverterException {
		return (T) object;
	}

}
