/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

/**
 * An empty implementation of the {@link TaskListener} interface.
 * 
 * @author computerguy5
 * 
 */
public abstract class TaskListenerAdapter<T> implements TaskListener<T> {

	public void started(TaskEvent<Void> event) {
	}

	public void interrupted(TaskEvent<InterruptedException> event) {
	}

	public void canceled(TaskEvent<Void> event) {
	}

	public void failed(TaskEvent<Throwable> event) {
	}

	public void succeeded(TaskEvent<? extends T> event) {
	}

	public void finished(TaskEvent<Void> event) {
	}

}
