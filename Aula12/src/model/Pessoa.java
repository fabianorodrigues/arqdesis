package model;

public abstract class Pessoa 
{
	private String nome, cpf, rg, login, senha;
	private String dataNascimento;
	
	public Pessoa()
	{
		dataNascimento = "00/00/0000";
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {

		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {

		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {

		this.rg = rg;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
		 
}
