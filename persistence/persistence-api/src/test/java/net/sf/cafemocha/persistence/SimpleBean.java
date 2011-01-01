package net.sf.cafemocha.persistence;

/**
 * Simple bean class for testing object persistence.
 * 
 * @author computerguy5
 * 
 */
public class SimpleBean {

	public SimpleBean() {
	}

	public SimpleBean(String name, int value) {
		this.name = name;
		this.value = value;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

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
		} else if (!(obj instanceof SimpleBean)) {
			return false;
		}

		SimpleBean rhs = (SimpleBean) obj;

		return value == rhs.value && name == null ? rhs.name == null : name
				.equals(rhs.name);
	}

	@Override
	public String toString() {
		return String.format("%s: %d", name, value);
	}
}