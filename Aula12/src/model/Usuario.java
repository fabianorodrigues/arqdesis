package model;

import java.io.Serializable;

public class Usuario extends Pessoa implements Serializable
{
	public enum TipoDeUsuario
	{
		Atendente(1), Gerente(2);
		
		private final int valor;
		
		TipoDeUsuario(int v){
			valor = v;
		}
		
		public int getValor()
		{
			return valor;
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
