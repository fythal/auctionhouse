<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>#
<%@ page language="java" import="UserController.java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="header">eBay 2.0
	<div id="form">
	<%
	if ( session.getAttribute( "theMail" ) != null) { %>
		Sie sind angemeldet als: <% session.getAttribute( "theMail" ) ; 
	}
	else { %>
		<form action="SaveSession.jsp" method="post" onsubmit="return verifyForm(this);">
		<p><input name="email" type="text" size="20" value="E-Mail"></p>
		<p><input name="password" type="password" size="20" value="******"></p>
		<input type="submit" name="login" value="Login">
		</form>
		<form action="form.jsp" method="post">
		<input type="submit" name="register" value="Register">
		</form>
	</div>
	<% } %>
</div>
</body>
</html>