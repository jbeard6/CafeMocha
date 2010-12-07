/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replaces <code>${resourceName}</code> variables with their value from the
 * {@link ResourceMap}. The special expression <code>${null}</code> returns
 * <code>null</code>.
 * 
 * @author computerguy5
 */
public class FormattedStringResourceConverter implements
		ResourceConverter<String, String> {

	private static final Pattern PATTERN = Pattern
			.compile("\\$\\{([^\\}]+)\\}");

	public String convert(ResourceMap resourceMap, String object)
			throws ResourceConverterException {
		Matcher m = PATTERN.matcher(object);

		StringBuffer stringBuffer = new StringBuffer();
		while (m.find()) {
			String key = m.group(1);
			String value = resourceMap.getObject(key, String.class);

			/*
			 * A resourceMap may or may not throw a MissingResourceException,
			 * but we must or we can not substitute its value.
			 */
			if (value == null) {
				throw new MissingResourceException("resource missing")
						.initResourceMap(resourceMap).initResourceName(key);
			}

			// Escape $ in replacement to avoid illegal group reference
			m.appendReplacement(stringBuffer,
					value == null ? null : value.replaceAll("\\$", "\\\\\\$"));
		}
		m.appendTail(stringBuffer);

		return stringBuffer.toString();
	}

}
