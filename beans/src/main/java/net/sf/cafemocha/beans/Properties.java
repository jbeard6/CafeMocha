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

/**
 * Utility class for getting and setting the values of Java beans.
 * 
 * @author computerguy5
 * 
 */
public abstract class Properties {

	/**
	 * Returns the descriptor for the specified property of the specified class.
	 * 
	 * @param beanClass
	 *            the class for which to obtain the descriptor
	 * @param propertyName
	 *            the name of the property for which to obtain the descriptor
	 * @return the descriptor for the property
	 * @throws IntrospectionException
	 *             if an error occurs while introspecting the property
	 */
	public static PropertyDescriptor descriptor(Class<?> beanClass,
			String propertyName) throws IntrospectionException {
		// TODO Cache common requests
		return new PropertyDescriptor(propertyName, beanClass);
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
	 * @throws IntrospectionException
	 *             if an error occurs while introspecting the property
	 */
	public static PropertyDescriptor descriptor(Class<?> beanClass,
			String propertyName, String getterName, String setterName)
			throws IntrospectionException {
		// TODO Cache common requests
		return new PropertyDescriptor(propertyName, beanClass, getterName,
				setterName);
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
	 * @throws IntrospectionException
	 *             if an error occurs while introspecting the property
	 * @throws IllegalAccessException
	 *             if the getter method is inaccessible
	 * @throws InvocationTargetException
	 *             if the getter method throws an exception
	 * @throws ClassCastException
	 *             if the value of the property is not of type <code>T</code>
	 */
	public static <T> T getValue(Object obj, String propertyName)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		// Obtain the property descriptor for the given property
		PropertyDescriptor descriptor = descriptor(obj.getClass(), propertyName);

		Method readMethod = descriptor.getReadMethod();

		// ClassCastException is permissible
		@SuppressWarnings("unchecked")
		T value = (T) readMethod.invoke(obj);

		return value;
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
	 * @throws IntrospectionException
	 *             if an error occurs while introspecting the property
	 * @throws IllegalAccessException
	 *             if the setter method is inaccessible
	 * @throws InvocationTargetException
	 *             if the setter method throws an exception
	 */
	public static void setValue(Object obj, String propertyName, Object value)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		// Obtain the property descriptor for the given property
		PropertyDescriptor descriptor = descriptor(obj.getClass(), propertyName);

		Method writeMethod = descriptor.getWriteMethod();

		writeMethod.invoke(obj, value);
	}
}
