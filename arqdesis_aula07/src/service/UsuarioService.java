package service;

import java.util.ArrayList;

import dao.UsuarioDAO;
import model.Usuario;;

public class UsuarioService 
{
private UsuarioDAO dao;
	
	public UsuarioService()
	{
		dao = new UsuarioDAO();
	}
	
	public void incluir(Usuario objeto)
	{
		objeto.setCpf(objeto.getCpf().replaceAll("[.-]", ""));
		objeto.setRg(objeto.getRg().replaceAll("[.-]", ""));
		objeto = criptografarLoginSenha(objeto);
		dao.incluir(objeto);
	}

	public void alterar(Usuario objeto) 
	{
		objeto = criptografarLoginSenha(objeto);
		dao.alterar(objeto);
	}

	public void excluir(Usuario objeto) 
	{
		dao.excluir(objeto.getId());
	}

	public Usuario consultar(int id) 
	{
		return descriptografarLoginSenha(dao.consultar(id));
	}
	
	public Usuario consultar(String login, String senha) 
	{
		return descriptografarLoginSenha(dao.consultar(login, senha));
	}
	
	public ArrayList<Usuario> listar()
	{
		return dao.listar();
	}
	
	public boolean cpfExistente(String cpf)
	{
		return dao.cpfExistente(cpf);
	}
	
	private Usuario criptografarLoginSenha(Usuario objeto)
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
			return new Usuario();
		}		
	}
	
	private Usuario descriptografarLoginSenha(Usuario objeto)
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
			return new Usuario();
		}
	}
}
