package model;

import java.io.Serializable;

public class Conjunto implements Serializable
{
	private int id;
	private Temperatura temperatura;
	private String descricao;
	private int andar;
	
	public int getId() {
		return id;
	}
	public void setId(int codigo) {
		this.id = codigo;
	}
	public Temperatura getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Temperatura temperatura) {
		this.temperatura = temperatura;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getAndar() {
		return andar;
	}
	public void setAndar(int andar) {
		this.andar = andar;
	}
	
	
}
