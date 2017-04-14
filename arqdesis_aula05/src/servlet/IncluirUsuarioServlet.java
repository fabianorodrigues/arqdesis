package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
		PrintWriter writer = response.getWriter();
		
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
		
		writer.println(usuarioIncluido(usuario.getId()));
	}
	
	public String usuarioIncluido(int codigo)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("<html><head><title> Usuário Cadastrado!</title></head><body>");
		str.append("<form method=\"get\" action = \"http://localhost:8080/arqdesis_aula05/index.html\">");
		str.append("Usuário incluido com sucesso!<br/>Código do usuário: ");
		str.append(codigo);
		str.append("<br><input type = \"submit\" value = \"Voltar\"></body></html> ");

		return str.toString();
	}

}
