package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import service.ClienteService;

/**
 * Servlet implementation class CrudClientesServlet
 */
@WebServlet("/crudClientes")
public class CrudClientesServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudClientesServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Cliente cliente = new Cliente();
		ClienteService service = new ClienteService();
		String oQueFazer = request.getParameter("oQueFazer");
		PrintWriter out = response.getWriter();
		
		cliente.setId(Integer.parseInt(request.getParameter("idCliente")));
		cliente.setNome(request.getParameter("nomeCliente"));
		cliente.setFone(request.getParameter("foneCliente"));
		cliente.setEmail(request.getParameter("emailCliente"));
		
		
		
		switch (oQueFazer) 
		{
			case "Cadastrar":
				cliente.setId(service.criar(cliente));
				out.println(clienteIncluido(cliente.getId()));
				break;
			case "Consultar":
				cliente = service.carregar(cliente.getId());
				out.println(clienteConsultado(cliente));
				break;
			case "Remover":
				service.excluir(cliente.getId());
				out.println(clienteRemovido(cliente.getId()));
				break;
			case "Atualizar":
				service.atualizar(cliente);
				out.println(clienteAtualizado(cliente));
				break;
		}
	}
		
	public String clienteIncluido(int codigo)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("<html><head><title> Cliente Cadastrado!</title></head><body>");
		str.append("<form method=\"get\" action = \"http://localhost:8080/arqdesis_crud_clientes/clientes.html\">");
		str.append("Código do cliente: ");
		str.append(codigo);
		str.append("<br><input type = \"submit\" value = \"Voltar\"></body></html> ");

		return str.toString();
	}
	
	public String clienteRemovido(int codigo)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("<html><head><title> Cliente Removido!</title></head><body>");
		str.append("<form method=\"get\" action = \"http://localhost:8080/arqdesis_crud_clientes/clientes.html\">");
		str.append("Cliente com id ");
		str.append(codigo);
		str.append(" removido com sucesso");
		str.append("<br><input type = \"submit\" value = \"Voltar\"></body></html> ");

		return str.toString();
	}
	
	public String clienteAtualizado(Cliente cliente)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("<html><head><title> Cliente Atualizado!</title></head><body>");
		str.append("<form method=\"get\" action = \"http://localhost:8080/arqdesis_crud_clientes/clientes.html\">");
		str.append("Dados atualizados:<br> ");
		str.append("Código: ");
		str.append(cliente.getId());
		str.append("<br>Nome: ");
		str.append(cliente.getNome());
		str.append("<br>Telefone: ");
		str.append(cliente.getFone());
		str.append("<br>Email: ");
		str.append(cliente.getEmail());
		str.append("<br><input type = \"submit\" value = \"Voltar\"></body></html> ");

		return str.toString();
	}
	
	public String clienteConsultado(Cliente cliente)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("<html><head><title> Consulta de cliente </title></head><body>");
		str.append("<form method=\"get\" action = \"http://localhost:8080/arqdesis_crud_clientes/clientes.html\">");
		str.append("Dados do cliente:<br> ");
		str.append("Código: ");
		str.append(cliente.getId());
		str.append("<br>Nome: ");
		str.append(cliente.getNome());
		str.append("<br>Telefone: ");
		str.append(cliente.getFone());
		str.append("<br>Email: ");
		str.append(cliente.getEmail());
		str.append("<br><input type = \"submit\" value = \"Voltar\"></body></html> ");

		return str.toString();
	}
	
	

}
