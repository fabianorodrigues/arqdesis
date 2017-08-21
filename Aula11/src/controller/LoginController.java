package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import service.UsuarioService;;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        UsuarioService service = new UsuarioService();

        Usuario usuario = service.consultar(req.getParameter("login"), req.getParameter("senha"));

        if (usuario == null) {
            writer.println("<html><body>Usuário ou senha inválida</body></html>");
        } else {
            Cookie cookie = new Cookie("usuario.logado", usuario.getLogin());
            resp.addCookie(cookie);
            writer.println("<html><body>Usuário logado: " + usuario.getLogin()
                    + "</body></html>");
        }

    }

}