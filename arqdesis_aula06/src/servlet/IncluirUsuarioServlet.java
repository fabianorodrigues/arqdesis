package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import model.Usuario.TipoDeUsuario;
import service.UsuarioService;


@WebServlet("/incluirUsuario")
public class IncluirUsuarioServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		Usuario usuario = new Usuario();
		UsuarioService service = new UsuarioService();
		
		usuario.setNome(request.getParameter("nome"));
		usuario.setCpf(request.getParameter("cpf"));
		usuario.setRg(request.getParameter("rg"));
		usuario.setDataNascimento(request.getParameter("data"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		
		String tipo = request.getParameter("tipo");
		if(tipo.equals("Atendente")) usuario.setTipoDeUsuario(TipoDeUsuario.Atendente);
		else if(tipo.equals("Gerente")) usuario.setTipoDeUsuario(TipoDeUsuario.Gerente);
		
		service.incluir(usuario);
		
		request.setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("IncluirUsuario.jsp");
		dispatcher.forward(request, response);
		
	}
	

}
