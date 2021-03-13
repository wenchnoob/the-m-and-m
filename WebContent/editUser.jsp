<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.cmc.model.*, com.cmc.database.DBInteractions"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile Information</title>
</head>
<body>
<%Account user = (Account)session.getAttribute("loggedInUser"); %>
	<p>Your profile information:</p>
	<form action = "viewUser.jsp" method = "post">
	<table border=1 width="75%">
	<tr>
	<td>First Name</td>
	<td>
	<input type="text" name="firstName"></input></td>
	</tr>
	<tr>
	<td>Last Name</td>
	<td><input type="text" name="lastName"></td>
	</tr>
	<tr>
	<td>Username</td>
	<td><input type="text" name="username"></td>
	</tr>
	</tr>
	<tr>
	<td>Password</td>
	<td><input type="text" name="password"></td>
	</tr>
	</table>
	</form>
	<a href = "editUserPortal.jsp">Save User</a>
	<a href = "editUser.jsp">Edit User</a>
	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a>
	<% session.setAttribute("from", "profile.jsp"); %>
</body>
</html>