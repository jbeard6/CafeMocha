/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * Specifies methods for obtaining an appropriate {@link ResourceConverter}.
 * 
 * @author computerguy5
 */
public interface ResourceConverterFactory {

	/**
	 * Returns a {@link ResourceConverter} capable of converting an
	 * <code>F</code> object to a <code>T</code> object.
	 * 
	 * @param <F>
	 *            the type of object to convert from
	 * @param <T>
	 *            the type of object to convert into
	 * @param fromClass
	 *            the class with which to lookup a {@link ResourceConverter}
	 *            capable of converting from the <code>fromClass</code>
	 * @param toClass
	 *            the class with which to lookup a {@link ResourceConverter}
	 *            capable of converting to the <code>toClass</code>
	 * @return an appropriate {@link ResourceConverter} if one exists
	 * @throws MissingConverterException
	 *             if no converter is available to convert from
	 *             <code>fromClass</code> to <code>toClass</code>
	 */
	public <F, T> ResourceConverter<F, T> getConverter(
			Class<? extends F> fromClass, Class<? extends T> toClass)
			throws MissingConverterException;

}
