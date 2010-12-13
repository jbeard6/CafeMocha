/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application;

import java.util.Collections;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.sf.cafemocha.beans.AbstractObservable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a simple application lifecycle.
 * 
 * @author computerguy5
 * 
 */
public abstract class Application extends AbstractObservable {

	private static final Logger LOG = LoggerFactory
			.getLogger(Application.class);

	public Application() {
		this.context = new ApplicationContext(this);
		this.exitListeners = new CopyOnWriteArrayList<ExitListener>();
	}

	/* TODO Figure out how to initialize a proper context */
	private final ApplicationContext context;

	public ApplicationContext getContext() {
		return context;
	}

	private final List<ExitListener> exitListeners;

	/**
	 * Returns the {@link ExitListener ExitListeners} that have been registered
	 * with this {@link Application}. The returned list will be unmodifiable.
	 * 
	 * @return the registered exit listeners
	 */
	public List<ExitListener> getExitListeners() {
		return Collections.unmodifiableList(exitListeners);
	}

	/**
	 * Add an {@link ExitListener} to the {@link Application}.
	 * 
	 * @param exitListener
	 *            the exit listener to add
	 */
	public void addExitListener(ExitListener exitListener) {
		this.exitListeners.add(exitListener);
	}

	/**
	 * Remove the specified {@link ExitListener} from the {@link Application}.
	 * 
	 * @param exitListener
	 *            the exit listener to be removed from the application
	 * @return <code>true</code> if the exit listener had previously been added
	 *         to the application
	 */
	public boolean removeExitListener(ExitListener exitListener) {
		return this.exitListeners.remove(exitListener);
	}

	/**
	 * Notify all registered {@link ExitListener ExitListeners} of the impending
	 * exit and terminate the application.
	 * 
	 * @param event
	 *            the event that triggered the shutdown
	 */
	public void exit(EventObject event) {
		// Verify that the application is allowed to exit
		for (ExitListener exitListener : exitListeners) {
			if (!exitListener.canExit(event)) {
				// Vetoed exit
				return;
			}
		}

		// Inform all exit listeners that we're exiting
		int exitCode = 0;
		try {
			for (ExitListener exitListener : exitListeners) {
				try {
					exitListener.willExit(event);
				} catch (Exception ex) {
					LOG.warn("ExitListener.willExit() failed", ex);
					exitCode = 2;
				}
			}

			// Last minute clean up
			shutdown();
		} catch (Exception ex) {
			LOG.error("Shutdown error.", ex);
			exitCode |= 4;
		} finally {
			// Terminate the JVM
			System.exit(exitCode);
		}
	}

	public void initialize(String... arguments) {
		// Initialize the application
	}

	protected void shutdown() {
		// Subclasses may override to clean up immediately prior to shutdown
	}

}
