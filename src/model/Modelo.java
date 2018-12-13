package model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Modelo extends DefaultEntity<Modelo> {

	private static final long serialVersionUID = 3674833586110088765L;

	@ManyToOne
	@JoinColumn(name = "idmarcas")
	private Marca marca;

	private String modelo;

	public Modelo() {
	}

	public Modelo(Marca marca, String modelo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return getModelo();
	}
	
	

}
