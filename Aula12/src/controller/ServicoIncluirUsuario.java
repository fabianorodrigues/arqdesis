package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import service.UsuarioService;
import util.JSonUsuario;;

@WebServlet("/servicoIncluirUsuario")
public class ServicoIncluirUsuario extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		super.service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		StringBuilder strJSon = JSonUsuario.criarJSon(request);
		PrintWriter out = response.getWriter();
		
		try 
		{
			Usuario usuario = JSonUsuario.toUsuario(strJSon.toString());
			UsuarioService service = new UsuarioService();
			
			service.incluir(usuario);
			
			out.println(JSonUsuario.toJSon(usuario));
				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}