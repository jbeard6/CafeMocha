/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests the functionality of the {@link XMLStorageManager}.
 * 
 * @author computerguy5
 * 
 */
public class XMLStorageManagerTest extends
		StorageManagerTest<XMLStorageManager> {

	@Override
	protected XMLStorageManager createStorageManager() {
		VolatileStorageProvider storageProvider = new VolatileStorageProvider();

		return new XMLStorageManager(storageProvider);
	}

	@Test(expected = IOException.class)
	public void invalidObject() throws IOException {
		final NonBean nonBean = new NonBean("nonBean", 100);

		getStorageManager().writeObject(nonBean, "nonBean");
	}

	/**
	 * A sample class that can not be persisted with an {@link XMLEncoder} or
	 * restored by an {@link XMLDecoder} because it does not have a zero
	 * argument constructor.
	 * 
	 * @author computerguy5
	 * 
	 */
	private static class NonBean {

		public NonBean(String name, int value) {
			this.name = name;
			this.value = value;
		}

		private final String name;

		private final int value;

		@Override
		public int hashCode() {
			return value * (name == null ? 1 : name.hashCode());
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			} else if (obj == null) {
				return false;
			} else if (!(obj instanceof NonBean)) {
				return false;
			}

			NonBean rhs = (NonBean) obj;

			return value == rhs.value && name == null ? rhs.name == null : name
					.equals(rhs.name);
		}

		@Override
		public String toString() {
			return String.format("%s: %d", name, value);
		}
	}

}
