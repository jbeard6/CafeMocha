/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for getting and setting the values of Java beans.
 * 
 * @author computerguy5
 * 
 */
public abstract class Properties {

	private static final Logger LOG = LoggerFactory.getLogger(Properties.class);

	/**
	 * Returns the descriptor for the specified property of the specified class.
	 * 
	 * @param beanClass
	 *            the class for which to obtain the descriptor
	 * @param propertyName
	 *            the name of the property for which to obtain the descriptor
	 * @return the descriptor for the property
	 * @throws NoSuchPropertyException
	 *             if the specified property does not exist
	 */
	public static PropertyDescriptor descriptor(Class<?> beanClass,
			String propertyName) throws NoSuchPropertyException {
		try {
			// TBD Cache common requests
			return new PropertyDescriptor(propertyName, beanClass);
		} catch (IntrospectionException ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("Property %s#%s does not exist",
						beanClass, propertyName), ex);
			}
			throw new NoSuchPropertyException(beanClass, propertyName, ex);
		}
	}

	/**
	 * Returns the descriptor for the specified property of the specified class.
	 * 
	 * @param beanClass
	 *            the class for which to obtain the descriptor
	 * @param propertyName
	 *            the name of the property for which to obtain the descriptor
	 * @param getterName
	 *            the name of the method used to obtain the property value
	 * @param setterName
	 *            the name of the method used to set the property value
	 * @return the descriptor for the property
	 * @throws NoSuchPropertyException
	 *             if the specified property does not exist
	 */
	public static PropertyDescriptor descriptor(Class<?> beanClass,
			String propertyName, String getterName, String setterName)
			throws NoSuchPropertyException {
		try {
			// TBD Cache common requests
			return new PropertyDescriptor(propertyName, beanClass, getterName,
					setterName);
		} catch (IntrospectionException ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("Property %s#%s does not exist",
						beanClass, propertyName), ex);
			}
			throw new NoSuchPropertyException(beanClass, propertyName, ex);
		}
	}

	/**
	 * Returns the value of the specified bean property by invoking its getter
	 * method.
	 * 
	 * @param <T>
	 *            the type of object returned
	 * @param obj
	 *            the object upon which to read the property
	 * @param propertyName
	 *            the name of the property to read
	 * @return the property value
	 * @throws NullPointerException
	 *             if either <code>obj</code> or <code>propertyName</code> is
	 *             <code>null</code>
	 * @throws NoSuchPropertyException
	 *             if the specified property does not exist
	 * @throws WriteOnlyPropertyException
	 *             if the property has only a setter
	 * @throws InaccessiblePropertyException
	 *             if the getter method is inaccessible
	 * @throws GetPropertyException
	 *             if the getter method throws an exception
	 * @throws ClassCastException
	 *             if the value of the property is not of type <code>T</code>
	 */
	public static <T> T getValue(Object obj, String propertyName)
			throws PropertyException {
		// Obtain the property descriptor for the given property
		PropertyDescriptor descriptor = descriptor(obj.getClass(), propertyName);

		Method readMethod = descriptor.getReadMethod();
		if (readMethod == null) {
			throw new WriteOnlyPropertyException(obj.getClass(), propertyName);
		}

		try {
			// ClassCastException is permissible
			@SuppressWarnings("unchecked")
			T value = (T) readMethod.invoke(obj);

			return value;
		} catch (IllegalAccessException ex) {
			String message = String.format("Property %s#%s is inaccessible.",
					obj.getClass(), propertyName);
			LOG.warn(message, ex);
			throw new InaccessiblePropertyException(obj.getClass(),
					propertyName, "Property is inaccessible.", ex);
		} catch (InvocationTargetException ex) {
			throw new GetPropertyException(obj.getClass(), propertyName, ex);
		}
	}

	/**
	 * Sets the value of the specified bean property by invoking its setter
	 * method.
	 * 
	 * @param obj
	 *            the object upon which to set the property
	 * @param propertyName
	 *            the name of the property to set
	 * @param value
	 *            the property value
	 * @throws NullPointerException
	 *             if either <code>obj</code> or <code>propertyName</code> is
	 *             <code>null</code>
	 * @throws NoSuchPropertyException
	 *             if the specified property does not exist
	 * @throws ReadOnlyPropertyException
	 *             if the property has only a getter
	 * @throws InaccessiblePropertyException
	 *             if the setter method is inaccessible
	 * @throws SetPropertyException
	 *             if the setter method throws an exception
	 * @throws IllegalArgumentException
	 *             if the specified <code>value</code> is not compatible with
	 *             the property setter
	 */
	public static void setValue(Object obj, String propertyName, Object value)
			throws PropertyException {
		// Obtain the property descriptor for the given property
		PropertyDescriptor descriptor = descriptor(obj.getClass(), propertyName);

		Method writeMethod = descriptor.getWriteMethod();

		if (writeMethod == null) {
			throw new ReadOnlyPropertyException(obj.getClass(), propertyName);
		}

		try {
			writeMethod.invoke(obj, value);
		} catch (IllegalAccessException ex) {
			String message = String.format("Property %s#%s is inaccessible.",
					obj.getClass(), propertyName);
			LOG.warn(message, ex);
			throw new InaccessiblePropertyException(obj.getClass(),
					propertyName, "Property is inaccessible.", ex);
		} catch (InvocationTargetException ex) {
			throw new SetPropertyException(obj.getClass(), propertyName, ex);
		}
	}

	/**
	 * Set the properties named according to the keys in the {@link Map} to the
	 * corresponding values.
	 * 
	 * @param obj
	 *            the object on which to set the property values
	 * @param values
	 *            the property name to value mapping
	 * @throws NullPointerException
	 *             if either <code>obj</code> or <code>values</code> is
	 *             <code>null</code>
	 * @throws NoSuchPropertyException
	 *             if a specified property does not exist
	 * @throws ReadOnlyPropertyException
	 *             if a property has only a getter
	 * @throws InaccessiblePropertyException
	 *             if a setter method is inaccessible
	 * @throws SetPropertyException
	 *             if a setter method throws an exception
	 * @throws IllegalArgumentException
	 *             if a specified property value is not compatible with the
	 *             property setter
	 */
	public static void setValues(Object obj, Map<String, Object> values)
			throws PropertyException {
		// Initialize the value of all of the properties
		for (Entry<String, ?> entry : values.entrySet()) {
			String propertyName = entry.getKey();
			Object value = entry.getValue();

			setValue(obj, propertyName, value);
			LOG.debug("Set property {} to {}", propertyName, value);
		}
	}
}
