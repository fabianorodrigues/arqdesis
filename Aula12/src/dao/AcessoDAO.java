package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Acesso;

public class AcessoDAO 
{
	public int incluir(Acesso acesso)
	{
		String sql = "INSERT INTO acesso(usuario, funcionario, entrada, horario_entrada, horario_saida)"
				+ "VALUES (?,?,?,?,?)";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);)
		{
			modificaDados(stm, acesso);		
			stm.execute();
			
			sql = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sql);
					ResultSet rs = stm2.executeQuery();)
			{
				if(rs.next())
					acesso.setId(rs.getInt(1));
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return acesso.getId();
	}
	
	public void alterar(Acesso acesso) 
	{
		String sql = "UPDATE acesso SET usuario = ?, funcionario = ?, entrada = ?, horario_entrada = ?, "
				+ "horario_saida = ? WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			modificaDados(stm, acesso);
			stm.setInt(6, acesso.getId());			
			stm.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) 
	{
		String sql = "DELETE FROM acesso WHERE id = ?";
		
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
	
	public Acesso consultar(int id) 
	{
		Acesso acesso = new Acesso();
		acesso.setId(id);
		
		String sql = "SELECT * FROM acesso WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setInt(1, acesso.getId());
			try (ResultSet rs = stm.executeQuery();) 
			{
				retornaDados(rs, acesso);
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
		return acesso;
	}
	
	public ArrayList<Acesso> listar()
	{
		ArrayList<Acesso> lista = new ArrayList<Acesso>();
		String sql = "SELECT * FROM acesso";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);
				ResultSet rs = stm.executeQuery())
		{
			while (rs.next()) 
			{
				Acesso acesso = new Acesso();
				retornaDados(rs, acesso);
				lista.add(acesso);

			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lista;
	}
	
	private void modificaDados(PreparedStatement stm, Acesso acesso) throws SQLException
	{
		stm.setInt(1, acesso.getUsuario().getId());
		stm.setInt(2, acesso.getFuncionario().getId());
		stm.setString(3, acesso.getDataEntrada());
		stm.setString(4, acesso.getHorarioEntrada());
		stm.setString(5, acesso.getHorarioSaida());
	}
	
	private void retornaDados(ResultSet rs, Acesso acesso) throws SQLException
	{
		if (rs.next()) 
		{
			acesso.setId(rs.getInt("id"));
			acesso.getUsuario().setId(rs.getInt("usuario"));
			acesso.getFuncionario().setId(rs.getInt("funcionario"));
			acesso.setDataEntrada(rs.getString("entrada"));
			acesso.setHorarioEntrada(rs.getString("horario_entrada"));
			acesso.setHorarioSaida(rs.getString("horario_saida"));
		} 
		else 
			acesso.setId(0);
	}
	
}
