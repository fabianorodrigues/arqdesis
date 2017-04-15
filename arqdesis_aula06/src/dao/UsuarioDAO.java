package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;
import model.Usuario.TipoDeUsuario;;

public class UsuarioDAO 
{
	public int incluir(Usuario usuario)
	{
		String sql = "INSERT INTO usuario(nome, cpf, rg, nascimento, login, senha, tipo)"
				+ "VALUES (?,?,?,?,?,?,?)";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);)
		{			
			modificaDados(stm, usuario);
			stm.execute();
			
			sql = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sql);
					ResultSet rs = stm2.executeQuery();)
			{
				if(rs.next())
					usuario.setId(rs.getInt(1));
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return usuario.getId();
	}
	
	public void alterar(Usuario usuario) 
	{
		String sql = "UPDATE usuario SET nome = ?, cpf = ?, rg = ?, nascimento = ?, login = ?,"
				+ "senha = ?, tipo = ? WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			modificaDados(stm, usuario);
			stm.setInt(8, usuario.getId());		
			stm.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) 
	{
		String sql = "DELETE FROM usuario WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Usuario consultar(int id) 
	{
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		String sql = "SELECT * FROM usuario WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setInt(1, usuario.getId());
			try (ResultSet rs = stm.executeQuery();) 
			{
				retornaDados(rs, usuario);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usuario;
	}
	
	public Usuario consultar(String login, String senha) 
	{
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ? ";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setString(1, usuario.getLogin());
			stm.setString(2, usuario.getSenha());
			try (ResultSet rs = stm.executeQuery();) 
			{
				retornaDados(rs, usuario);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usuario;
	}
	
	public ArrayList<Usuario> listar()
	{
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuario";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);
				ResultSet rs = stm.executeQuery())
		{
			while (rs.next()) 
			{
				Usuario usuario = new Usuario();
				retornaDados(rs, usuario);
				lista.add(usuario);
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public boolean cpfExistente(String cpf)
	{
		String sql = "SELECT COUNT(cpf) AS 'total' FROM usuario WHERE cpf = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);)		
		{
			stm.setString(1, cpf);
			try (ResultSet rs = stm.executeQuery())
			{
				if(rs.next())
					if(rs.getInt("total") > 0) return true;
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	private void modificaDados(PreparedStatement stm, Usuario usuario) throws SQLException	
	{
		stm.setString(1, usuario.getNome());
		stm.setString(2, usuario.getCpf());
		stm.setString(3, usuario.getRg());
		stm.setString(4, usuario.getDataNascimento());
		stm.setString(5, usuario.getLogin());
		stm.setString(6, usuario.getSenha());
		stm.setInt(7, usuario.getTipoDeUsuario().getValor());
	}
	
	private void retornaDados(ResultSet rs, Usuario usuario) throws SQLException
	{
		if (rs.next()) 
		{
			usuario.setId(rs.getInt("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setCpf(rs.getString("cpf"));
			usuario.setRg(rs.getString("rg"));
			usuario.setDataNascimento(rs.getString("nascimento"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));					
			if(rs.getInt("tipo") == 1)
				usuario.setTipoDeUsuario(TipoDeUsuario.Atendente);
			else
				usuario.setTipoDeUsuario(TipoDeUsuario.Gerente);
		} 
		else 
			usuario.setId(0);
	}
	
	
	
	
	
	
	
	
	
	
}
