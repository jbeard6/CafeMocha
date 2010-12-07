/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.beans;

import java.beans.PropertyChangeListener;

/**
 * Interface for objects that have properties that can be observed for changes.
 * 
 * @author computerguy5
 * 
 */
public interface Observable {

	/**
	 * Adds the given {@link PropertyChangeListener} to the listener list. The
	 * listener is registered for all bound properties of this class.
	 * 
	 * @param listener
	 *            the PropertyChangeListener to be added
	 * 
	 * @see #removePropertyChangeListener(PropertyChangeListener)
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Adds the given {@link PropertyChangeListener} to the listener list. The
	 * listener is registered for all bound properties of this class.
	 * 
	 * @param property
	 *            the property for which to be notified when changed
	 * @param listener
	 *            the PropertyChangeListener to be added
	 * 
	 * @see #removePropertyChangeListener(PropertyChangeListener)
	 */
	void addPropertyChangeListener(String property,
			PropertyChangeListener listener);

	/**
	 * Removes the given {@link PropertyChangeListener} from the listener list.
	 * This method should be used to remove PropertyChangeListeners that were
	 * registered for all bound properties of this class.
	 * 
	 * @param listener
	 *            the PropertyChangeListener to be removed
	 * 
	 * @see #addPropertyChangeListener(PropertyChangeListener)
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Removes the given {@link PropertyChangeListener} from the listener list.
	 * This method should be used to remove PropertyChangeListeners that were
	 * registered for all bound properties of this class.
	 * 
	 * 
	 * @param property
	 *            the property for which to no longer be notified when changed
	 * @param listener
	 *            the PropertyChangeListener to be removed
	 * 
	 * @see #addPropertyChangeListener(PropertyChangeListener)
	 */
	void removePropertyChangeListener(String property,
			PropertyChangeListener listener);
}
