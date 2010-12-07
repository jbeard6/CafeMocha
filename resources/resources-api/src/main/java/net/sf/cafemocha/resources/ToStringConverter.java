/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * Converts any {@link Object} to a {@link String} by calling
 * {@link Object#toString()}.
 * 
 * @author computerguy5
 */
public class ToStringConverter implements ResourceConverter<Object, String> {

	public String convert(ResourceMap resourceMap, Object object) {
		return object == null ? null : object.toString();
	}

}
