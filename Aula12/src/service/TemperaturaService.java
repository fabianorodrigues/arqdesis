package service;

import java.util.ArrayList;

import dao.TemperaturaDAO;
import model.Temperatura;

public class TemperaturaService 
{
	private TemperaturaDAO dao;
	
	public TemperaturaService()
	{
		dao = new TemperaturaDAO();
	}
	
	public void incluir(Temperatura objeto) 
	{
		dao.incluir(objeto);
	}

	public void alterar(Temperatura objeto) 
	{
		dao.alterar(objeto);
	}

	public void excluir(Temperatura objeto) 
	{
		dao.excluir(objeto.getId());
	}

	public Temperatura consultar(int id) 
	{
		return dao.consultar(id);
	}
	
	public ArrayList<Temperatura> listar()
	{
		return dao.listar();
	}
}
