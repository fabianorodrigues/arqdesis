<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Usuário Cadastrado!</title>
	</head>
	
	<body>
	<%@page import="model.Usuario" %>
		<%Usuario usuario = (Usuario)request.getAttribute("usuario"); %>
	
		Usuário incluido com sucesso!</br>
		Código: <%=usuario.getId() %></br>
		Nome: <%=usuario.getNome() %></br>
		Tipo de usuário: <%=usuario.getTipoDeUsuario() %></br>
		
		<input type="submit" value="Voltar" onclick="history.go(-1)"/>
	</body>
</html>