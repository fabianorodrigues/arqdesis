package util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import model.Usuario;
import model.Usuario.TipoDeUsuario;

public class JSonUsuario 
{
	public static StringBuilder criarJSon(HttpServletRequest request) throws IOException 
	{	
		StringBuilder strJSon = new StringBuilder();
		BufferedReader reader = request.getReader();
		
		try 
		{
			String linha;
			
			while ((linha = reader.readLine()) != null)
				strJSon.append(linha).append('\n');
		} 
		finally 
		{
			reader.close();
		}
		
		return strJSon;
	}


	public static Usuario toUsuario(String json) throws IOException 
	{
		try 
		{
			JSONObject objetoJSon = new JSONObject(json);
			
			Usuario usuario = new Usuario();
			
			usuario.setNome(objetoJSon.getString("nome"));
			usuario.setCpf(objetoJSon.getString("cpf"));
			usuario.setRg(objetoJSon.getString("rg"));
			usuario.setDataNascimento(objetoJSon.getString("data"));
			usuario.setLogin(objetoJSon.getString("login"));
			usuario.setSenha(objetoJSon.getString("senha"));
			
			String tipo = objetoJSon.getString("tipo");
			if(tipo.equals("Atendente")) usuario.setTipoDeUsuario(TipoDeUsuario.Atendente);
			else if(tipo.equals("Gerente")) usuario.setTipoDeUsuario(TipoDeUsuario.Gerente);
			
			return usuario;
		} 
		catch (JSONException jsone) 
		{
			jsone.printStackTrace();
			throw new IOException(jsone);
		}
	}

	public static String toJSon(Usuario usuario) throws IOException 
	{
		JSONObject objetoJSon = new JSONObject();
		
		try 
		{			
			objetoJSon.put("nome", usuario.getNome());
			objetoJSon.put("cpf", usuario.getCpf());
			objetoJSon.put("rg", usuario.getRg());
			objetoJSon.put("data", usuario.getDataNascimento());
			objetoJSon.put("login", usuario.getLogin());
			objetoJSon.put("senha", usuario.getSenha());
			
			if(usuario.getTipoDeUsuario().equals(TipoDeUsuario.Atendente)) objetoJSon.put("tipo", "Atendente");
			else if(usuario.getTipoDeUsuario().equals(TipoDeUsuario.Gerente)) objetoJSon.put("tipo", "Gerente");
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return objetoJSon.toString();
	}

}
