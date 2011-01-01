/**
 * $LastChangedRevision$
 * $HeadURL$
 * $LastChangedDate$
 * $LastChangedBy$
 */
package net.sf.cafemocha.persistence;

/**
 * A bean consisting of {@link SimpleBean} properties.
 * 
 * @author computerguy5
 * 
 */
public class ComplexBean {

	public ComplexBean() {
	}

	public ComplexBean(SimpleBean beanOne, SimpleBean beanTwo) {
		this.beanOne = beanOne;
		this.beanTwo = beanTwo;
	}

	private SimpleBean beanOne;

	public SimpleBean getBeanOne() {
		return beanOne;
	}

	public void setBeanOne(SimpleBean beanOne) {
		this.beanOne = beanOne;
	}

	private SimpleBean beanTwo;

	public SimpleBean getBeanTwo() {
		return beanTwo;
	}

	public void setBeanTwo(SimpleBean beanTwo) {
		this.beanTwo = beanTwo;
	}

	@Override
	public int hashCode() {
		int hashCode = 51 * (beanOne == null ? 1 : beanOne.hashCode());

		hashCode *= beanTwo == null ? 1 : beanTwo.hashCode();

		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!(obj instanceof ComplexBean)) {
			return false;
		}

		ComplexBean rhs = (ComplexBean) obj;

		return (beanOne == null ? rhs.beanOne == null : beanOne
				.equals(rhs.beanOne))
				&& (beanTwo == null ? rhs.beanTwo == null : beanTwo
						.equals(rhs.beanTwo));
	}

	@Override
	public String toString() {
		return String.format("(%s, %s)", beanOne, beanTwo);
	}
}
