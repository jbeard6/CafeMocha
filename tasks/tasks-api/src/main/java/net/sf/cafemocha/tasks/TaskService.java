/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

/**
 * A service to which {@link Task Tasks} may be submitted for asynchronous
 * execution.
 * 
 * @author computerguy5
 * 
 */
public interface TaskService {

	/**
	 * Schedule the specified {@link Task} for execution.
	 * 
	 * @param <T>
	 *            the type of result returned by the task
	 * @param task
	 *            the task to be executed
	 */
	public <T> void execute(Task<T> task);

	/**
	 * Initiates an orderly shutdown in which previously submitted {@link Task
	 * Tasks} are executed, but no new tasks will be accepted. Invocation has
	 * nod additional effect if already shut down.
	 */
	public void shutdown();

	/**
	 * Returns <code>true</code> if this {@link TaskService} has been
	 * {@link #shutdown()}.
	 * 
	 * @return <code>true</code> if this task service is shutdown
	 */
	public boolean isShutdown();

	/**
	 * Returns <code>true</code> if this {@link TaskService} has been
	 * {@link #shutdown()} and all {@link Task Tasks} have completed execution.
	 * 
	 * @return <code>true</code> if all task execution has terminated
	 */
	public boolean isTerminated();

}
