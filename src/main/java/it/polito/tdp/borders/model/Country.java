package it.polito.tdp.borders.model;

public class Country {

	private int id;
	private String abbr;
	private String nome;
	private int numConfini;
	
	
	public Country(int id, String abbr, String nome) {
		this.id = id;
		this.abbr = abbr;
		this.nome = nome;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAbbr() {
		return abbr;
	}


	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbr == null) ? 0 : abbr.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Country other = (Country) obj;
		if (abbr == null) {
			if (other.abbr != null)
				return false;
		} else if (!abbr.equals(other.abbr))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String toString() {
		return  nome;
	}


	public void setNumConfini(int count) {
		
		this.numConfini = count;
		
	}
	public int numConfini() {
		
		return numConfini;
	}
	
}
