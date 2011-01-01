/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import net.sf.cafemocha.persistence.SimpleBean;
import net.sf.cafemocha.persistence.StorageManagerTest;
import net.sf.cafemocha.persistence.VolatileStorageProvider;

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

	@Test(expected = XMLEncodingException.class)
	public void invalidObject() throws IOException {
		final NonBean nonBean = new NonBean("nonBean", 100);

		getStorageManager().writeObject(nonBean, "nonBean");
	}

	@Test(expected = XMLDecodingException.class)
	public void corruptDataNonXML() throws IOException {
		// Write non-xml data
		OutputStream output = getStorageManager().getStorageProvider()
				.openOutput("corrupt");
		PrintWriter writer = new PrintWriter(output);

		writer.println("This is an invalid XML resource.");
		writer.flush();
		writer.close();

		// This should throw the exception
		getStorageManager().readObject("corrupt");
	}

	@Test
	public void rawValidXML() throws IOException {
		// Write non-XML data
		OutputStream output = getStorageManager().getStorageProvider()
				.openOutput("beanOne");
		PrintWriter writer = new PrintWriter(output);

		writer.println("<java class=\"java.beans.XMLDecoder\">");
		writer.println("<object class=\"net.sf.cafemocha.persistence.SimpleBean\">");
		writer.println("<void property=\"name\">");
		writer.println("<string>beanOne</string>");
		writer.println("</void>");
		writer.println("</object>");
		writer.println("</java>");
		writer.flush();
		writer.close();

		SimpleBean beanOne = new SimpleBean("beanOne", 0);

		// This should read successfully
		SimpleBean readBeanOne = getStorageManager().readObject("beanOne");

		// Ensure that the object is equivalent, but different
		assertEquals(beanOne, readBeanOne);
		assertNotSame(beanOne, readBeanOne);
	}

	@Test(expected = XMLDecodingException.class)
	public void rawEmptyXML() throws IOException {
		// Write non-XML data
		OutputStream output = getStorageManager().getStorageProvider()
				.openOutput("corrupt");
		PrintWriter writer = new PrintWriter(output);

		writer.println("<java class=\"java.beans.XMLDecoder\">");
		writer.println("</java>");
		writer.flush();
		writer.close();

		// This should throw exception
		getStorageManager().readObject("corrupt");
	}

	@Test(expected = XMLDecodingException.class)
	public void rawCorruptXML() throws IOException {
		// Write non-XML data
		OutputStream output = getStorageManager().getStorageProvider()
				.openOutput("corrupt");
		PrintWriter writer = new PrintWriter(output);

		writer.println("<java class=\"java.beans.XMLDecoder\">");
		writer.println("<object class=\"net.sf.cafemocha.persistence.SimpleBean\">");
		writer.println("<void property=\"name\">");
		writer.println("<string>beanOne</string>");
		writer.println("</voidd>"); // misspelled
		writer.println("</object>");
		writer.println("</java>");
		writer.flush();
		writer.close();

		// This should throw exception
		getStorageManager().readObject("corrupt");
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
