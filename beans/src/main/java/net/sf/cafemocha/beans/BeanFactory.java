/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utility class for creating instances of bean classes.
 * 
 * @author computerguy5
 * 
 */
public abstract class BeanFactory {

	/**
	 * Create an instance of the <code>className</code> class by invoking its
	 * default constructor.
	 * 
	 * TODO Consolidate exceptions
	 * 
	 * @param <T>
	 *            the type of object to return
	 * @param className
	 *            the name of the class of which to create an instance
	 * @throws ClassNotFoundException
	 *             if the class is not present
	 * @throws IllegalAccessException
	 *             if the default constructor is not accessible
	 * @throws InstantiationException
	 *             if an exception occurs in the instantiation of the object
	 * @throws ClassCastException
	 *             if the <code>className</code> class is not of type
	 *             <code>T</code>
	 * @return a new instance of the specified class
	 */
	public static <T> T createInstance(String className)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		@SuppressWarnings("unchecked")
		Class<T> cls = (Class<T>) Class.forName(className);

		return cls.newInstance();
	}

	/**
	 * TODO Consolidate exceptions
	 * 
	 * TODO Complete Javadoc
	 * 
	 * @param <T>
	 * @param className
	 * @param properties
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 */
	public static <T> T createInstance(String className,
			Map<String, ?> properties) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IntrospectionException, InvocationTargetException {
		T instance = createInstance(className);

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

		return createInstance(className);
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

		return createInstance(className);
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

		return createInstance(className);
	}

}
