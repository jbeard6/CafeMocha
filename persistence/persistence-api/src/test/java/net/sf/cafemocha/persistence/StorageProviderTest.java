/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests common functionality of a {@link StorageProvider}.
 * 
 * TODO Test concurrent access
 * 
 * TODO Test multiple resources
 * 
 * @author computerguy5
 * 
 */
public abstract class StorageProviderTest<T extends StorageProvider> {

	private T storageProvider;

	protected abstract T createStorageProvider();

	protected final T getStorageProvider() {
		return storageProvider;
	}

	@Before
	public void setUp() {
		storageProvider = createStorageProvider();
	}

	@After
	public void tearDown() {
		storageProvider = null;
	}

	@Test
	public void testEntry() throws IOException {
		final String resourceName = "resourceOne";
		final String lineOne = "This is the first line.";
		final String lineTwo = "This is the second line.";

		// Write some data to an entry
		OutputStream output = storageProvider.openOutput(resourceName);
		PrintWriter p = new PrintWriter(output);
		p.println(lineOne);
		p.println(lineTwo);
		p.flush();
		p.close();

		assertTrue("resourceOne does not exist.",
				storageProvider.exists(resourceName));

		// Read the data back
		InputStream input = storageProvider.openInput(resourceName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		assertEquals(lineOne, reader.readLine());
		assertEquals(lineTwo, reader.readLine());
		assertNull("End of Stream Expected", reader.readLine());

		reader.close();

		// Remove the resource
		storageProvider.delete(resourceName);
		assertFalse("The resource was not successfully deleted.",
				storageProvider.exists(resourceName));
	}

}
