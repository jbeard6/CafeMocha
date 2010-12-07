/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

import java.util.EventObject;

/**
 * An {@link EventObject Event} occurring on a {@link Task}.
 * 
 * @author computerguy5
 * 
 * @param <T>
 *            the type of value associated with this {@link TaskEvent}
 */
public class TaskEvent<T> extends EventObject {
	private static final long serialVersionUID = -7713134693804465218L;

	/**
	 * Convenience method for creating a {@link TaskEvent} that represents the
	 * start of execution of the specified {@link Task}.
	 * 
	 * @param task
	 *            the task that has started
	 * @return task event that represents the start of execution of the
	 *         specified task
	 */
	public static TaskEvent<Void> started(Task<?> task) {
		return new TaskEvent<Void>(task, null);
	}

	/**
	 * Convenience method for creating a {@link TaskEvent} that represents the
	 * interruption of the specified {@link Task}.
	 * 
	 * @param task
	 *            the task that has been interrupted
	 * @param exception
	 *            the interrupted exception
	 * @return task event that represents the interruption of the specified task
	 */
	public static TaskEvent<InterruptedException> interrupted(Task<?> task,
			InterruptedException exception) {
		return new TaskEvent<InterruptedException>(task, exception);
	}

	/**
	 * Convenience method for creating a {@link TaskEvent} that represents the
	 * cancellation of the specified {@link Task}.
	 * 
	 * @param task
	 *            the task that has been canceled
	 * @return task event that represents the cancellation of the specified task
	 */
	public static TaskEvent<Void> canceled(Task<?> task) {
		return new TaskEvent<Void>(task, null);
	}

	/**
	 * Convenience method for creating a {@link TaskEvent} that represents the
	 * failure of the specified {@link Task}.
	 * 
	 * @param task
	 *            the task that has failed
	 * @param exception
	 *            the failure exception
	 * @return task event that represents the failure of the specified task
	 */
	public static TaskEvent<Throwable> failed(Task<?> task, Throwable exception) {
		return new TaskEvent<Throwable>(task, exception);
	}

	/**
	 * Convenience method for creating a {@link TaskEvent} that represents the
	 * completion of the specified {@link Task}.
	 * 
	 * @param task
	 *            the task that has completed
	 * @param value
	 *            the value computed by the task
	 * @return task event that represents the completion of the specified task
	 */
	public static <T> TaskEvent<T> succeeded(Task<T> task, T value) {
		return new TaskEvent<T>(task, value);
	}

	/**
	 * Convenience method for creating a {@link TaskEvent} that represents the
	 * completion of the specified {@link Task}.
	 * 
	 * @param task
	 *            the task that has completed
	 * @return task event that represents the completion of the specified task
	 */
	public static TaskEvent<Void> finished(Task<?> task) {
		return new TaskEvent<Void>(task, null);
	}

	/**
	 * Construct a new {@link TaskEvent} that represents the specified
	 * <code>value</code> associated with the specified {@link Task}.
	 * 
	 * @param source
	 *            the task that generated this event
	 * @param value
	 *            the value associated with this task event
	 */
	public TaskEvent(Task<?> source, T value) {
		super(source);
		this.value = value;
	}

	private final T value;

	/**
	 * Returns the value that this {@link TaskEvent} represents.
	 * 
	 * @return the value associated with this task event
	 */
	public T getValue() {
		return value;
	}

}
