<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	
	
	<h1>Login</h1>
	<form action="loginPortal.jsp" method="post">
		<label>Username: <input type="text" name="username"></label><br>
		<label>Password: <input type="text" name="password"></label><br>
		<input type="submit" value="Submit">
	</form>
	
	<button onclick="window.location.href='<%= (String)session.getAttribute("from") %>'">Go Back!</button>
	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a>
	<% session.setAttribute("from", "login.jsp"); %>
</body>
</html>