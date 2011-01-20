/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.tasks;

/**
 * Abstraction for code that is to be executed asynchronously.
 * 
 * @author computerguy5
 * 
 */
public interface Task<T> {

	/**
	 * Performs the work of the {@link Task}.
	 * 
	 * @param context
	 *            the execution context in which the execute method is invoked
	 * @return the result of the task
	 * @throws Exception
	 *             if an error occurs while executing the task
	 */
	public T execute(TaskExecutionContext<T> context) throws Exception;
}
