<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.cmc.model.*, com.cmc.database.DBInteractions, com.cmc.controller.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Account src = (Account)session.getAttribute("loggedInUser");
		Account targ = (Account)session.getAttribute("viewing");
		
		AccountController controller = AccountController.getInstance();
		if (firstName != null && !firstName.trim().equals("")) controller.editBasicUserInfo(src, targ, AccountController.ManagedField.FIRSTNAME, firstName);
		
		
		
		application.getRequestDispatcher("/userHome.jsp").forward(request, response);
	%>
</body>
</html>