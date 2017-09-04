package service;

import java.util.ArrayList;

import dao.EmpresaDAO;
import model.Empresa;

public class EmpresaService 
{
	private EmpresaDAO dao;
	
	public EmpresaService()
	{
		dao = new EmpresaDAO();
	}
	
	public void incluir(Empresa objeto) 
	{
		dao.incluir(objeto);
	}

	public void alterar(Empresa objeto) 
	{
		dao.alterar(objeto);
	}

	public void excluir(Empresa objeto) 
	{
		dao.excluir(objeto.getId());
	}

	public Empresa consultar(int id) 
	{
		return dao.consultar(id);
	}
	
	public ArrayList<Empresa> listar()
	{
		return dao.listar();
	}
	
	public boolean cnpjExistente(String cnpj)
	{
		return dao.cnpjExistente(cnpj);
	}
}
