<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.cmc.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Account curUser = (Account) session.getAttribute("loggedInUser");
		boolean success = false;
		if (curUser != null && curUser.getType() == Account.AccountType.BASIC_USER) {
			if (curUser.getClass() == User.class)
				success = ((User) curUser).unsaveSchool(request.getParameter("remove"));
		}

		if (success) {
			out.println("<h1>Successful unsave</h1>");
		} else {
			out.println("<h1>Failed unsave.</h1>");
		}
	%>
	<a href="../../views/userHome.jsp">Home</a><br>
</body>
</html>