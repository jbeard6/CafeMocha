/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.sf.cafemocha.beans.AbstractObservable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a simple application lifecycle.
 * 
 * @author computerguy5
 * 
 */
public abstract class Application extends AbstractObservable {

	private static final Logger LOG = LoggerFactory
			.getLogger(Application.class);

	public Application() {
		this.exitListeners = new CopyOnWriteArrayList<ExitListener>();
		this.context = new ApplicationContext(this);
	}

	private final List<ExitListener> exitListeners;

	private final ApplicationContext context;

}
