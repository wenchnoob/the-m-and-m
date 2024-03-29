<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.cmc.controller.*, com.cmc.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accounts</title>
</head>
<body>
	<h1>All Accounts in our system</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Username</th>
				<th>Type</th>
				<th>Enabled Status</th>
				<th>View</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Account user: AdminFunctionalityController.getInstance().viewAllAccounts()) {
		%>
			<tr>
				<td><%= user.getLastName() + ", " + user.getFirstName() %></td>
				<td><%= user.getUsername() %></td>
				<td><%= user.getType() %></td>
				<td><%= user.isEnabled() ? "YES": "NO" %></td>
				<td><a href="../views/profile.jsp?viewing=<%=user.getUsername()%>">View</a>
				</td>
			</tr>
			<%
			}
		%>
		</tbody>
	</table>
	<a href="../views/addUser.jsp">Add Account</a><br>
	<a href="../views/userHome.jsp">Home</a><br>
</body>
</html>