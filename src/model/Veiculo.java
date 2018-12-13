package model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Veiculo extends DefaultEntity<Veiculo> {

	private static final long serialVersionUID = -1955499235814570506L;	
	
	@OneToOne
	@JoinColumn(name="idmodelo")
	private Modelo modelo;
	
	@OneToOne
	@JoinColumn(name="idmarca")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	private Tamanho tamanho;
	private String placa;
	private String cor;

	public Veiculo() {

	}

	public Veiculo(Modelo modelo, Marca marca,Cliente cliente, Tamanho tamanho, String placa, String cor) {
		super();
		this.modelo = modelo;
		this.marca = marca;
		this.cliente = cliente;
		this.tamanho = tamanho;
		this.placa = placa;
		this.cor = cor;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return getPlaca();
	}
	
}
