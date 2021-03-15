<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.cmc.model.*, com.cmc.controller.*, com.cmc.database.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home</title>
</head>
<body>
	<%
		Account loggedInUser = (Account)session.getAttribute("loggedInUser");
	%>
	
	<% 
		if (loggedInUser != null) {
	%>
		<h1>Choose My College</h1>
		<div>
			<ul>
				<li><a href="profile.jsp?viewing=<%=loggedInUser.getUsername()%>">View Profile</a></li>
				
				<% 
					if (loggedInUser.getType() == Account.AccountType.BASIC_USER) {
				%>
					<li><a href="search.jsp">Search Universities</a></li>
					<li><a href="viewSavedSchools.jsp">View Saved Schools</a></li>
				<%
					}
				%>
				
				<% 
					if (loggedInUser.getType() == Account.AccountType.ADMIN) {
				%>
						<li><a href="allAccounts.jsp">View All Accounts</a></li>
						<li><a href="allUniversities.jsp">View All Universities</a></li>
				<%
					}
				%>
				
				<li><a href="../actions/update/logout.jsp">Log Out</a></li>
			</ul>
		</div>
	<% } else { %>
		<h1>Error Something went wrong</h1>
		<a href = "../index.jsp">Main Page</a>
	<% } %>
	
	
	
</body>
</html>