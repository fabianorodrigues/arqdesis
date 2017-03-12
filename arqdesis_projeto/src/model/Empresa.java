package model;

import java.io.Serializable;

public class Empresa implements Serializable
{
	private int id;
	private Conjunto conjunto;
	private String cnpj, razaoSocial, horarioInicio, horarioEncerramento;
	private int temperaturaMaxima;
	
	
	public int getId() {
		return id;
	}
	public void setId(int codigo) {
		this.id = codigo;
	}
	public Conjunto getConjunto() {
		return conjunto;
	}
	public void setConjunto(Conjunto conjunto) {
		this.conjunto = conjunto;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioEncerramento() {
		return horarioEncerramento;
	}
	public void setHorarioEncerramento(String horarioEncerramento) {
		this.horarioEncerramento = horarioEncerramento;
	}
	public int getTemperaturaMaxima() {
		return temperaturaMaxima;
	}
	public void setTemperaturaMaxima(int temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
	
}
