/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

/**
 * Indicates the current execution state of a {@link Task}.
 * 
 * @author computerguy5
 * 
 */
public enum ExecutionState {
	/**
	 * The {@link Task} has not yet been started.
	 */
	PENDING,

	/**
	 * The {@link Task} has begun execution, but has not yet terminated.
	 */
	STARTED,

	/**
	 * The {@link Task} has terminated by interruption.
	 */
	INTERRUPTED,

	/**
	 * The {@link Task} has been canceled.
	 */
	CANCELED,

	/**
	 * The {@link Task} has terminated unsuccessfully.
	 */
	FAILED,

	/**
	 * The {@link Task} has completed successfully.
	 */
	SUCCESS;
}
