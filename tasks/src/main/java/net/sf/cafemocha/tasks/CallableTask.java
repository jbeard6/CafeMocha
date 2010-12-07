/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author computerguy5
 * 
 */
public class CallableTask<T> implements Callable<T> {

	private static final Logger LOG = LoggerFactory
			.getLogger(CallableTask.class);

	/**
	 * @param task
	 *            the task to be executed
	 */
	public CallableTask(Task<T> task) {
		if (task == null) {
			throw new NullPointerException("task");
		}

		this.task = task;
	}

	private final Task<T> task;

	public T call() throws Exception {
		// Indicate that the task has begun
		task.setState(ExecutionState.STARTED);
		task.fireStarted();

		// Perform the task
		try {
			T value = task.execute();

			// Indicate that the task has completed successfully
			task.setState(ExecutionState.SUCCESS);
			task.fireSucceeded(value);

			// return the value
			return value;
		} catch (InterruptedException ex) {
			LOG.info("Task Execution Interrupted", ex);

			// Indicate that the task was interrupted
			task.setState(ExecutionState.INTERRUPTED);
			task.fireInterrupted(ex);

			// Pass along the exception
			throw ex;
		} catch (Exception ex) {
			LOG.error("Task Execution Failed", ex);

			// Indicate that the task failed
			task.setState(ExecutionState.FAILED);
			task.fireFailed(ex);

			// Pass along the exception
			throw ex;
		} finally {
			// Indicate completion
			task.fireFinished();
		}
	}

}
