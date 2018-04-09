package model;

public class Tupel {
	private int key;
	private int value;

	public Tupel(int key, int value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + key;
		result = prime * result + value;
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
		Tupel other = (Tupel) obj;
		if (key != other.key)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
