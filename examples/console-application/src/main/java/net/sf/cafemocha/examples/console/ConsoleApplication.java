/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.examples.console;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.sf.cafemocha.application.Application;
import net.sf.cafemocha.tasks.ExecutorServiceTaskService;
import net.sf.cafemocha.tasks.Task;
import net.sf.cafemocha.tasks.TaskService;

/**
 * An example application that executes at the system console.
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
public class ConsoleApplication extends Application {

	public static void main(final String[] args) {
		final ConsoleApplication application = new ConsoleApplication();
		application.launch(args);

		ScheduledExecutorService service = newSingleThreadScheduledExecutor();

		Runnable exitCommand = new Runnable() {
			public void run() {
				application.exit(null);
			}
		};

		// Prompt for exit every 5 seconds until terminated
		service.scheduleAtFixedRate(exitCommand, 10L, 5L, TimeUnit.SECONDS);
	}

	public ConsoleApplication() {
		this.tasks = new ArrayList<Task<?>>();
	}

	private final List<Task<?>> tasks;

	@Override
	protected void initialize(String... arguments) {
		long taskCount = 100;

		try {
			if (arguments != null && arguments.length > 0) {
				taskCount = Long.valueOf(arguments[0]);
			}
		} catch (NumberFormatException ex) {
			System.err.println("Invalid Argument (Integer expected): "
					+ arguments[0]);
			exit(null);
		}

		for (int i = 0; i < taskCount; i++) {
			tasks.add(new ConsoleTask(i));
		}

		// Register an exit listener
		addExitListener(new ConsoleExitListener());

		TaskService service = new ExecutorServiceTaskService(
				newSingleThreadExecutor());

		getContext().setTaskService(service);
	}

	@Override
	protected void startup() {
		System.out.println("Application Started");
	}

	@Override
	protected void ready() {
		System.out.println("Application Ready");

		// Launch tasks
		TaskService taskService = getContext().getTaskService();
		for (Task<?> task : tasks) {
			taskService.execute(task);
		}
	}

	@Override
	protected void shutdown() {
		System.out.println("Application Terminating");
		super.shutdown();
	}

}
