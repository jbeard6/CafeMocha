/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application.swing;

import static java.awt.EventQueue.invokeAndWait;
import static java.awt.EventQueue.isDispatchThread;

import java.lang.reflect.InvocationTargetException;

import net.sf.cafemocha.application.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple application lifecycle for a Swing application.
 * 
 * @author computerguy5
 * 
 */
public abstract class SwingApplication extends Application {

	private static final Logger LOG = LoggerFactory
			.getLogger(SwingApplication.class);

	@Override
	protected SwingApplicationContext createContext() {
		return new SwingApplicationContext(this);
	}

	/*
	 * Convenience method that returns the covariant SwingApplicationContext.
	 * 
	 * @see net.sf.cafemocha.application.Application#getContext()
	 */
	@Override
	public SwingApplicationContext getContext() {
		// Cast safety is enforced via createContext()
		return (SwingApplicationContext) super.getContext();
	}

	@Override
	public final void launch(String... arguments) {
		initApplication(this);

		// Initialize the application on calling thread
		initialize(arguments);

		// Start the application on the event dispatching thread
		if (isDispatchThread()) {
			launchOnEdt();
		} else {
			Runnable edtLaunch = new Runnable() {
				public void run() {
					launchOnEdt();
				}
			};

			try {
				// Re-dispatch on EDT
				invokeAndWait(edtLaunch);
			} catch (InterruptedException ex) {
				LOG.error("Application Launch Interrupted", ex);
				// Send back to calling thread
				throw new RuntimeException("Application Launch Interrupted", ex);
			} catch (InvocationTargetException ex) {
				// An uncaught exception occurred in launch
				// Send back to calling thread
				throw new RuntimeException("Application Launch Error", ex);
			}
		}
	}

	private void launchOnEdt() {
		// Launch the application lifecycle
		startup();

		// TODO Wait for empty event queue before sending ready event
		ready();
	}

	// TODO Add Look and Feel, Window location preferences, etc.

}
