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
		String status = request.getParameter("status");
		String type = request.getParameter("type");
		
		Account src = (Account)session.getAttribute("loggedInUser");
		Account targ = (Account)session.getAttribute("viewing");
		
		AccountController controller = AccountController.getInstance();
		AdminFunctionalityController controller2 = AdminFunctionalityController.getInstance();
		
		if (firstName != null && !firstName.trim().equals("")) controller.editBasicUserInfo(src, targ, AccountController.ManagedField.FIRSTNAME, firstName);
		if (lastName != null && !lastName.trim().equals("")) controller.editBasicUserInfo(src, targ, AccountController.ManagedField.LASTNAME, lastName);
		if (password != null && !password.trim().equals("")) controller.editBasicUserInfo(src, targ, AccountController.ManagedField.PASSWORD, password);
		
		if (status != null && !status.trim().equals("")) {
			boolean enabled = status.equals("TRUE");
			controller2.changeStatus(src, targ, enabled);
		}
		
		
		
		if (type != null && !type.trim().equals("")) {
			Account.AccountType acctype = type.trim().equals("ADMIN") ? Account.AccountType.ADMIN : Account.AccountType.BASIC_USER;
			controller2.changeUserType(src, targ, acctype);
		}
		
		application.getRequestDispatcher("/profile.jsp?viewing=" + targ.getUsername()).forward(request, response);
	%>
</body>
</html>