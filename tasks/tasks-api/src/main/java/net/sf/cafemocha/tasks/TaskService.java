/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Asynchronously executes {@link Task Tasks}.
 * 
 * @author computerguy5
 * 
 */
public class TaskService {

	private static final int CORE_POOL_SIZE_DEFAULT = 3;

	private static final int MAXIMUM_POOL_SIZE_DEFAULT = 10;

	private static final long KEEP_ALIVE_DEFAULT = 1L;

	private static final TimeUnit KEEP_ALIVE_TIME_UNIT_DEFAULT = TimeUnit.SECONDS;

	public TaskService() {
		this(new ThreadPoolExecutor(CORE_POOL_SIZE_DEFAULT,
				MAXIMUM_POOL_SIZE_DEFAULT, KEEP_ALIVE_DEFAULT,
				KEEP_ALIVE_TIME_UNIT_DEFAULT,
				new LinkedBlockingQueue<Runnable>()));
	}

	public TaskService(ExecutorService executorService) {
		if (executorService == null) {
			throw new NullPointerException("executorService");
		}
		this.executorService = executorService;
		this.tasks = new CopyOnWriteArrayList<Task<?>>();
	}

	private final ExecutorService executorService;

	private final List<Task<?>> tasks;

	public void execute(Task<?> task) {
		if (task == null) {
			throw new NullPointerException("task");
		} else if (task.getTaskService() != null) {
			throw new IllegalArgumentException("Task already queued.");
		}

		task.setTaskService(this);
		tasks.add(task);

		// TODO executorService.submit(task)
	}

	/**
	 * Initiates an orderly shutdown in which previously submitted tasks are
	 * executed, but no new tasks will be accepted. Invocation has no additional
	 * effect if already shut down.
	 */
	public void shutdown() {
		executorService.shutdown();
	}

	// TODO public List<Task> shutdownNow() {}

	/**
	 * Blocks until all {@link Task Tasks} have completed execution after a
	 * shutdown request, or the timeout occurs, or the current thread is
	 * interrupted, whichever happens first.
	 * 
	 * @param timeout
	 *            the maximum time to wait
	 * @param unit
	 *            the time unit of the timeout argument
	 * @return <code>true</code> if this executor terminated and false if the
	 *         timeout elapsed before termination
	 * @throws InterruptedException
	 *             if interrupted while waiting
	 */
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return executorService.awaitTermination(timeout, unit);
	}

	/**
	 * Returns <code>true</code> if this {@link TaskService} has been shut down.
	 * 
	 * 
	 * @return <code>true</code> if this task service has been shut down
	 */
	public boolean isShutdown() {
		return executorService.isShutdown();
	}

	/**
	 * Returns true if all {@link Task Tasks} have completed following shutdown.
	 * Note that isTerminated is never true unless {@link #shutdown()} was
	 * called first.
	 * 
	 * @return <code>true</code> if all tasks have completed following shut down
	 */
	public boolean isTerminated() {
		return executorService.isTerminated();
	}

}
