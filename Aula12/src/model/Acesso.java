package model;

import java.io.Serializable;
import java.util.Date;

public class Acesso implements Serializable
{
	private int id;
	private Usuario usuario;
	private Funcionario funcionario;
	private String dataEntrada, horarioEntrada, horarioSaida;
	
	public Acesso()
	{
		usuario = new Usuario();
		funcionario = new Funcionario();
		dataEntrada = "00/00/0000";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int codigo) {
		this.id = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getHorarioEntrada() {
		return horarioEntrada;
	}
	public void setHorarioEntrada(String horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}
	public String getHorarioSaida() {
		return horarioSaida;
	}
	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
	}
	
	
}
