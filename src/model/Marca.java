package model;

import javax.persistence.Entity;

@Entity
public class Marca extends DefaultEntity<Marca> {

	private static final long serialVersionUID = -4092251125464086060L;

	private String marca;
	
	public Marca(String marca) {
		super();
		this.marca = marca;
	}
	
	public Marca() {

	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String toString() {
		return this.getMarca();
	}
}