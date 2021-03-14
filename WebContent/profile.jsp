<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.cmc.model.*, com.cmc.database.DBInteractions"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Account user = DBInteractions.getInstance().getUserByUserName(request.getParameter("viewing")); 
	if (user == null) user = (Account)session.getAttribute("viewing");
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
				<td>Type</td>
				<td><%=user.getType() %></td>
			</tr>
		</table>
	<% } %>
	<a href = "editUser.jsp">Edit User</a><br>
	<a href="userHome.jsp">Home</a><br>
	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a><br>
	<%session.setAttribute("from", "profile.jsp"); %>
</body>
</html>