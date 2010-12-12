/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application;

import net.sf.cafemocha.beans.AbstractObservable;
import net.sf.cafemocha.tasks.TaskService;

/**
 * Maintains the execution state of an {@link Application}, including access to
 * resources and data storage.
 * 
 * @author computerguy5
 * 
 */
public class ApplicationContext extends AbstractObservable {

	public ApplicationContext(Application application) {
		if (application == null) {
			throw new NullPointerException("application");
		}
		this.application = application;
	}

	private final Application application;

	/**
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}

	public static final String RESOURCE_MANAGER_PROPERTY = "resourceManager";

	private ResourceManager resourceManager;

	/**
	 * @return the resourceManager
	 */
	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	/**
	 * @param resourceManager
	 *            the resourceManager to set
	 */
	public void setResourceManager(ResourceManager resourceManager) {
		ResourceManager oldValue = this.resourceManager;
		this.resourceManager = resourceManager;
		firePropertyChange(RESOURCE_MANAGER_PROPERTY, oldValue, resourceManager);
	}

	public static final String STORAGE_MANAGER_PROPERTY = "storageManager";

	private StorageManager storageManager;

	/**
	 * @return the storageManager
	 */
	public StorageManager getStorageManager() {
		return storageManager;
	}

	/**
	 * @param storageManager
	 *            the storageManager to set
	 */
	public void setStorageManager(StorageManager storageManager) {
		StorageManager oldValue = this.storageManager;
		this.storageManager = storageManager;
		firePropertyChange(STORAGE_MANAGER_PROPERTY, oldValue, storageManager);
	}

	public static final String TASK_SERVICE_PROPERTY = "taskService";

	private TaskService taskService;

	/**
	 * @return the taskService
	 */
	public TaskService getTaskService() {
		return taskService;
	}

	/**
	 * @param taskService
	 *            the taskService to set
	 */
	public void setTaskService(TaskService taskService) {
		TaskService oldValue = this.taskService;
		this.taskService = taskService;
		firePropertyChange(TASK_SERVICE_PROPERTY, oldValue, taskService);
	}

}
