/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.examples.console;

import net.sf.cafemocha.tasks.Task;
import net.sf.cafemocha.tasks.TaskExecutionContext;

/**
 * Outputs a string to the console.
 * 
 * @author computerguy5
 * 
 */
public class ConsoleTask implements Task<Void> {

	public ConsoleTask(int count) {
		this.count = count;
	}

	private final int count;

	@Override
	public String toString() {
		return "Task " + count;
	}

	public Void execute(TaskExecutionContext<Void> context) {
		System.out.println(this);
		return null;
	}

}
