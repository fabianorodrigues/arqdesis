package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Conjunto;

public class ConjuntoDAO 
{
	public int incluir(Conjunto conjunto)
	{
		String sql = "INSERT INTO conjunto(temperatura, descricao, andar)"
				+ "VALUES (?,?,?)";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);)
		{			
			modificaDados(stm, conjunto);
			stm.execute();
			
			sql = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sql);
					ResultSet rs = stm2.executeQuery();)
			{
				if(rs.next())
					conjunto.setId(rs.getInt(1));
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return conjunto.getId();
	}
	
	public void alterar(Conjunto conjunto) 
	{
		String sql = "UPDATE conjunto SET temperatura = ?, descricao = ?, andar = ? WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			modificaDados(stm, conjunto);
			stm.setInt(4, conjunto.getId());		
			stm.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) 
	{
		String sql = "DELETE FROM conjunto WHERE id = ?";
		
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
	
	public Conjunto consultar(int id) 
	{
		Conjunto conjunto = new Conjunto();
		conjunto.setId(id);
		
		String sql = "SELECT * FROM conjunto WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setInt(1, conjunto.getId());
			try (ResultSet rs = stm.executeQuery();) 
			{
				retornaDados(rs, conjunto);
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
		return conjunto;
	}
	
	public ArrayList<Conjunto> listar()
	{
		ArrayList<Conjunto> lista = new ArrayList<Conjunto>();
		String sql = "SELECT * FROM conjunto";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);
				ResultSet rs = stm.executeQuery())
		{
			while (rs.next()) 
			{
				Conjunto conjunto = new Conjunto();
				retornaDados(rs, conjunto);
				lista.add(conjunto);

			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lista;
	}
	
	private void modificaDados(PreparedStatement stm, Conjunto conjunto) throws SQLException
	{
		stm.setInt(1, conjunto.getTemperatura().getId());
		stm.setString(2, conjunto.getDescricao());
		stm.setInt(3, conjunto.getAndar());
	}
	
	private void retornaDados(ResultSet rs, Conjunto conjunto) throws SQLException
	{
		if (rs.next()) 
		{
			conjunto.setId(rs.getInt("id"));
			conjunto.getTemperatura().setId(rs.getInt("temperatura"));
			conjunto.setDescricao(rs.getString("descricao"));
			conjunto.setAndar(rs.getInt("andar"));				
		} 
		else 
			conjunto.setId(0);
	}
}
