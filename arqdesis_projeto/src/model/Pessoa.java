package model;

import java.util.Date;

public abstract class Pessoa 
{
	private String nome, cpf, rg, login, senha;
	private String dataNascimento;
	
	protected String getNome() {
		return nome;
	}
	protected void setNome(String nome) {

		this.nome = nome;
	}
	
	protected String getCpf() {
		return cpf;
	}
	protected void setCpf(String cpf) {

		this.cpf = cpf;
	}
	
	protected String getRg() {
		return rg;
	}
	protected void setRg(String rg) {

		this.rg = rg;
	}
	
	protected String getLogin() {
		return login;
	}
	protected void setLogin(String login) {
		this.login = login;
	}
	
	protected String getSenha() {
		return senha;
	}
	protected void setSenha(String senha) {
		this.senha = senha;
	}
	
	protected String getDataNascimento() {
		return dataNascimento;
	}
	protected void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
		 
}
