/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application.swing;

import net.sf.cafemocha.application.Application;

/**
 * A simple application lifecycle for a Swing application.
 * 
 * @author computerguy5
 * 
 */
public class SwingApplication extends Application {

	/*
	 * Convenience method that returns the covariant SwingApplicationContext.
	 * 
	 * @see net.sf.cafemocha.application.Application#getContext()
	 */
	@Override
	public SwingApplicationContext getContext() {
		// TODO Enforce safety of cast through constructor
		return (SwingApplicationContext) super.getContext();
	}

	// TODO Add Look and Feel, Window location preferences, etc.

}
