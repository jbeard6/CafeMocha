/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for creating instances of bean classes.
 * 
 * @author computerguy5
 * 
 */
public abstract class BeanFactory {

	private static final Logger LOG = LoggerFactory
			.getLogger(BeanFactory.class);

	/**
	 * Create an instance of the <code>className</code> class by invoking its
	 * default constructor.
	 * 
	 * @param <T>
	 *            the type of object to return
	 * @param className
	 *            the name of the class of which to create an instance
	 * @return a new instance of the specified class
	 * @throws BeanInstantiationException
	 *             if the class is not present, if the default constructor is
	 *             non-existent or not accessible, or if an exception occurs in
	 *             the instantiation of the object
	 * @throws ClassCastException
	 *             if the <code>className</code> class is not of type
	 *             <code>T</code>
	 */
	public static <T> T createInstance(String className)
			throws BeanInstantiationException {
		try {
			@SuppressWarnings("unchecked")
			Class<T> cls = (Class<T>) Class.forName(className);

			// Explicit cast to repair Maven no maximal instance error
			return (T) cls.newInstance();
		} catch (ClassNotFoundException ex) {
			String message = String.format("Class %s Not Found", className);
			LOG.warn(message, ex);
			throw new BeanInstantiationException(message, ex);
		} catch (InstantiationException ex) {
			String message = String.format(
					"Class %s is abstract, has no default constructor, "
							+ "or constructor raised an exception", className);
			LOG.warn(message, ex);
			throw new BeanInstantiationException(message, ex);
		} catch (IllegalAccessException ex) {
			String message = String
					.format("Class %s default constructor is not accessible",
							className);
			LOG.warn(message, ex);
			throw new BeanInstantiationException(message, ex);
		}
	}

	/**
	 * Create an instance of the <code>className</code> class by invoking its
	 * default constructor and initializing the specified property values.
	 * 
	 * @param <T>
	 *            the type of object to return
	 * @param className
	 *            the name of the class of which to create an instance
	 * @param properties
	 *            mapping of property names to the initial values to set on the
	 *            new instance
	 * @return a new instance of the specified class with the specified
	 *         properties set
	 * @throws BeanInstantiationException
	 *             if the class is not present, if the default constructor is
	 *             non-existent or not accessible, or if an exception occurs in
	 *             the instantiation of the object
	 * @throws ClassCastException
	 *             if the <code>className</code> class is not of type
	 *             <code>T</code>
	 * @throws NoSuchPropertyException
	 *             if the specified property does not exist
	 * @throws ReadOnlyPropertyException
	 *             if the property has only a getter
	 * @throws InaccessiblePropertyException
	 *             if the setter method is inaccessible
	 * @throws SetPropertyException
	 *             if the setter method throws an exception
	 * @throws IllegalArgumentException
	 *             if a property value is not compatible with its setter
	 */
	public static <T> T createInstance(String className,
			Map<String, ?> properties) throws BeanInstantiationException,
			PropertyException {
		// Explicit cast to repair Maven no maximal instance error
		@SuppressWarnings("unchecked")
		T instance = (T) createInstance(className);

		if (properties == null) {
			return instance;
		}

		// Initialize the value of all of the properties
		for (Entry<String, ?> entry : properties.entrySet()) {
			Properties.setValue(instance, entry.getKey(), entry.getValue());
		}

		return instance;
	}

	/**
	 * Creates an instance of the class defined in the system property with the
	 * specified <code>propertyName</code>. The property must contain a fully
	 * qualified name of a class containing a publicly accessible default
	 * constructor.
	 * 
	 * @param <T>
	 *            the type of object to return
	 * @param propertyName
	 *            the name of the property that contains the name of the class
	 *            to instantiate
	 * @return a new instance of the class defined in the specified property
	 * @throws ClassNotFoundException
	 *             if the class is not present
	 * @throws IllegalAccessException
	 *             if the default constructor is not accessible
	 * @throws InstantiationException
	 *             if an exception occurs in the instantiation of the object
	 * @throws ClassCastException
	 *             if the <code>className</code> class is not of type
	 *             <code>T</code>
	 */
	public static <T> T getInstance(String propertyName)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		String className = System.getProperty(propertyName);

		// Explicit cast to repair Maven no maximal instance error
		@SuppressWarnings("unchecked")
		T instance = (T) createInstance(className);

		return instance;
	}

	/**
	 * Creates an instance of the class defined in the system property with the
	 * specified <code>propertyName</code>. The property must contain a fully
	 * qualified name of a class containing a publicly accessible default
	 * constructor.
	 * 
	 * @param <T>
	 *            the type of object to return
	 * @param propertyName
	 *            the name of the property that contains the name of the class
	 *            to instantiate
	 * @param defaultClassName
	 *            the name of the class to use if the specified property is
	 *            undefined
	 * @return a new instance of the class defined in the specified property
	 * @throws ClassNotFoundException
	 *             if the class is not present
	 * @throws IllegalAccessException
	 *             if the default constructor is not accessible
	 * @throws InstantiationException
	 *             if an exception occurs in the instantiation of the object
	 * @throws ClassCastException
	 *             if the <code>className</code> class is not of type
	 *             <code>T</code>
	 */
	public static <T> T getInstance(String propertyName, String defaultClassName)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		String className = System.getProperty(propertyName, defaultClassName);

		// Explicit cast to repair Maven no maximal instance error
		@SuppressWarnings("unchecked")
		T instance = (T) createInstance(className);

		return instance;
	}

	/**
	 * Creates an instance of the class defined in the system property with the
	 * specified <code>propertyName</code>. The property must contain a fully
	 * qualified name of a class containing a publicly accessible default
	 * constructor.
	 * 
	 * @param <T>
	 *            the type of object to return
	 * @param propertyName
	 *            the name of the property that contains the name of the class
	 *            to instantiate
	 * @param defaultClassName
	 *            the name of the class to use if the specified property is
	 *            undefined
	 * @return a new instance of the class defined in the specified property
	 * @throws ClassNotFoundException
	 *             if the class is not present
	 * @throws IllegalAccessException
	 *             if the default constructor is not accessible
	 * @throws InstantiationException
	 *             if an exception occurs in the instantiation of the object
	 * @throws ClassCastException
	 *             if the <code>className</code> class is not of type
	 *             <code>T</code>
	 */
	public static <T> T getInstance(String propertyName, T defaultInstance)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		String className = System.getProperty(propertyName);

		if (className == null) {
			// Property is undefined, return the default
			return defaultInstance;
		}

		// Explicit cast to repair Maven no maximal instance error
		@SuppressWarnings("unchecked")
		T instance = (T) createInstance(className);

		return instance;
	}

}
