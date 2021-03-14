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
<% Account user = (Account)session.getAttribute("viewing"); %>
	<p>Your profile information:</p>
	
	<form action = "editUserPortal.jsp" method = "post">
		<table border=1 width="75%">
			<thead>
				<tr>
					<td>Attribute</td>
					<td>Old Value</td>
					<td>New Value</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>First Name</td>
					<td><%=user.getFirstName()%></td>
					<td><input type="text" name="firstName"></input></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><%=user.getLastName()%></td>
					<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<td>Username</td>
					<td><%=user.getUsername()%></td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><%=user.getPassword()%></td>
					<td><input type="text" name="password"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit"></td>
					<td><input type="reset" value="Reset"></td>
				</tr>
			</tbody>
		</table>
	</form>
	
	<a href="userHome.jsp">Home</a><br>
	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a><br>
	<% session.setAttribute("from", "profile.jsp"); %>
</body>
</html>