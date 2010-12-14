/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application;

import java.util.EventObject;

/**
 * An empty implementation of the {@link ExitListener} interface.
 * 
 * @author computerguy5
 * 
 */
public abstract class ExitAdapter implements ExitListener {

	public boolean canExit(EventObject event) {
		// Don't prevent exiting
		return true;
	}

	public void willExit(EventObject event) {
		// Empty implementation
	}

}
