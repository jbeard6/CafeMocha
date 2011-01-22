/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import net.sf.cafemocha.tasks.Task;
import net.sf.cafemocha.tasks.TaskExecutor;
import net.sf.cafemocha.tasks.TaskService;

/**
 * A {@link TaskService} that executes {@link Task Tasks} using an
 * {@link ExecutorService}.
 * 
 * @author computerguy5
 * 
 */
public class ExecutorServiceTaskService implements TaskService {

	public ExecutorServiceTaskService(ExecutorService executorService) {
		if (executorService == null) {
			throw new NullPointerException("executorService");
		}
		this.executorService = executorService;
	}

	private final ExecutorService executorService;

	public <T> void execute(Task<T> task) {
		if (task == null) {
			throw new NullPointerException("task");
		}

		// Create an executor that defines the lifecycle of the task
		TaskExecutor<T> executor = new TaskExecutor<T>(this, task);

		// Submit the executor runnable to the executor service
		executorService.submit(executor);
	}

	public void shutdown() {
		executorService.shutdown();
	}

	/**
	 * Blocks until all {@link Task Tasks} have completed execution after a
	 * shutdown request, or the timeout occurs, or the current thread is
	 * interrupted, whichever happens first.
	 * 
	 * @param timeout
	 *            the maximum time to wait
	 * @param unit
	 *            the time unit of the timeout argument
	 * @return <code>true</code> if this task service terminated and false if
	 *         the timeout elapsed before termination
	 * @throws InterruptedException
	 *             if interrupted while waiting
	 */
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return executorService.awaitTermination(timeout, unit);
	}

	public boolean isShutdown() {
		return executorService.isShutdown();
	}

	public boolean isTerminated() {
		return executorService.isTerminated();
	}

}
