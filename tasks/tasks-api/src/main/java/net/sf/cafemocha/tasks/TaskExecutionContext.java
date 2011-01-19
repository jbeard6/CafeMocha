/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

/**
 * A context to describe the current state of a {@link Task} execution.
 * 
 * @author computerguy5
 * 
 */
public interface TaskExecutionContext<T> {

	/**
	 * Returns the time that the execution started in milliseconds since the
	 * standard base time known as "the epoch", namely January 1, 1970, 00:00:00
	 * GMT.
	 * 
	 * @return the time that execution started
	 */
	public long getStarted();

	/**
	 * Returns the time that the execution finished in milliseconds since the
	 * standard base time known as "the epoch", namely January 1, 1970, 00:00:00
	 * GMT.
	 * 
	 * @return the time that execution finished
	 */
	public long getFinished();

	/**
	 * Add the specified {@link TaskListener} to receive {@link TaskEvent
	 * events} for this {@link Task Task's} execution.
	 * 
	 * @param listener
	 *            the listener to add
	 * @throws NullPointerException
	 *             if <code>listener</code> is <code>null</code>
	 */
	public void addTaskListener(TaskListener<? super T> listener);

	/**
	 * Remove the specified {@link TaskListener} from receiving
	 * {@link TaskEvent events} for this {@link Task}.
	 * 
	 * @param listener
	 *            the listener to be removed
	 * @return true if the listener was previously registered and has been
	 *         removed
	 * @throws NullPointerException
	 *             if <code>listener</code> is <code>null</code>
	 */
	public boolean removeTaskListener(TaskListener<? super T> listener);

	/**
	 * Returns the {@link ExecutionState} of the {@link Task} in this
	 * {@link TaskExecutionContext}.
	 * 
	 * @return the execution state of the task in this context
	 */
	public ExecutionState getState();

	/**
	 * Returns the execution progress of the {@link Task} as a percentage. In
	 * other words, a value of <code>58</code> is interpreted as
	 * <code>58%</code>.
	 * 
	 * @return progress of execution
	 */
	public int getProgress();
}
