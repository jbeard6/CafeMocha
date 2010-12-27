/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.examples.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.EventObject;

import net.sf.cafemocha.application.ExitAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Asks the user if the application can exit.
 * 
 * @author computerguy5
 * 
 */
public class ConsoleExitListener extends ExitAdapter {

	private static final Logger LOG = LoggerFactory
			.getLogger(ConsoleExitListener.class);

	public boolean canExit(EventObject event) {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

		try {
			String response = null;
			while ((response = readLine("Exit Application? [Y|n] ", System.out,
					r)) != null) {
				if ("y".equalsIgnoreCase(response)) {
					return true;
				} else if ("n".equalsIgnoreCase(response)) {
					System.out.println("Exit Aborted");
					return false;
				}

				// Another response altogether; ask again
			}
		} catch (IOException ex) {
			LOG.error("Failed to read response", ex);
			return true;
		}

		// End of input reached; allow exit
		return true;
	}

	private String readLine(String prompt, PrintStream out, BufferedReader in)
			throws IOException {
		out.print(prompt);
		out.flush();

		return in.readLine();
	}

}
