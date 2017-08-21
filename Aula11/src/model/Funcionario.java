package model;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable
{
	public enum StatusAlterarTemperatura
	{
		Negado(0), Permitido(1);
		
		private final int valor;
		
		StatusAlterarTemperatura(int v) {
			valor = v;
		}
		
		public int getValor(){
			return valor;
		}
	}
	
	private int id;
	private Empresa empresa;
	private String horarioEntradaDe,horarioEntradaAte; 
	private StatusAlterarTemperatura statusAlterarTemperatura;
	
	public Funcionario()
	{
		empresa = new Empresa();
	}
	
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
