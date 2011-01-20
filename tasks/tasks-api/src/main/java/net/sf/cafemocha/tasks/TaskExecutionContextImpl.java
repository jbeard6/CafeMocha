/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link TaskExecutionContext} implementation that allows a
 * {@link TaskExecutor} to fire events and other such things.
 * 
 * This class is package private so that it can only be used by a
 * {@link TaskExecutor} only.
 * 
 * @author computerguy5
 * 
 */
class TaskExecutionContextImpl<T> implements TaskExecutionContext<T> {

	private static final Logger LOG = LoggerFactory
			.getLogger(TaskExecutionContextImpl.class);

	/**
	 * @param task
	 *            the task associated with this execution context
	 */
	public TaskExecutionContextImpl(Task<T> task) {
		this.task = task;
	}

	private final Task<T> task;

	/**
	 * Returns the {@link Task}
	 * 
	 * @return the task associated with this execution context
	 */
	public Task<T> getTask() {
		return task;
	}

	/**
	 * Using a {@link CopyOnWriteArrayList} to make iteration easier.
	 */
	private final List<TaskListener<? super T>> listeners = new CopyOnWriteArrayList<TaskListener<? super T>>();

	/**
	 * Add the specified {@link TaskListener} to receive {@link TaskEvent
	 * events} for this {@link Task Task's} execution.
	 * 
	 * @param listener
	 *            the listener to add
	 * @throws NullPointerException
	 *             if <code>listener</code> is <code>null</code>
	 */
	public void addTaskListener(TaskListener<? super T> listener) {
		if (listener == null) {
			throw new NullPointerException("listener");
		}

		listeners.add(listener);
		LOG.debug("added listener: {}", listener);
	}

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
	public boolean removeTaskListener(TaskListener<? super T> listener) {
		if (listener == null) {
			throw new NullPointerException("listener");
		}

		boolean removed = listeners.remove(listener);

		if (removed) {
			LOG.debug("removed listener: {}", listener);
		}

		return removed;
	}

	void fireStarted() {
		TaskEvent<Void> event = TaskEvent.started(task);
		LOG.debug("firing started event: {}", event);
		for (TaskListener<? super T> listener : listeners) {
			listener.started(event);
		}
	}

	void fireInterrupted(InterruptedException exception) {
		TaskEvent<InterruptedException> event = TaskEvent.interrupted(task,
				exception);
		LOG.debug("firing interrupted event: {}", event);
		for (TaskListener<? super T> listener : listeners) {
			listener.interrupted(event);
		}
	}

	void fireCanceled() {
		TaskEvent<Void> event = TaskEvent.started(task);
		LOG.debug("firing canceled event: {}", event);
		for (TaskListener<? super T> listener : listeners) {
			listener.canceled(event);
		}
	}

	void fireFailed(Throwable exception) {
		TaskEvent<Throwable> event = TaskEvent.failed(task, exception);
		LOG.debug("firing failed event: {}", event);
		for (TaskListener<? super T> listener : listeners) {
			listener.failed(event);
		}
	}

	void fireSucceeded(T value) {
		TaskEvent<T> event = TaskEvent.succeeded(task, value);
		LOG.debug("firing succeeded event: {}", event);
		for (TaskListener<? super T> listener : listeners) {
			listener.succeeded(event);
		}
	}

	void fireFinished() {
		TaskEvent<Void> event = TaskEvent.finished(task);
		LOG.debug("firing finished event: {}", event);
		for (TaskListener<? super T> listener : listeners) {
			listener.finished(event);
		}
	}

	private long started;

	public long getStarted() {
		return started;
	}

	void started() {
		this.started = System.currentTimeMillis();
	}

	private long finished;

	public long getFinished() {
		return finished;
	}

	void finished() {
		this.finished = System.currentTimeMillis();
	}

	private volatile ExecutionState state = ExecutionState.PENDING;

	/**
	 * Returns the {@link ExecutionState} of the {@link Task}.
	 * 
	 * @return the execution state of the task
	 */
	public ExecutionState getState() {
		return state;
	}

	protected final void setState(ExecutionState state) {
		this.state = state;
		LOG.debug("state: {}", state);
	}

	/**
	 * Returns <code>true</code> if the {@link Task} is pending execution.
	 * 
	 * @return true if the task is pending execution
	 */
	public boolean isPending() {
		return state == ExecutionState.PENDING;
	}

