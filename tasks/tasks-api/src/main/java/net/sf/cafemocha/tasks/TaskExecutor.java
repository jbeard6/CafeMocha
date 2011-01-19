/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Executes the body of a {@link Task}, firing events as appropriate.
 * 
 * @author computerguy5
 * 
 */
public class TaskExecutor<T> implements Runnable {

	private static final Logger LOG = LoggerFactory
			.getLogger(TaskExecutor.class);

	/**
	 * Construct a new {@link TaskExecutor} that will execute the specified
	 * {@link Task} for the specified {@link TaskService}.
	 * 
	 * @param taskService
	 *            the task service for which the task will be executed
	 * @param task
	 *            the task to be executed
	 * @throws NullPointerException
	 *             if either <code>taskService</code> or <code>task</code> is
	 *             <code>null</code>
	 */
	public TaskExecutor(TaskService taskService, Task<T> task) {
		this.taskService = taskService;
		this.task = task;
	}

	private final TaskService taskService;

	/**
	 * Returns the {@link TaskService} for which the {@link Task} is executed.
	 * 
	 * @return the task service for which the task is executed
	 */
	public TaskService getTaskService() {
		return taskService;
	}

	private final Task<T> task;

	/**
	 * Returns the {@link Task} being executed.
	 * 
	 * @return the executed task
	 */
	public Task<T> getTask() {
		return task;
	}

	private T result;

	/**
	 * Returns the result of the {@link Task} execution.
	 * 
	 * @return the result of the task execution
	 */
	public T getResult() {
		return result;
	}

	public void run() {
		LOG.trace("ENTER");

		try {
			// TODO started = System.currentTimeMillis();
			// TODO fireStarted();

			result = task.execute();

			// TODO fireSuccess(result);
		} catch (InterruptedException ex) {
			// TODO fireInterrupted(ex);
		} catch (Exception ex) {
			// TODO fireFailed(ex);
		} finally {
			// TODO fireFinished();
			// TODO finished = System.currentTimeMillis();
		}
	}

}
