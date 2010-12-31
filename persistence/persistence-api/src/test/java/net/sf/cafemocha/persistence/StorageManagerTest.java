/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests common functionality of a {@link StorageManager}.
 * 
 * @author computerguy5
 * 
 */
public abstract class StorageManagerTest<T extends StorageManager> {

	private T storageManager;

	protected abstract T createStorageManager();

	protected final T getStorageManager() {
		return storageManager;
	}

	@Before
	public void setUp() {
		storageManager = createStorageManager();
	}

	@After
	public void tearDown() {
		storageManager = null;
	}

	@Test
	public void testObjectStorage() {
		// TODO Test storage and retrieval of objects using the StorageManager
	}

}
