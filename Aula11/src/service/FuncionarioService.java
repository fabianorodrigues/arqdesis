package service;

import java.util.ArrayList;

import dao.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioService 
{
	private FuncionarioDAO dao;
	
	public FuncionarioService()
	{
		dao = new FuncionarioDAO();
	}
	
	public void incluir(Funcionario objeto)
	{
		objeto = criptografarLoginSenha(objeto);
		dao.incluir(objeto);
	}

	public void alterar(Funcionario objeto) 
	{
		objeto = criptografarLoginSenha(objeto);
		dao.alterar(objeto);
	}

	public void excluir(Funcionario objeto) 
	{
		dao.excluir(objeto.getId());
	}

	public Funcionario consultar(int id) 
	{
		return descriptografarLoginSenha(dao.consultar(id));
	}
	
	public Funcionario consultar(String login, String senha) 
	{
		return descriptografarLoginSenha(dao.consultar(login, senha));
	}
	
	public ArrayList<Funcionario> listar()
	{
		return dao.listar();
	}
	
	public boolean cpfExistente(String cpf)
	{
		return dao.cpfExistente(cpf);
	}
	
	private Funcionario criptografarLoginSenha(Funcionario objeto)
	{
		try
		{
			objeto.setLogin(Criptografia.criptografar(objeto.getLogin()));
			objeto.setSenha(Criptografia.criptografar(objeto.getSenha()));
			
			return objeto;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new Funcionario();
		}		
	}
	
	private Funcionario descriptografarLoginSenha(Funcionario objeto)
	{
		try 
		{
			objeto.setLogin(Criptografia.descriptografar(objeto.getLogin()));
			objeto.setSenha(Criptografia.descriptografar(objeto.getSenha()));
			
			return objeto;
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return new Funcionario();
		}
	}
	
	
}
