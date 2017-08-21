package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest)request;
		Cookie cookie = getUsuario(req);
		String usuario = "<deslogado>";
		
		if(cookie != null) usuario = cookie.getValue(); 
		
		System.out.println(String.format("Usuario %s aceesando a URI %s", 
				usuario, req.getRequestURI()));
		chain.doFilter(request, response);
	}

	private Cookie getUsuario(HttpServletRequest req) 
	{
		Cookie[] cookies = req.getCookies();
		
		if(cookies == null) return null;
		
		for (Cookie cookie : cookies) 
			if(cookie.getName().equals("usuario.logado")) return cookie;
		
		
		return null;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
