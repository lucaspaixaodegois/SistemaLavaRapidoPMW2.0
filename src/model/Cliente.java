package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

@Entity
public class Cliente extends DefaultEntity<Cliente> {

	private static final long serialVersionUID = 6762636530489302919L;

	private String cpfCnpj;
	private String nome;
	private String endereco;
	private String email;
	private Modelo modelo;
	private Tamanho tamanho;
	private String placa;
	private String cor;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private List<Telefone> listaTelefone;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Veiculo> listaVeiculo;

	@Column(columnDefinition = "Date")
	private LocalDate dataAniversario;

	public Cliente(String cpfCnpj, String nome, String endereco, String email, Modelo modelo, Tamanho tamanho,
			String placa, String cor, List<Telefone> listaTelefone, List<Veiculo> listaVeiculo,
			LocalDate dataAniversario) {
		super();
		this.cpfCnpj = cpfCnpj;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.modelo = modelo;
		this.tamanho = tamanho;
		this.placa = placa;
		this.cor = cor;
		this.listaTelefone = listaTelefone;
		this.listaVeiculo = listaVeiculo;
		this.dataAniversario = dataAniversario;
	}

	public Cliente() {

	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		try{
			setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
			this.cpfCnpj = cpfCnpj;
		}catch(ParseException e)
		{
			e.printStackTrace();
		}
		
	}

	private void setFormatterFactory(DefaultFormatterFactory defaultFormatterFactory) {
		// TODO Auto-generated method stub
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
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

	public List<Veiculo> getListaVeiculo() {
		return listaVeiculo;
	}

	public void setListaVeiculo(List<Veiculo> listaVeiculo) {
		this.listaVeiculo = listaVeiculo;
	}

	public LocalDate getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(LocalDate dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public String toString() {
		
		return getNome();
	}
}
