package com.capgemini.model;

public class Person {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pAge;
		result = prime * result + pId;
		result = prime * result + ((pName == null) ? 0 : pName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (pAge != other.pAge)
			return false;
		if (pId != other.pId)
			return false;
		if (pName == null) {
			if (other.pName != null)
				return false;
		} else if (!pName.equals(other.pName))
			return false;
		return true;
	}

	private int pId;
	private String pName;
	private int pAge;

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpAge() {
		return pAge;
	}

	public void setpAge(int pAge) {
		this.pAge = pAge;
	}

	@Override
	public String toString() {
		return "Person [pId=" + pId + ", pName=" + pName + ", pAge=" + pAge + "]";
	}

	public Person(int pId, String pName, int pAge) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pAge = pAge;
	}

	public Person() {
		super();

	}

}
