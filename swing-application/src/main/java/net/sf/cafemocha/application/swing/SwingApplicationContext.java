/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application.swing;

import net.sf.cafemocha.application.ApplicationContext;

/**
 * Refinement of the {@link ApplicationContext} for a {@link SwingApplication}.
 * 
 * @author computerguy5
 * 
 */
public class SwingApplicationContext extends ApplicationContext {

	/**
	 * @param application
	 */
	public SwingApplicationContext(SwingApplication application) {
		super(application);

		// Manage the system clipboard
		this.clipboardManager = ClipboardManager.systemClipboardManager();
	}

	/*
	 * Convenience method with covariant return type.
	 * 
	 * @see net.sf.cafemocha.application.ApplicationContext#getApplication()
	 */
	@Override
	public SwingApplication getApplication() {
		// SwingApplication type is enforced by constructor
		return (SwingApplication) super.getApplication();
	}

	public static final String CLIPBOARD_MANAGER_PROPERTY = "clipboardManager";

	private ClipboardManager clipboardManager;

	/**
	 * @return the clipboardManager
	 */
	public ClipboardManager getClipboardManager() {
		return clipboardManager;
	}

	/**
	 * @param clipboardManager
	 *            the clipboardManager to set
	 */
	public void setClipboardManager(ClipboardManager clipboardManager) {
		ClipboardManager oldValue = this.clipboardManager;
		this.clipboardManager = clipboardManager;
		firePropertyChange(CLIPBOARD_MANAGER_PROPERTY, oldValue,
				clipboardManager);
	}

	public static final String ACTION_MANAGER_PROPERTY = "actionManager";

	private ActionManager actionManager;

	/**
	 * @return the actionManager
	 */
	public ActionManager getActionManager() {
		return actionManager;
	}

	/**
	 * @param actionManager
	 *            the actionManager to set
	 */
	public void setActionManager(ActionManager actionManager) {
		ActionManager oldValue = this.actionManager;
		this.actionManager = actionManager;
		firePropertyChange(ACTION_MANAGER_PROPERTY, oldValue, actionManager);
	}

}
