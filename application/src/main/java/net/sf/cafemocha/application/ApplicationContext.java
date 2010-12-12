/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.application;

/**
 * Maintains the execution state of an {@link Application}, including access to
 * resources and data storage.
 * 
 * @author computerguy5
 * 
 */
public class ApplicationContext<T extends Application> {

	public ApplicationContext(T application) {
		if (application == null) {
			throw new NullPointerException("application");
		}
		this.application = application;
	}

	private final T application;

	/**
	 * @return the application
	 */
	public T getApplication() {
		return application;
	}

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
		this.resourceManager = resourceManager;
	}

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
		this.storageManager = storageManager;
	}

}
