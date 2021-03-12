<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.cmc.controller.*, com.cmc.model.*" %>

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
			</tr>
		</thead>
		<tbody>
		<%
			for (Account user: AdminFunctionalityController.getInstance().viewAllAccounts()) {
		%>
			<tr>
				<td>
					<%= user.getLastName() + ", " + user.getFirstName() %>
				</td>
				<td>
					<%= user.getUsername() %>
				</td>
				<td>
					<%= user.getType() %>
				</td>
				<td>
					<%= user.isEnabled() ? "YES": "NO" %>
				</td>
			</tr>	
		<%
			}
		%>
		</tbody>
	</table>
	
	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a>
	<% session.setAttribute("from", "allAccounts.jsp"); %>
</body>
</html>