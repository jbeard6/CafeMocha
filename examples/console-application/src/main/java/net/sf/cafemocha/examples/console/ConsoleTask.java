/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.examples.console;

import net.sf.cafemocha.tasks.Task;

/**
 * Outputs a string to the console.
 * 
 * @author computerguy5
 * 
 */
public class ConsoleTask extends Task<Void> {

	public ConsoleTask(int count) {
		this.count = count;
	}

	private final int count;

	@Override
	public String toString() {
		return "Task " + count;
	}

	@Override
	protected Void execute() throws Exception {
		System.out.println(toString());
		return null;
	}

}
