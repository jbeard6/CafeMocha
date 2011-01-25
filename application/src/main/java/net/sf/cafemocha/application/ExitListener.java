/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application;

import java.util.EventListener;
import java.util.EventObject;

/**
 * Receives notifications that the {@link Application} intends to exit and
 * provides an opportunity for the exit to be vetoed.
 * 
 * @author computerguy5
 * 
 */
public interface ExitListener extends EventListener {

	public boolean canExit(EventObject event);

	public void willExit(EventObject event);

}
