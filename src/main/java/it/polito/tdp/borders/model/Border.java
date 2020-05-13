package it.polito.tdp.borders.model;

public class Border {

	private Country c1;
	private Country c2;
	private int anno;

	public Border(Country c1, Country c2, int anno) {
		this.c1 = c1;
		this.c2 = c2;
		this.anno = anno;
	}

	public Country getC1() {
		return c1;
	}

	public Country getC2() {
		return c2;
	}

	public int getAnno() {
		return anno;
	}

	public void setC1(Country c1) {
		this.c1 = c1;
	}

	public void setC2(Country c2) {
		this.c2 = c2;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c1 == null) ? 0 : c1.hashCode());
		result = prime * result + ((c2 == null) ? 0 : c2.hashCode());
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
		Border other = (Border) obj;
		if (c1 == null) {
			if (other.c1 != null)
				return false;
		} else if (!c1.equals(other.c1))
			return false;
		if (c2 == null) {
			if (other.c2 != null)
				return false;
		} else if (!c2.equals(other.c2))
			return false;
		return true;
	}
	
	public String toString() {
		return c1.getNome() + " --> " + c2.getNome() + "\n";
	}

}
