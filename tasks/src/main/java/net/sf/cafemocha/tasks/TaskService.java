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

}
