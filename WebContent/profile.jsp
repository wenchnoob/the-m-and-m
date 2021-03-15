<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.cmc.model.*, com.cmc.database.DBInteractions"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>
<%
	Account user = DBInteractions.getInstance().getUserByUserName(request.getParameter("viewing"));
	session.setAttribute("viewing", user);
%>
	<p>Your profile information:</p>
	<% if (user != null) { %>
		<table border=1 width="75%">
			<tr>
				<td>First Name</td>
				<td><%=user.getFirstName() %></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><%=user.getLastName() %></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><%=user.getUsername()%></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><%=user.getPassword() %></td>
			</tr>
			<tr>
				<td>Status</td>
				<td><%=user.isEnabled() == true ? "ENABLED": "DISABLED" %></td>
			</tr>
			<tr>
				<td>Type</td>
				<td><%=user.getType() %></td>
			</tr>
		</table>
	<% } %>
	<a href="editUser.jsp">Edit User</a><br>
	<a href="userHome.jsp">Home</a><br>
</body>
</html>