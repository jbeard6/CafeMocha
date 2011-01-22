/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application.swing;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import net.sf.cafemocha.tasks.TaskEvent;
import net.sf.cafemocha.tasks.TaskListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of a {@link TaskListener} that fires events on the Event
 * Dispatch Thread (EDT).
 * 
 * TODO Handle exceptions in EDT methods more appropriately than just logging
 * 
 * @author computerguy5
 * 
 */
public abstract class SwingTaskListenerAdapter<T> implements TaskListener<T> {

	private static final Logger LOG = LoggerFactory
			.getLogger(SwingTaskListenerAdapter.class);

	public final void started(final TaskEvent<Void> event) {
		try {
			if (!SwingUtilities.isEventDispatchThread()) {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						startedEdt(event);
					}
				});
			} else {
				startedEdt(event);
			}
		} catch (InterruptedException ex) {
			LOG.warn("Interrupted dispatching started event on EDT", ex);
		} catch (InvocationTargetException ex) {
			LOG.warn("Error dispatching started event on EDT", ex);
		}
	}

	protected void startedEdt(TaskEvent<Void> event) {
	}

	public void canceled(final TaskEvent<Void> event) {
		try {
			if (!SwingUtilities.isEventDispatchThread()) {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						canceledEdt(event);
					}
				});
			} else {
				canceledEdt(event);
			}
		} catch (InterruptedException ex) {
			LOG.warn("Interrupted dispatching canceled event on EDT", ex);
		} catch (InvocationTargetException ex) {
			LOG.warn("Error dispatching canceled event on EDT", ex);
		}
	}

	protected void canceledEdt(TaskEvent<Void> event) {
	}

	public void interrupted(final TaskEvent<InterruptedException> event) {
		try {
			if (!SwingUtilities.isEventDispatchThread()) {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						interruptedEdt(event);
					}
				});
			} else {
				interruptedEdt(event);
			}
		} catch (InterruptedException ex) {
			LOG.warn("Interrupted dispatching interrupted event on EDT", ex);
		} catch (InvocationTargetException ex) {
			LOG.warn("Error dispatching interrupted event on EDT", ex);
		}
	}

	protected void interruptedEdt(TaskEvent<InterruptedException> event) {
	}

	public void failed(final TaskEvent<Throwable> event) {
		try {
			if (!SwingUtilities.isEventDispatchThread()) {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						failedEdt(event);
					}
				});
			} else {
				failedEdt(event);
			}
		} catch (InterruptedException ex) {
			LOG.warn("Interrupted dispatching failed event on EDT", ex);
		} catch (InvocationTargetException ex) {
			LOG.warn("Error dispatching failed event on EDT", ex);
		}
	}

	protected void failedEdt(TaskEvent<Throwable> event) {
	}

	public void succeeded(final TaskEvent<? extends T> event) {
		try {
			if (!SwingUtilities.isEventDispatchThread()) {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						suceededEdt(event);
					}
				});
			} else {
				suceededEdt(event);
			}
		} catch (InterruptedException ex) {
			LOG.warn("Interrupted dispatching suceeded event on EDT", ex);
		} catch (InvocationTargetException ex) {
			LOG.warn("Error dispatching suceeded event on EDT", ex);
		}
	}

	protected void suceededEdt(TaskEvent<? extends T> event) {
	}

	public void finished(final TaskEvent<Void> event) {
		try {
			if (!SwingUtilities.isEventDispatchThread()) {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						finishedEdt(event);
					}
				});
			} else {
				finishedEdt(event);
			}
		} catch (InterruptedException ex) {
			LOG.warn("Interrupted dispatching finished event on EDT", ex);
		} catch (InvocationTargetException ex) {
			LOG.warn("Error dispatching finished event on EDT", ex);
		}
	}

	protected void finishedEdt(TaskEvent<Void> event) {
	}

}
