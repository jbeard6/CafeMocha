/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application.swing;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

/**
 * Manages access to the system {@link Clipboard} by the
 * {@link SwingApplication}.
 * 
 * @author joseph
 * 
 */
public class ClipboardManager {

	/**
	 * Constructs a new {@link ClipboardManager} that manages the system
	 * clipboard.
	 * 
	 * @throws HeadlessException
	 *             if the application is not running in a graphical environment
	 */
	public ClipboardManager() throws HeadlessException {
		// TODO Check for security manager
		this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	}

	private Clipboard clipboard;

}
