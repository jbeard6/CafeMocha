/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.examples.console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.sf.cafemocha.application.swing.SwingApplication;

/**
 * An example Swing application.
 * 
 * TODO Demo persistence
 * 
 * TODO Demo resources by obtaining messages from bundle
 * 
 * TODO Demo validation
 * 
 * TODO Demo tasks
 * 
 * @author computerguy5
 * 
 */
public class ExampleSwingApplication extends SwingApplication {

	public static void main(final String[] args) {
		final ExampleSwingApplication application = new ExampleSwingApplication();
		application.launch(args);
	}

	private JFrame frame;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.cafemocha.application.Application#initialize(java.lang.String[])
	 */
	@Override
	protected void initialize(String... arguments) {
		frame = new JFrame("Swing Application Example");

		frame.setLayout(new BorderLayout());

		JLabel helloWorldLabel = new JLabel("Hello World");
		frame.add(helloWorldLabel, BorderLayout.CENTER);

		JButton exitButton = new JButton(new ExitApplicationAction());
		frame.add(exitButton, BorderLayout.SOUTH);

		// Size window appropriately
		frame.pack();

		// Register an exit listener
		addExitListener(new OptionPaneExitListener(frame));
	}

	@Override
	protected void startup() {
		// Center on screen
		frame.setLocationRelativeTo(null);

		// Display the application window
		frame.setVisible(true);

		System.out.println("Application Started");
	}

	@Override
	protected void ready() {
		System.out.println("Application Ready");
	}

	@Override
	protected void shutdown() {
		System.out.println("Application Terminating");
		super.shutdown();
	}

	private class ExitApplicationAction extends AbstractAction {
		private static final long serialVersionUID = 5765492451065538181L;

		public ExitApplicationAction() {
			super("Exit");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent event) {
			// Send the event to the application to initiate a shutdown
			exit(event);
		}

	}

}
