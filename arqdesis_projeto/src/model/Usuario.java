package model;

import java.io.Serializable;

public class Usuario extends Pessoa implements Serializable
{
	public enum TipoDeUsuario
	{
		Atendente(1), Gerente(2);
		
		public int valor;
		
		TipoDeUsuario(int valor)
		{
			this.valor = valor;
		}
	}
	
	private int id;
	private TipoDeUsuario tipoDeUsuario;
	
	public int getId() {
		return id;
	}
	public void setId(int codigo) {
		this.id = codigo;
	}
	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}
	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}
	
	
	
	
}