	/**
	 * Returns <code>true</code> if the {@link Task} execution has started, but
	 * not yet terminated.
	 * 
	 * @return <code>true</code> if the task execution has started but not
	 *         completed
	 */
	public boolean isStarted() {
		return state == ExecutionState.STARTED;
	}

	/**
	 * Returns <code>true</code> if the {@link Task} execution has terminated
	 * either successfully or in error.
	 * 
	 * @return <code>true</code> if the task execution has terminated
	 */
	public boolean isFinished() {
		ExecutionState state = this.state;

		return state == ExecutionState.INTERRUPTED
				|| state == ExecutionState.CANCELED
				|| state == ExecutionState.FAILED
				|| state == ExecutionState.SUCCESS;
	}

	/**
	 * Returns <code>true</code> if the {@link Task} execution was interrupted.
	 * 
	 * @return true if the task was interrupted
	 */
	public boolean isInterrupted() {
		return state == ExecutionState.INTERRUPTED;
	}

	/**
	 * Returns <code>true</code> if the {@link Task} was canceled.
	 * 
	 * @return <code>true</code> if the task was canceled
	 */
	public boolean isCanceled() {
		return state == ExecutionState.CANCELED;
	}

	/**
	 * Returns <code>true</code> if the {@link Task} has terminated in error.
	 * 
	 * @return <code>true</code> if the task has terminated in error
	 */
	public boolean isFailed() {
		return state == ExecutionState.FAILED;
	}

	/**
	 * Returns <code>true</code> if the {@link Task} completed normally.
	 * 
	 * @return true if the task executed successfully
	 */
	public boolean isSuccessful() {
		return state == ExecutionState.SUCCESS;
	}

	/**
	 * The execution progress of the {@link Task}. This is considered to be a
	 * percentage. In other words, a value of <code>58</code> is interpreted as
	 * <code>58%</code>.
	 */
	private volatile int progress;

	/**
	 * Returns the execution progress of this {@link Task} as a percentage. In
	 * other words, a value of <code>58</code> is interpreted as
	 * <code>58%</code>.
	 * 
	 * @return progress of execution
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * Set the execution progress of this {@link Task}.
	 * 
	 * @param progress
	 *            the progress (percentage) of the task execution
	 */
	protected final void setProgress(int progress) {
		if (progress < 0 || progress > 100) {
			throw new IllegalArgumentException(
					"progress out of range: 0 <= progress <= 100");
		}

		this.progress = progress;
		LOG.debug("progress: {}", progress);
	}

	/**
	 * A convenience method that sets the {@code progress} property to the
	 * following ratio normalized to 0 .. 100.
	 * 
	 * <pre>
	 * value - min / max - min
	 * </pre>
	 * 
	 * @param value
	 *            a value in the range min ... max, inclusive
	 * @param min
	 *            the minimum value of the range
	 * @param max
	 *            the maximum value of the range
	 * @see #setProgress(int)
	 */
	protected final void setProgress(int value, int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("invalid range: min >= max");
		} else if ((value < min) || (value > max)) {
			throw new IllegalArgumentException("invalid value");
		}
		float percentage = (float) (value - min) / (float) (max - min);
		setProgress(Math.round(percentage * 100.0f));
	}

	/**
	 * A convenience method that sets the {@code progress} property to
	 * <code>percentage * 100</code>.
	 * 
	 * @param percentage
	 *            a value in the range 0.0 ... 1.0 inclusive
	 * @see #setProgress(int)
	 */
	protected final void setProgress(float percentage) {
		if ((percentage < 0.0) || (percentage > 1.0)) {
			throw new IllegalArgumentException("invalid percentage");
		}
		setProgress(Math.round(percentage * 100.0f));
	}

	/**
	 * A convenience method that sets the {@code progress} property to the
	 * following ratio normalized to 0 .. 100.
	 * 
	 * <pre>
	 * value - min / max - min
	 * </pre>
	 * 
	 * @param value
	 *            a value in the range min ... max, inclusive
	 * @param min
	 *            the minimum value of the range
	 * @param max
	 *            the maximum value of the range
	 * @see #setProgress(int)
	 */
	protected final void setProgress(float value, float min, float max) {
		if (min >= max) {
			throw new IllegalArgumentException("invalid range: min >= max");
		} else if ((value < min) || (value > max)) {
			throw new IllegalArgumentException("invalid value");
		}
		float percentage = (value - min) / (max - min);
		setProgress(Math.round(percentage * 100.0f));
	}

}
