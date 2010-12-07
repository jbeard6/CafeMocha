/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * Converts a resource from an <code>F</code> object to a <code>T</code> object.
 * For instance, a {@link ResourceMap} may contain objects as {@link String
 * Strings}, and a <code>ResourceConverter&lt;String, Font&gt;</code> would be
 * used to obtain a {@link java.awt.Font} object from the {@link ResourceMap}.
 * 
 * @author computerguy5
 * @param <F>
 *            the type of object from which to convert
 * @param <T>
 *            the type of object to convert into
 */
public interface ResourceConverter<F, T> {

	/**
	 * Convert the specified <code>object</code> into a <code>T</code> object.
	 * May optionally obtain additional resources from the {@link ResourceMap}
	 * to aid in the conversion.
	 * 
	 * @param resourceMap
	 *            the {@link ResourceMap} from which the object was obtained and
	 *            from which additional resources may be obtained
	 * @param object
	 *            the object to convert
	 * @return the converted object
	 * @throws ResourceConverterException
	 *             if an error occurs converting the object
	 */
	public T convert(ResourceMap resourceMap, F object)
			throws ResourceConverterException;

}
