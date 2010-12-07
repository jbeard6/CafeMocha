/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

/**
 * @author computerguy5
 * 
 */
public interface TaskListener<T> {

	/**
	 * Called when a {@link Task} has begun execution.
	 * 
	 * @param event
	 *            a TaskEvent whose source is the {@code Task} object
	 */
	public void started(TaskEvent<Void> event);

	/**
	 * Called when a {@link Task} was interrupted during execution prior to
	 * completion.
	 * 
	 * @param event
	 *            a TaskEvent whose source is the {@code Task} object
	 */
	public void interrupted(TaskEvent<InterruptedException> event);

	/**
	 * Called when a {@link Task} has been canceled prior to completion.
	 * 
	 * @param event
	 *            a TaskEvent whose source is the {@code Task} object
	 */
	public void canceled(TaskEvent<Void> event);

	/**
	 * Called when a {@link Task} has completed in error.
	 * 
	 * @param event
	 *            a TaskEvent whose source is the {@code Task} object
	 */
	public void failed(TaskEvent<Throwable> event);

	/**
	 * Called when a {@link Task} has completed successfully.
	 * 
	 * @param event
	 *            a TaskEvent whose source is the {@code Task} object
	 */
	public void succeeded(TaskEvent<? extends T> event);

	/**
	 * Called when a {@link Task} has completed, either successfully or in
	 * error. This method is executed after the execution of the corresponding
	 * {@link #succeeded(TaskEvent)}, {@link #interrupted(TaskEvent)}, or
	 * {@link #failed(TaskEvent)} method.
	 * 
	 * @param event
	 *            a TaskEvent whose source is the {@code Task} object
	 */
	public void finished(TaskEvent<Void> event);

}
