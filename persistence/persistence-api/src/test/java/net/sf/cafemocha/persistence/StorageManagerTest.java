/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import static junit.framework.Assert.assertNotSame;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public void basicObjectStorage() throws IOException {
		final SimpleBean beanOne = new SimpleBean("beanOne", 1);
		final SimpleBean beanTwo = new SimpleBean("beanTwo", 2);
		final ComplexBean complex = new ComplexBean(beanOne, beanTwo);
		final List<SimpleBean> list = new ArrayList<SimpleBean>();
		list.add(beanOne);
		list.add(beanTwo);

		// Test storage and retrieval of objects using the StorageManager
		storageManager.writeObject(beanOne, "beanOne");
		storageManager.writeObject(beanTwo, "beanTwo");
		storageManager.writeObject(complex, "complex");
		storageManager.writeObject(list, "list");

		/*
		 * Assert that they are equivalent, but not the same object, because
		 * reading typically occurs in a different JVM instance.
		 */
		SimpleBean readBeanOne = storageManager.readObject("beanOne");
		assertEquals(beanOne, readBeanOne);
		assertNotSame(beanOne, readBeanOne);

		SimpleBean readBeanTwo = storageManager.readObject("beanTwo");
		assertEquals(beanTwo, readBeanTwo);
		assertNotSame(beanTwo, readBeanTwo);

		ComplexBean readComplex = storageManager.readObject("complex");
		assertEquals(complex, readComplex);
		assertNotSame(complex, readComplex);

		List<SimpleBean> readList = storageManager.readObject("list");
		assertEquals(list, readList);
		assertNotSame(list, readList);
	}

}
