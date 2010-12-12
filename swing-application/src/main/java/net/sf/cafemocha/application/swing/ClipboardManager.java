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
 * Manages access to the {@link Clipboard} by the {@link SwingApplication}.
 * 
 * @author computerguy5
 * 
 */
public class ClipboardManager {

	/**
	 * Constructs a new {@link ClipboardManager} that manages the system
	 * clipboard.
	 * 
	 * @throws HeadlessException
	 *             if the application is not running in a graphical environment
	 * @see Toolkit#getSystemClipboard()
	 */
	public static ClipboardManager systemClipboardManager()
			throws HeadlessException {
		// TODO Check for security manager?
		return new ClipboardManager(Toolkit.getDefaultToolkit()
				.getSystemClipboard());
	}

	/**
	 * Construct a new {@link ClipboardManager manager} for the specified
	 * {@link Clipboard}.
	 * 
	 * @param clipboard
	 *            the clipboard to be managed
	 * @throws NullPointerException
	 *             if <code>clipboard</code> is <code>null</code>
	 */
	public ClipboardManager(Clipboard clipboard) {
		if (clipboard == null) {
			throw new NullPointerException("clipboard");
		}
		this.clipboard = clipboard;
	}

	private final Clipboard clipboard;

	/**
	 * @return the clipboard
	 */
	public Clipboard getClipboard() {
		return clipboard;
	}

}
