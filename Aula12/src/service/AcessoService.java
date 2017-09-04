package service;

import java.util.ArrayList;

import dao.AcessoDAO;
import model.Acesso;

public class AcessoService 
{
	private AcessoDAO dao;
	
	public AcessoService()
	{
		dao = new AcessoDAO();
	}
	
	public void incluir(Acesso objeto) 
	{
		dao.incluir(objeto);
	}

	public void alterar(Acesso objeto) 
	{
		dao.alterar(objeto);
	}

	public void excluir(Acesso objeto) 
	{
		dao.excluir(objeto.getId());
	}

	public Acesso consultar(int id) 
	{
		return dao.consultar(id);
	}
	
	public ArrayList<Acesso> listar()
	{
		return dao.listar();
	}
}
