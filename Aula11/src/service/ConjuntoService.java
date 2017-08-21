package service;

import java.util.ArrayList;

import dao.ConjuntoDAO;
import model.Conjunto;

public class ConjuntoService 
{
	private ConjuntoDAO dao;
	
	public ConjuntoService()
	{
		dao = new ConjuntoDAO();
	}
	
	public void incluir(Conjunto objeto) 
	{
		dao.incluir(objeto);
	}

	public void alterar(Conjunto objeto) 
	{
		dao.alterar(objeto);
	}

	public void excluir(Conjunto objeto) 
	{
		dao.excluir(objeto.getId());
	}

	public Conjunto consultar(int id) 
	{
		return dao.consultar(id);
	}
	
	public ArrayList<Conjunto> listar()
	{
		return dao.listar();
	}
}
