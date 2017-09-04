package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Temperatura;
import model.Temperatura.StatusDoArCondicionado;;

public class TemperaturaDAO 
{
	public int incluir(Temperatura temperatura)
	{
		String sql = "INSERT INTO temperatura(temperatura_atual, ar_condicionado) VALUES (?,?)";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);)
		{
			modificaDados(stm, temperatura);
			stm.execute();
			
			sql = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sql);
					ResultSet rs = stm2.executeQuery();)
			{
				if(rs.next())
					temperatura.setId(rs.getInt(1));
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return temperatura.getId();
	}
	
	public void alterar(Temperatura temperatura) 
	{
		String sql = "UPDATE temperatura SET temperatura_atual = ?, ar_condicionado = ? WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			modificaDados(stm, temperatura);
			stm.setInt(3, temperatura.getId());		
			stm.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) 
	{
		String sql = "DELETE FROM temperatura WHERE id = ?";
		
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
	
	public Temperatura consultar(int id) 
	{
		Temperatura temperatura = new Temperatura();
		temperatura.setId(id);
		
		String sql = "SELECT * FROM temperatura WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setInt(1, temperatura.getId());
			try (ResultSet rs = stm.executeQuery();) 
			{
				retornaDados(rs, temperatura);
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
		return temperatura;
	}
	
	public ArrayList<Temperatura> listar()
	{
		ArrayList<Temperatura> lista = new ArrayList<Temperatura>();
		String sql = "SELECT id FROM temperatura";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);
				ResultSet rs = stm.executeQuery())
		{
			while (rs.next()) 
			{
				Temperatura temperatura = new Temperatura();
				retornaDados(rs, temperatura);
				lista.add(temperatura);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lista;
	}
	
	private void modificaDados(PreparedStatement stm, Temperatura temperatura) throws SQLException
	{
		stm.setInt(1, temperatura.getTemperaturaAtual());
		stm.setInt(2, temperatura.getStatusDoArCondicionado().getValor());
	}
	
	private void retornaDados(ResultSet rs, Temperatura temperatura) throws SQLException
	{
		if (rs.next()) 
		{
			temperatura.setId(rs.getInt("id"));
			temperatura.setTemperaturaAtual(rs.getInt("temperatura_atual"));
			
			if(rs.getInt("ar_condicionado") == 0)
				temperatura.setStatusDoArCondicionado(StatusDoArCondicionado.Desligado);
			else
				temperatura.setStatusDoArCondicionado(StatusDoArCondicionado.Ligado);
		} 
		else 
			temperatura.setId(0);
	}
}
