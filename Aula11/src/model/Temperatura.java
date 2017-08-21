package model;

import java.io.Serializable;

public class Temperatura implements Serializable
{
	public enum StatusDoArCondicionado
	{
		Desligado(0), Ligado(1);
		
		private final int valor;
		
		StatusDoArCondicionado(int valor){
			this.valor = valor;
		}
		
		public int getValor(){
			return valor;
		}
	}

	private int id;	
	private int temperaturaAtual;
	private StatusDoArCondicionado statusDoArCondicionado;
	
	public int getId() {
		return id;
	}
	public void setId(int codigo) {
		this.id = codigo;
	}
	public int getTemperaturaAtual() {
		return temperaturaAtual;
	}
	public void setTemperaturaAtual(int temperaturaAtual) {
		this.temperaturaAtual = temperaturaAtual;
	}
	public StatusDoArCondicionado getStatusDoArCondicionado() {
		return statusDoArCondicionado;
	}
	public void setStatusDoArCondicionado(StatusDoArCondicionado statusDoArCondicionado) {
		this.statusDoArCondicionado = statusDoArCondicionado;
	}
	
	
}
