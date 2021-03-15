<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.cmc.controller.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		session.setAttribute("loggedInUser", AccountController.getInstance().logon(request.getParameter("username"), request.getParameter("password")));
		application.getRequestDispatcher("../../views/userHome.jsp").forward(request, response);
	%>
</body>
</html>