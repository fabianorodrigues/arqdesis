package model;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable
{
	public enum StatusAlterarTemperatura

	{
		Negado(0), Permitido(1);
		
		public int valor;
		
		StatusAlterarTemperatura(int valor) 
		{
			this.valor = valor;
		}
	}
	
	private int id;
	private Empresa empresa;
	private String horarioEntradaDe,horarioEntradaAte; 
	private StatusAlterarTemperatura statusAlterarTemperatura;
	
	public int getId() {
		return id;
	}
	public void setId(int codigo) {
		this.id = codigo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getHorarioEntradaDe() {
		return horarioEntradaDe;
	}
	public void setHorarioEntradaDe(String horarioEntradaDe) {
		this.horarioEntradaDe = horarioEntradaDe;
	}
	public String getHorarioEntradaAte() {
		return horarioEntradaAte;
	}
	public void setHorarioEntradaAte(String horarioEntradaAte) {
		this.horarioEntradaAte = horarioEntradaAte;
	}
	public StatusAlterarTemperatura getStatusAlterarTemperatura() {
		return statusAlterarTemperatura;
	}
	public void setStatusAlterarTemperatura(StatusAlterarTemperatura statusAlterarTemperatura) {
		this.statusAlterarTemperatura = statusAlterarTemperatura;
	}
	
	
}
