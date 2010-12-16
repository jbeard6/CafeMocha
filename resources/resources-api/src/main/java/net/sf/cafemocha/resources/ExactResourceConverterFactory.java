/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.resources;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A {@link ResourceConverterFactory} with which {@link ResourceConverter
 * ResourceConverters} can be registered. Converter lookup is based on an exact
 * match of the <code>fromClass</code> and <code>toClass</code>.
 * 
 * @author computerguy5
 * 
 */
public class ExactResourceConverterFactory implements ResourceConverterFactory {

	/**
	 * Construct a new {@link ResourceConverterFactory} with which
	 * {@link ResourceConverter ResourceConverters} can be registered.
	 */
	public ExactResourceConverterFactory() {
		this.registrations = new ConcurrentHashMap<ConverterKey, ResourceConverter<?, ?>>();
	}

	private final Map<ConverterKey, ResourceConverter<?, ?>> registrations;

	/**
	 * Register the {@link ResourceConverter} to perform the conversion from
	 * <code>fromClass</code> to <code>toClass</code>.
	 * 
	 * @param <F>
	 *            the type from which the resources will be converted
	 * @param <T>
	 *            the type to which the resources will be converted
	 * @param fromClass
	 *            the class from which the resources will be converted
	 * @param toClass
	 *            the class to which the resources will be converted
	 * @param resourceConverter
	 *            the resource converter that will be used for the conversion
	 * @return the previously registered resource converter
	 */
	public <F, T> ResourceConverter<F, T> register(
			Class<? extends F> fromClass, Class<? extends T> toClass,
			ResourceConverter<F, T> resourceConverter) {
		// Cast guaranteed safe by registration method
		@SuppressWarnings("unchecked")
		ResourceConverter<F, T> previous = (ResourceConverter<F, T>) registrations
				.put(keyFor(fromClass, toClass), resourceConverter);

		return previous;
	}

	/**
	 * Unregister the {@link ResourceConverter} that was previously registered
	 * to perform the conversion from <code>fromClass</code> to
	 * <code>toClass</code>.
	 * 
	 * 
	 * @param <F>
	 *            the type from which the resources will be converted
	 * @param <T>
	 *            the type to which the resources will be converted
	 * @param fromClass
	 *            the class from which the resources will be converted
	 * @param toClass
	 *            the class to which the resources will be converted
	 * @return the previously registered resource converter
	 */
	public <F, T> ResourceConverter<F, T> unregister(
			Class<? extends F> fromClass, Class<? extends T> toClass) {
		// Cast guaranteed safe by registration method
		@SuppressWarnings("unchecked")
		ResourceConverter<F, T> converter = (ResourceConverter<F, T>) registrations
				.remove(keyFor(fromClass, toClass));

		return converter;
	}

	/**
	 * Obtains the {@link ResourceConverter} that has been registered to handle
	 * the conversion from <code>fromClass</code> to <code>toClass</code>.
	 * 
	 * @see ResourceConverterFactory#getConverter(Class, Class)
	 */
	public <F, T> ResourceConverter<F, T> getConverter(
			Class<? extends F> fromClass, Class<? extends T> toClass)
			throws MissingConverterException {
		// Cast guaranteed safe by registration method
		@SuppressWarnings("unchecked")
		ResourceConverter<F, T> converter = (ResourceConverter<F, T>) registrations
				.get(keyFor(fromClass, toClass));

		if (converter == null) {
			throw new MissingConverterException();
		}

		return converter;
	}

	/**
	 * Convenience method for generating a key for the specified pair of
	 * {@link Class Classes}.
	 * 
	 * @param fromClass
	 *            the class from which the resource converter converts
	 * @param toClass
	 *            the class to which the resource converter converts
	 * @return a converter key for the specified pair of classes
	 */
	public static ConverterKey keyFor(Class<?> fromClass, Class<?> toClass) {
		return new ConverterKey(fromClass, toClass);
	}

	/**
	 * A key used for lookup of {@link ResourceConverter ResourceConverters} by
	 * the {@link ExactResourceConverterFactory}.
	 * 
	 * @author computerguy5
	 * 
	 */
	private static class ConverterKey {

		/**
		 * @param fromClass
		 *            the class from which the resource converter converts
		 * @param toClass
		 *            the class to which the resource converter converts
		 */
		public ConverterKey(Class<?> fromClass, Class<?> toClass) {
			assert fromClass != null : "fromClass is null";
			assert toClass != null : "toClass is null";

			this.fromClass = fromClass;
			this.toClass = toClass;
		}

		private final Class<?> fromClass;

		private final Class<?> toClass;

		@Override
		public int hashCode() {
			// Simple hashing
			return fromClass.hashCode() ^ toClass.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			} else if (obj == this) {
				return true;
			} else if (!(obj instanceof ConverterKey)) {
				return false;
			}

			ConverterKey rhs = (ConverterKey) obj;

			return fromClass.equals(rhs.fromClass)
					&& toClass.equals(rhs.toClass);
		}

		@Override
		public String toString() {
			return String.format("[%s, %s]", fromClass, toClass);
		}
	}

}
