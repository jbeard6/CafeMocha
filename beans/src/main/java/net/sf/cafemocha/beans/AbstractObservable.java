/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * An abstract superclass that contains common functionality for binding Bean
 * properties.
 * 
 * @author computerguy5
 * 
 */
public abstract class AbstractObservable implements Observable {

	protected transient PropertyChangeSupport propertyChangeSupport;

	/**
	 * @see PropertyChangeSupport#fireIndexedPropertyChange(String, int,
	 *      boolean, boolean)
	 */
	protected final void fireIndexedPropertyChange(String propertyName,
			int index, boolean oldValue, boolean newValue) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.fireIndexedPropertyChange(propertyName,
					index, oldValue, newValue);
		}
	}

	/**
	 * @see PropertyChangeSupport#fireIndexedPropertyChange(String, int, int,
	 *      int)
	 */
	protected final void fireIndexedPropertyChange(String propertyName,
			int index, int oldValue, int newValue) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.fireIndexedPropertyChange(propertyName,
					index, oldValue, newValue);
		}
	}

	/**
	 * @see PropertyChangeSupport#fireIndexedPropertyChange(String, int, Object,
	 *      Object)
	 */
	protected final void fireIndexedPropertyChange(String propertyName,
			int index, Object oldValue, Object newValue) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.fireIndexedPropertyChange(propertyName,
					index, oldValue, newValue);
		}
	}

	/**
	 * Fire an existing PropertyChangeEvent
	 * <p>
	 * If the event's oldValue property is not equal to newValue, invoke the
	 * {@code propertyChange} method on all of the
	 * {@code PropertyChangeListeners} added so far, on the event dispatching
	 * thread.
	 * 
	 * @see #addPropertyChangeListener
	 * @see #removePropertyChangeListener
	 * @see java.beans.PropertyChangeSupport#firePropertyChange(PropertyChangeEvent
	 *      e)
	 */
	protected final void firePropertyChange(PropertyChangeEvent e) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.firePropertyChange(e);
		}
	}

	/**
	 * @see PropertyChangeSupport#firePropertyChange(String, boolean, boolean)
	 */
	protected final void firePropertyChange(String propertyName,
			boolean oldValue, boolean newValue) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.firePropertyChange(propertyName, oldValue,
					newValue);
		}
	}

	/**
	 * @see PropertyChangeSupport#firePropertyChange(String, int, int)
	 */
	protected final void firePropertyChange(String propertyName, int oldValue,
			int newValue) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.firePropertyChange(propertyName, oldValue,
					newValue);
		}
	}

	/**
	 * Called whenever the value of a bound property is set.
	 * <p>
	 * If oldValue is not equal to newValue, invoke the {@code propertyChange}
	 * method on all of the {@code PropertyChangeListeners} added so far, on the
	 * event dispatching thread.
	 * 
	 * @see #addPropertyChangeListener
	 * @see #removePropertyChangeListener
	 * @see java.beans.PropertyChangeSupport#firePropertyChange(String, Object,
	 *      Object)
	 */
	protected final void firePropertyChange(String propertyName,
			Object oldValue, Object newValue) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.firePropertyChange(propertyName, oldValue,
					newValue);
		}
	}

	/**
	 * Indicates that an arbitrary set of bound properties have changed. The
	 * {@link PropertyChangeEvent} will have no property name, old value, or new
	 * value set.
	 */
	protected final void fireMultiplePropertyChange() {
		firePropertyChange(null, null, null);
	}

	/**
	 * Add a PropertyChangeListener to the listener list. The listener is
	 * registered for all properties and its {@code propertyChange} method will
	 * run on the event dispatching thread.
	 * <p>
	 * If {@code listener} is null, no exception is thrown and no action is
	 * taken.
	 * 
	 * @param listener
	 *            the PropertyChangeListener to be added.
	 * @see #removePropertyChangeListener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener
	 */
	public final synchronized void addPropertyChangeListener(
			PropertyChangeListener listener) {
		if (propertyChangeSupport == null) {
			propertyChangeSupport = new PropertyChangeSupport(this);
		}

		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * Add a PropertyChangeListener for a specific property. The listener will
	 * be invoked only when a call on firePropertyChange names that specific
	 * property. The same listener object may be added more than once. For each
	 * property, the listener will be invoked the number of times it was added
	 * for that property. If <code>propertyName</code> or <code>listener</code>
	 * is null, no exception is thrown and no action is taken.
	 * 
	 * @param propertyName
	 *            The name of the property to listen on.
	 * @param listener
	 *            the PropertyChangeListener to be added
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 */
	public final synchronized void addPropertyChangeListener(
			String propertyName, PropertyChangeListener listener) {
		if (propertyChangeSupport == null) {
			propertyChangeSupport = new PropertyChangeSupport(this);
		}

		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Remove a PropertyChangeListener from the listener list.
	 * <p>
	 * If {@code listener} is null, no exception is thrown and no action is
	 * taken.
	 * 
	 * @param listener
	 *            the PropertyChangeListener to be removed.
	 * @see #addPropertyChangeListener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener
	 */
	public final synchronized void removePropertyChangeListener(
			PropertyChangeListener listener) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.removePropertyChangeListener(listener);
		}
	}

	/**
	 * Remove a PropertyChangeListener for a specific property. If
	 * <code>listener</code> was added more than once to the same event source
	 * for the specified property, it will be notified one less time after being
	 * removed. If <code>propertyName</code> is null, no exception is thrown and
	 * no action is taken. If <code>listener</code> is null, or was never added
	 * for the specified property, no exception is thrown and no action is
	 * taken.
	 * 
	 * @param propertyName
	 *            The name of the property that was listened on.
	 * @param listener
	 *            The PropertyChangeListener to be removed
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener)
	 */
	public final synchronized void removePropertyChangeListener(
			String propertyName, PropertyChangeListener listener) {
		if (propertyChangeSupport != null) {
			propertyChangeSupport.removePropertyChangeListener(propertyName,
					listener);
		}
	}

	/**
	 * An array of all of the {@code PropertyChangeListeners} added so far.
	 * 
	 * @return all of the {@code PropertyChangeListeners} added so far.
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners
	 */
	public final PropertyChangeListener[] getPropertyChangeListeners() {
		if (propertyChangeSupport != null)
			return propertyChangeSupport.getPropertyChangeListeners();
		else
			return new PropertyChangeListener[0];
	}

	/**
	 * An array of all of the {@code PropertyChangeListeners} added so far for
	 * the specified <code>propertyName</code>.
	 * 
	 * @return all of the {@code PropertyChangeListeners} added so far.
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners
	 */
	public final PropertyChangeListener[] getPropertyChangeListeners(
			String propertyName) {
		if (propertyChangeSupport != null)
			return propertyChangeSupport
					.getPropertyChangeListeners(propertyName);
		else
			return new PropertyChangeListener[0];
	}

	/**
	 * @see PropertyChangeSupport#hasListeners(String)
	 */
	public final boolean hasListeners(String propertyName) {
		return propertyChangeSupport != null
				&& propertyChangeSupport.hasListeners(propertyName);
	}

}
