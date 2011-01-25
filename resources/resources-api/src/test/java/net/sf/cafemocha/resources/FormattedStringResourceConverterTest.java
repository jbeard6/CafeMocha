/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests behavior of the {@link FormattedStringResourceConverter}.
 * 
 * @author computerguy5
 * 
 */
public class FormattedStringResourceConverterTest {

	@Before
	public void setUp() {
		// Initialize the converter
		converter = new FormattedStringResourceConverter();

		// Initialize the resource map
		resourceMap = new MockResourceMap();
		resourceMap.putObject("hello", "Hello");
		resourceMap.putObject("world", "World");
		resourceMap.putObject("greeting", "${hello} ${world}!");
		resourceMap.putObject("name with space", "key had space");
		resourceMap.putObject("big money", "$1,000,000.00");
	}

	private MockResourceMap resourceMap;

	private FormattedStringResourceConverter converter;

	@Test
	public void testConvert() {
		// Basic replacement
		assertEquals("Hello", converter.convert(resourceMap, "${hello}"));

		// Multiple replacement
		assertEquals("Hello World!",
				converter.convert(resourceMap, "${hello} ${world}!"));

		// whitespace in key
		assertEquals("key had space",
				converter.convert(resourceMap, "${name with space}"));

		// Special character in replacement
		assertEquals("$1,000,000.00",
				converter.convert(resourceMap, "${big money}"));

		// Recursive replacement is the responsibility of the resourceMap
		assertEquals("Oh ${hello} ${world}!",
				converter.convert(resourceMap, "Oh ${greeting}"));
	}

	@Test(expected = MissingResourceException.class)
	public void missingResource() {
		converter.convert(resourceMap, "${no such key}");
	}
}
