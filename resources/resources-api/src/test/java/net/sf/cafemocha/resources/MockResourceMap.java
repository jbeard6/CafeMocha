/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

import java.util.HashMap;
import java.util.Map;

/**
 * @author computerguy5
 * 
 */
public class MockResourceMap implements ResourceMap {

	public MockResourceMap() {
		this(null, null);
	}

	public MockResourceMap(Map<String, ?> resources) {
		this(null, resources);
	}

	public MockResourceMap(ResourceMap parent) {
		this(parent, null);
	}

	public MockResourceMap(ResourceMap parent, Map<String, ?> resources) {
		this.parent = parent;
		this.resources = new HashMap<String, Object>();

		if (resources != null) {
			this.resources.putAll(resources);
		}
	}

	private final ResourceMap parent;

	public ResourceMap getParent() {
		return parent;
	}

	private Map<String, Object> resources = new HashMap<String, Object>();

	public boolean containsKey(String key) {
		return resources.containsKey(key);
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(String key, Class<? extends T> clazz)
			throws MissingResourceException, ResourceConverterException {
		if (!containsKey(key)) {
			if (parent != null) {
				// Delegate to the parent map
				return parent.getObject(key, clazz);
			} else {
				// The resource doesn't exist
				throw new MissingResourceException();
			}
		}

		// The key exists, obtain its value
		Object value = resources.get(key);

		if (value == null) {
			// The value is null, direct conversion
			return null;
		}

		if (!clazz.isAssignableFrom(value.getClass())) {
			throw new MissingConverterException("Conversion not supported");
		}

		// We know that the cast is safe with above check
		return (T) value;
	}

	public void putObject(String key, Object value) {
		resources.put(key, value);
	}

}
