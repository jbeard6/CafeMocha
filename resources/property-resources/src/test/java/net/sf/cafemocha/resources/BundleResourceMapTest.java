/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

/**
 * Simple test for exercising the {@link BundleResourceMap}.
 * 
 * @author computerguy5
 * 
 */
public class BundleResourceMapTest {

	protected static BundleResourceMap createPropertiesFileBundle() {
		// The mock resource converter factory can perform no conversions!
		return new BundleResourceMap(new MockResourceConverterFactory(),
				"net.sf.cafemocha.resources.resources.BundleResourceMapTest");
	}

	@After
	public void tearDown() {
		resourceMap = null;
	}

	private BundleResourceMap resourceMap;

	/**
	 * Test method for
	 * {@link net.sf.cafemocha.resources.BundleResourceMap#containsKey(java.lang.String)}
	 * .
	 */
	@Test
	public void testContainsKey() {
		resourceMap = createPropertiesFileBundle();

		assertTrue("greeting does not exist",
				resourceMap.containsKey("greeting"));
		assertTrue("smallInteger does not exist",
				resourceMap.containsKey("smallInteger"));
	}

	/**
	 * Test method for
	 * {@link net.sf.cafemocha.resources.BundleResourceMap#getObject(java.lang.String, java.lang.Class)}
	 * .
	 */
	@Test
	public void testGetObject() {
		resourceMap = createPropertiesFileBundle();

		assertEquals("Hello World",
				resourceMap.getObject("greeting", String.class));
		assertEquals("1", resourceMap.getObject("smallInteger", String.class));
	}

}
