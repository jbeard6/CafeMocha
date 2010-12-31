/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import org.junit.After;

/**
 * Tests for correct functionality of the {@link VolatileStorageProvider}.
 * 
 * @author computerguy5
 * 
 */
public class VolatileStorageProviderTest extends
		StorageProviderTest<VolatileStorageProvider> {

	@Override
	protected VolatileStorageProvider createStorageProvider() {
		return new VolatileStorageProvider();
	}

	@After
	public void tearDown() {
		getStorageProvider().deleteAll();
		super.tearDown();
	}

}
