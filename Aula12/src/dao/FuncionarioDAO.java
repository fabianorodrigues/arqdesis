package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Funcionario;
import model.Funcionario.StatusAlterarTemperatura;

public class FuncionarioDAO 
{
	public int incluir(Funcionario funcionario)
	{
		String sql = "INSERT INTO funcionario(empresa, nome, cpf, rg, nascimento, login, senha, horario_de, horario_ate, alterar_temperatura)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);)
		{
			modificaDados(stm, funcionario);			
			stm.execute();
			
			sql = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sql);
					ResultSet rs = stm2.executeQuery();)
			{
				if(rs.next())
					funcionario.setId(rs.getInt(1));
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return funcionario.getId();
	}
	
	public void alterar(Funcionario funcionario) 
	{
		String sql = "UPDATE funcionario SET empresa = ?, nome = ?, cpf = ?, rg = ?, nascimento = ?, login = ?,"
				+ "senha = ?, horario_de = ?, horario_ate = ?, alterar_temperatura = ? WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			modificaDados(stm, funcionario);
			stm.setInt(11, funcionario.getId());		
			stm.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) 
	{
		String sql = "DELETE FROM funcionario WHERE id = ?";
		
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
	
	public Funcionario consultar(int id) 
	{
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		
		String sql = "SELECT * FROM funcionario WHERE id = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setInt(1, funcionario.getId());
			try (ResultSet rs = stm.executeQuery();) 
			{
				retornaDados(rs, funcionario);
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
		return funcionario;
	}
	
	public Funcionario consultar(String login, String senha) 
	{
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		
		String sql = "SELECT * FROM funcionario WHERE login = ? AND senha = ?";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);) 
		{
			stm.setString(1, funcionario.getLogin());
			stm.setString(2, funcionario.getSenha());
			
			try (ResultSet rs = stm.executeQuery();) 
			{
				retornaDados(rs, funcionario);
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
		return funcionario;
	}
	
	public ArrayList<Funcionario> listar()
	{
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM funcionario";
		
		try (Connection conn = Conexao.retornaConexao();
				PreparedStatement stm = conn.prepareStatement(sql);
				ResultSet rs = stm.executeQuery())
		{
			while (rs.next()) 
			{
				Funcionario funcionario = new Funcionario();	
				retornaDados(rs, funcionario);
				lista.add(funcionario);
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
		String sql = "SELECT COUNT(cpf) AS 'total' FROM funcionario WHERE cpf = ?";
		
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
	
	private void modificaDados(PreparedStatement stm, Funcionario funcionario) throws SQLException
	{
		stm.setInt(1, funcionario.getEmpresa().getId());
		stm.setString(2, funcionario.getNome());
		stm.setString(3, funcionario.getCpf());
		stm.setString(4, funcionario.getRg());
		stm.setString(5, funcionario.getDataNascimento());
		stm.setString(6, funcionario.getLogin());
		stm.setString(7, funcionario.getSenha());
		stm.setString(8, funcionario.getHorarioEntradaDe());
		stm.setString(9, funcionario.getHorarioEntradaAte());
		stm.setInt(10, funcionario.getStatusAlterarTemperatura().getValor());
	}
	
	private void retornaDados(ResultSet rs, Funcionario funcionario) throws SQLException
	{
		if (rs.next()) 
		{
			funcionario.setId(rs.getInt("id"));
			funcionario.getEmpresa().setId(rs.getInt("empresa"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setCpf(rs.getString("cpf"));
			funcionario.setRg(rs.getString("rg"));
			funcionario.setDataNascimento(rs.getString("nascimento"));
			funcionario.setLogin(rs.getString("login"));
			funcionario.setSenha(rs.getString("senha"));
			funcionario.setHorarioEntradaDe(rs.getString("horario_de"));
			funcionario.setHorarioEntradaAte(rs.getString("horario_ate"));
			
			if(rs.getInt("alterar_temperatura") == 0)
				funcionario.setStatusAlterarTemperatura(StatusAlterarTemperatura.Negado);
			else
				funcionario.setStatusAlterarTemperatura(StatusAlterarTemperatura.Permitido);
		} 
		else 
			funcionario.setId(0);
	}
	
	
	
}
