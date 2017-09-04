package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao 
{
	static
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException ex) 
		{
			throw new RuntimeException(ex);
		}
				
	}
	
	public static Connection retornaConexao() throws SQLException
	{
		return DriverManager.
				getConnection("jdbc:mysql://localhost:3306/controle_predial?autoReconnect=true&useSSL=false", "root", "123456");
		
	}
	
	
}
