package model;

import java.io.Serializable;

import javax.persistence.Entity;


@Entity
public class Usuario extends DefaultEntity<Usuario> implements Serializable{
	

	private static final long serialVersionUID = 2832889643952969394L;
	private String nome;
	private String cpf;
	private String senha;
	private Perfil perfil;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setLogin(String text) {
		// TODO Auto-generated method stub
		
	}
	public void setPassword(String text) {
		// TODO Auto-generated method stub
		
	}
	
}
