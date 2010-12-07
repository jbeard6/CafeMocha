/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

/**
 * An abstract implementation of the {@link ResourceMap} interface.
 * 
 * @author computerguy5
 * 
 */
public abstract class AbstractResourceMap implements ResourceMap {

	/**
	 * Constructs a new top-level {@link AbstractResourceMap}.
	 */
	public AbstractResourceMap() {
		// TODO Obtain a default factory instance if null
		this(null, null);
	}

	/**
	 * Constructs a new {@link AbstractResourceMap} with the specified
	 * <code>parent</code>.
	 * 
	 * @param parent
	 *            the parent resource map
	 */
	public AbstractResourceMap(ResourceMap parent) {
		// TODO Obtain a default factory instance if null
		this(parent, null);
	}

	/**
	 * Constructs a new top-level {@link AbstractResourceMap} with the specified
	 * {@link ResourceConverterFactory}.
	 * 
	 * @param converterFactory
	 *            the {@link ResourceConverterFactory} to use for converting
	 *            resources
	 * @throws NullPointerException
	 *             if <code>converterFactory</code> is <code>null</code>
	 */
	public AbstractResourceMap(ResourceConverterFactory converterFactory) {
		this(null, converterFactory);
	}

	/**
	 * Constructs a new {@link AbstractResourceMap} with the specified
	 * <code>parent</code> and {@link ResourceConverterFactory}.
	 * 
	 * @param parent
	 *            the parent resource map
	 * @param converterFactory
	 *            the {@link ResourceConverterFactory} to use for converting
	 *            resources
	 * @throws NullPointerException
	 *             if <code>converterFactory</code> is <code>null</code>
	 */
	public AbstractResourceMap(ResourceMap parent,
			ResourceConverterFactory converterFactory) {
		if (converterFactory == null) {
			throw new NullPointerException("converterFactory");
		}

		this.parent = parent;
		this.converterFactory = converterFactory;
	}

	protected final ResourceMap parent;

	public ResourceMap getParent() {
		return parent;
	}

	protected final ResourceConverterFactory converterFactory;

}
