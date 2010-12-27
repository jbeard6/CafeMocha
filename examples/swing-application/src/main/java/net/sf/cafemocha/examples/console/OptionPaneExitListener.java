/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.examples.console;

import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JOptionPane;

import net.sf.cafemocha.application.ExitAdapter;
import net.sf.cafemocha.application.ExitListener;

/**
 * Asks the user if the application can exit with a {@link JOptionPane}.
 * 
 * @author computerguy5
 * 
 */
public class OptionPaneExitListener extends ExitAdapter {

	/**
	 * Constructs a new {@link ExitListener} that prompts the user to confirm
	 * the termination of the application via a {@link JOptionPane}.
	 */
	public OptionPaneExitListener() {
		this(null);
	}

	/**
	 * Constructs a new {@link ExitListener} that prompts the user to confirm
	 * the termination of the application via a {@link JOptionPane}.
	 * 
	 * @param parent
	 *            the parent of the dialog
	 */
	public OptionPaneExitListener(Component parent) {
		this.parent = parent;
	}

	private final Component parent;

	public boolean canExit(EventObject event) {
		return OK_OPTION == showConfirmDialog(parent,
				"Are you sure you want to exit?", "Terminate Application",
				YES_NO_OPTION, QUESTION_MESSAGE);
	}

}
