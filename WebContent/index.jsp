<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose My College</title>
</head>
<body>
	<h1>Choose My College</h1>
	<div>
		<ul>
			<li><a href="login.jsp">Login</a></li>
		</ul>
	</div>
	
	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a><br>
	<% session.setAttribute("from", "index.jsp"); %>
</body>
</html>