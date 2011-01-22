/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

import java.util.concurrent.TimeUnit;

/**
 * A {@link TaskService} that can schedule {@link Task Tasks} to execute in the
 * future.
 * 
 * @author computerguy5
 * 
 */
public interface ScheduledTaskService extends TaskService {

	/**
	 * Schedule the specified {@link Task} for execution in the future.
	 * 
	 * @param <T>
	 *            the type of result returned by the task
	 * @param task
	 *            the task to be executed
	 * @param delay
	 *            the time from now to delay execution
	 * @param unit
	 *            the time unit of the delay parameter
	 */
	public <T> void schedule(Task<T> task, long delay, TimeUnit unit);

}
