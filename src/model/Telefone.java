package model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Telefone extends DefaultEntity<Telefone> {
	
	private static final long serialVersionUID = -8839605705217664226L;
	
	private String codigoArea;
	private String numeroCliente;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((codigoArea == null) ? 0 : codigoArea.hashCode());
		result = prime * result + ((numeroCliente == null) ? 0 : numeroCliente.hashCode());
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
		Telefone other = (Telefone) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codigoArea == null) {
			if (other.codigoArea != null)
				return false;
		} else if (!codigoArea.equals(other.codigoArea))
			return false;
		if (numeroCliente == null) {
			if (other.numeroCliente != null)
				return false;
		} else if (!numeroCliente.equals(other.numeroCliente))
			return false;
		return true;
	}
	
	
	
}
