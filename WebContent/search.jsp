<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a>
	<% session.setAttribute("from", "search.jsp"); %>
</body>
</html>