<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.cmc.model.*, com.cmc.database.DBInteractions, com.cmc.controller.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<title>Add User</title>
</head>
<body>

	<%
	if (request.getParameter("firstName") == null || request.getParameter("lastName") == null || request.getParameter("username") == null 
	|| request.getParameter("password") == null || request.getParameter("type") == null || request.getParameter("status") == null){
		application.getRequestDispatcher("/allAccounts.jsp").forward(request, response);
		return;
	}
		
		String firstName = request.getParameter("firstName").trim();
		
		String lastName = request.getParameter("lastName").trim();
		
		String username = request.getParameter("username").trim();
		
		String password = request.getParameter("password").trim();
		
		String type = request.getParameter("type").trim();
		
		String status = request.getParameter("status").trim();
		
		
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		
		boolean enabled = status.equals("TRUE");

		Account.AccountType acctype = type.trim().equals("ADMIN") ? Account.AccountType.ADMIN : Account.AccountType.BASIC_USER;
		
		boolean works = controller.addUser((Account)session.getAttribute("loggedInUser"), firstName, lastName, username, password, "basic question", "basic answer",
				enabled, acctype);

		application.getRequestDispatcher("/allAccounts.jsp").forward(request, response);
	%>
</body>
</html>