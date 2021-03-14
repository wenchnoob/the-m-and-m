<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.cmc.model.*, com.cmc.database.DBInteractions, com.cmc.controller.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<title>Add User</title>
</head>
<body>

	<%
		String firstName = request.getParameter("firstName");
		System.out.println(firstName);
		String lastName = request.getParameter("lastName");
		System.out.println(lastName);
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		System.out.println(password);
		String type = request.getParameter("type");
		System.out.println(type);
		String status = request.getParameter("status");
		System.out.println(status);
		//Account user = new User(firstName, lastName, username, password, type.charAt(0), status.charAt(0));

		//controller.addUser(user);

		application.getRequestDispatcher("/Menu.jsp").forward(request, response);
	%>
</body>
</html>