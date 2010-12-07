/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link ResourceMap} backed by a {@link ResourceBundle} that adds automatic
 * string conversion and string resource variable substitution.
 * <p>
 * As a convenience, <tt>getObject</tt> wrapper methods for common types are
 * provided.
 * <p>
 * The <tt>getObject</tt> method scans raw string resource values for
 * <tt>${resourceName}</tt> variable substitutions before performing string
 * conversion. Variables named this way can refer to String resources defined
 * anywhere in their ResourceMap or any parent ResourceMap. The special variable
 * <tt>${null}</tt> means that the value of the resource will be null.
 * 
 * @author computerguy5
 * @see ResourceBundle
 * 
 */
public class BundleResourceMap extends AbstractResourceMap {

	private static Logger LOG = LoggerFactory
			.getLogger(BundleResourceMap.class);

	/**
	 * Creates a top-level {@link BundleResourceMap} that contains all of the
	 * resources defined in the {@link ResourceBundle}.
	 * 
	 * @param converterFactory
	 *            the {@link ResourceConverterFactory} to use for converting
	 *            resources
	 * @param bundleName
	 *            the name of the resource bundle that contains the resources
	 * @throws NullPointerException
	 *             if either <code>converterFactory</code> or
	 *             <code>bundleName</code> is <code>null</code>
	 * @throws MissingResourceException
	 *             if no resource bundle for the specified
	 *             <code>bundleName</code> can be found
	 */
	public BundleResourceMap(ResourceConverterFactory converterFactory,
			String bundleName) throws MissingResourceException {
		this(null, converterFactory, bundleName);
	}

	/**
	 * Creates a top-level {@link BundleResourceMap} that contains all of the
	 * resources defined in the {@link ResourceBundle}.
	 * 
	 * @param converterFactory
	 *            the {@link ResourceConverterFactory} to use for converting
	 *            resources
	 * @param bundleName
	 *            the name of the resource bundle that contains the resources
	 * @param locale
	 *            the locale for which a resource bundle is desired
	 * @throws NullPointerException
	 *             if either <code>converterFactory</code>,
	 *             <code>bundleName</code> or <code>locale</code> is
	 *             <code>null</code>
	 * @throws MissingResourceException
	 *             if no resource bundle for the specified
	 *             <code>bundleName</code> can be found
	 */
	public BundleResourceMap(ResourceConverterFactory converterFactory,
			String bundleName, Locale locale) throws MissingResourceException {
		this(null, converterFactory, bundleName, locale);
	}

	/**
	 * Creates a top-level {@link BundleResourceMap} that contains all of the
	 * resources defined in the {@link ResourceBundle}.
	 * 
	 * @param converterFactory
	 *            the {@link ResourceConverterFactory} to use for converting
	 *            resources
	 * @param resourceBundle
	 *            the resource bundle that contains the resources
	 * @throws NullPointerException
	 *             if either <code>converterFactory</code> or
	 *             <code>resourceBundle</code> is <code>null</code>
	 */
	public BundleResourceMap(ResourceConverterFactory converterFactory,
			ResourceBundle resourceBundle) {
		this(null, converterFactory, resourceBundle);
	}

	/**
	 * Creates a {@link BundleResourceMap} that contains all of the resources
	 * defined in the {@link ResourceBundle} as well as all those defined in the
	 * <code>parent</code>.
	 * 
	 * @param parent
	 *            the parent resource map
	 * @param converterFactory
	 *            the {@link ResourceConverterFactory} to use for converting
	 *            resources
	 * @param bundleName
	 *            the name of the resource bundle that contains the resources
	 * @throws NullPointerException
	 *             if either <code>converterFactory</code> or
	 *             <code>bundleName</code> is <code>null</code>
	 * @throws MissingResourceException
	 *             if no resource bundle for the specified
	 *             <code>bundleName</code> can be found
	 */
	public BundleResourceMap(ResourceMap parent,
			ResourceConverterFactory converterFactory, String bundleName)
			throws MissingResourceException {
		this(parent, converterFactory, ResourceBundle.getBundle(bundleName));
	}

	/**
	 * Creates a {@link BundleResourceMap} that contains all of the resources
	 * defined in the {@link ResourceBundle} as well as all those defined in the
	 * <code>parent</code>.
	 * 
	 * @param parent
	 *            the parent resource map
	 * @param converterFactory
	 *            the {@link ResourceConverterFactory} to use for converting
	 *            resources
	 * @param bundleName
	 *            the name of the resource bundle that contains the resources
	 * @param locale
	 *            the locale for which a resource bundle is desired
	 * @throws NullPointerException
	 *             if either <code>converterFactory</code>,
	 *             <code>bundleName</code> or <code>locale</code> is
	 *             <code>null</code>
	 * @throws MissingResourceException
	 *             if no resource bundle for the specified
	 *             <code>bundleName</code> can be found
	 */
	public BundleResourceMap(ResourceMap parent,
			ResourceConverterFactory converterFactory, String bundleName,
			Locale locale) throws MissingResourceException {
		this(parent, converterFactory, ResourceBundle.getBundle(bundleName,
				locale));
	}

	/**
	 * Creates a {@link BundleResourceMap} that contains all of the resources
	 * defined in the {@link ResourceBundle} as well as all those defined in the
	 * <code>parent</code>.
	 * 
	 * @param parent
	 *            the parent resource map
	 * @param converterFactory
	 *            the {@link ResourceConverterFactory} to use for converting
	 *            resources
	 * @param resourceBundle
	 *            the resource bundle that contains the resources
	 * @throws NullPointerException
	 *             if either <code>converterFactory</code> or
	 *             <code>resourceBundle</code> is <code>null</code>
	 */
	public BundleResourceMap(ResourceMap parent,
			ResourceConverterFactory converterFactory,
			ResourceBundle resourceBundle) {
		super(parent, converterFactory);

		if (resourceBundle == null) {
			throw new NullPointerException("resourceBundle");
		}

		// TBD this is vulnerable to external modification
		this.resourceBundle = resourceBundle;
	}

	private final ResourceBundle resourceBundle;

	/**
	 * Returns the {@link ResourceBundle} that backs this
	 * {@link BundleResourceMap}.
	 * 
	 * @return the backing {@link ResourceBundle}
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public boolean containsKey(String key) {
		LOG.trace("ENTER {}", key);

		boolean found = false;

		// TBD Should we cache the keys?
		Enumeration<String> keys = resourceBundle.getKeys();
		while (keys.hasMoreElements()) {
			String k = keys.nextElement();

			if (key == null ? k == null : k.equals(key)) {
				// Key found
				found = true;
				break;
			}
		}

		LOG.trace("RETURN {}", found);
		return found;
	}

	public <T> T getObject(String key, Class<? extends T> clazz)
			throws MissingResourceException, ResourceConverterException {
		LOG.trace("ENTER {}, {}", key, clazz);

		if (!containsKey(key)) {
			if (parent == null) {
				throw new MissingResourceException(key + " does not exist.");
			}

			// Ask the parent for the object
			return parent.getObject(key, clazz);
		}

		String value = resourceBundle.getString(key);

		// Obtain an appropriate resource converter
		ResourceConverter<String, T> converter = converterFactory.getConverter(
				String.class, clazz);
		if (converter == null) {
			throw new MissingConverterException("No ResourceConverter")
					.initFromClass(String.class).initToClass(clazz);
		}

		// Convert value into appropriate type
		T converted = converter.convert(this, value);

		LOG.trace("RETURN {}", converted);
		return converted;
	}
}
