package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Empresa;

public class EmpresaDAO 
{
	
	public int incluir(Empresa empresa) 
	{
		String sql = "INSERT INTO empresa(conjunto, cnpj, razao_social, horario_inicio, horario_encerramento, temperatura_maxima) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			modificaDados(stm, empresa);
			stm.execute();
			
			sql = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sql);
					ResultSet rs = stm2.executeQuery();) 
			{
				if (rs.next())
					empresa.setId(rs.getInt(1));		
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
		
		return empresa.getId();
	}

	public void alterar(Empresa empresa) 
	{
		String sql = "UPDATE empresa SET conjunto = ?, cnpj = ?, razao_social = ?, horario_inicio = ?, "
				+ "horario_encerramento = ?, temperatura_maxima = ? WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			modificaDados(stm, empresa);
			stm.setInt(7, empresa.getId());		
			stm.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void excluir(int id) 
	{
		String sql = "DELETE FROM empresa WHERE id = ?";
		
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

	public Empresa consultar(int id) 
	{
		Empresa empresa = new Empresa();
		empresa.setId(id);
		
		String sql = "SELECT * FROM empresa WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setInt(1, empresa.getId());
			try (ResultSet rs = stm.executeQuery();) 
			{
				retornaDados(rs, empresa);
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
		return empresa;
	}
	
	public ArrayList<Empresa> listar()
	{
		ArrayList<Empresa> lista = new ArrayList<Empresa>();
		String sql = "SELECT * FROM empresa";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);
				ResultSet rs = stm.executeQuery())
		{
			while (rs.next()) 
			{
				Empresa empresa = new Empresa();
				retornaDados(rs, empresa);
				lista.add(empresa);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public boolean cnpjExistente(String cnpj)
	{
		String sql = "SELECT COUNT(cnpj) AS 'total' FROM empresa WHERE cnpj = ?";
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);)
		{
			stm.setString(1, cnpj);
			try (ResultSet rs = stm.executeQuery();)
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
	
	private void modificaDados(PreparedStatement stm, Empresa empresa) throws SQLException
	{
		stm.setInt(1, empresa.getConjunto().getId());
		stm.setString(2, empresa.getCnpj());
		stm.setString(3, empresa.getRazaoSocial());
		stm.setString(4, empresa.getHorarioInicio());
		stm.setString(5, empresa.getHorarioEncerramento());
		stm.setInt(6, empresa.getTemperaturaMaxima());
	}
	
	private void retornaDados(ResultSet rs, Empresa empresa) throws SQLException
	{
		if (rs.next()) 
		{
			empresa.getConjunto().setId(rs.getInt("conjunto"));
			empresa.setCnpj(rs.getString("cnpj"));
			empresa.setRazaoSocial(rs.getString("razao_social"));
			empresa.setHorarioInicio(rs.getString("horario_inicio"));
			empresa.setHorarioEncerramento(rs.getString("horario_encerramento"));
			empresa.setTemperaturaMaxima(rs.getInt("temperatura_maxima"));
		} 
		else 
			empresa.setId(0);
	}
}






