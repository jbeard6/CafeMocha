/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application;

import net.sf.cafemocha.beans.AbstractObservable;
import net.sf.cafemocha.persistence.StorageManager;
import net.sf.cafemocha.resources.ResourceManager;
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
	 * Returns the {@link ResourceManager} in use by this
	 * {@link ApplicationContext}.
	 * 
	 * @return the resource manager
	 */
	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	/**
	 * Set the {@link ResourceManager} used by this {@link ApplicationContext}.
	 * 
	 * @param resourceManager
	 *            the resource manager to set
	 * @throws NullPointerException
	 *             if <code>resourceManager</code> is <code>null</code>
	 */
	public void setResourceManager(ResourceManager resourceManager) {
		if (resourceManager == null) {
			throw new NullPointerException("resourceManager");
		}
		ResourceManager oldValue = this.resourceManager;
		this.resourceManager = resourceManager;
		firePropertyChange(RESOURCE_MANAGER_PROPERTY, oldValue, resourceManager);
	}

	public static final String STORAGE_MANAGER_PROPERTY = "storageManager";

	private StorageManager storageManager;

	/**
	 * Returns the {@link StorageManager} used by this
	 * {@link ApplicationContext}.
	 * 
	 * @return the storage manager
	 */
	public StorageManager getStorageManager() {
		return storageManager;
	}

	/**
	 * Sets the {@link StorageManager} used by this {@link ApplicationContext}.
	 * 
	 * @param storageManager
	 *            the storage manager to set
	 * @throws NullPointerException
	 *             if <code>storageManager</code> is <code>null</code>
	 */
	public void setStorageManager(StorageManager storageManager) {
		if (storageManager == null) {
			throw new NullPointerException("storageManager");
		}
		StorageManager oldValue = this.storageManager;
		this.storageManager = storageManager;
		firePropertyChange(STORAGE_MANAGER_PROPERTY, oldValue, storageManager);
	}

	public static final String TASK_SERVICE_PROPERTY = "taskService";

	private TaskService taskService;

	/**
	 * Returns the {@link TaskService} used by this {@link ApplicationContext}.
	 * 
	 * @return the task service
	 */
	public TaskService getTaskService() {
		return taskService;
	}

	/**
	 * Sets the {@link TaskService} used by this {@link ApplicationContext}.
	 * 
	 * @param taskService
	 *            the task service to set
	 * @throws NullPointerException
	 *             if <code>taskService</code> is <code>null</code>
	 */
	public void setTaskService(TaskService taskService) {
		if (taskService == null) {
			throw new NullPointerException("taskService");
		}
		TaskService oldValue = this.taskService;
		this.taskService = taskService;
		firePropertyChange(TASK_SERVICE_PROPERTY, oldValue, taskService);
	}

}
