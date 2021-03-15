<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.cmc.model.*, java.util.*, com.cmc.database.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Saved Schools</title>
</head>
<body>
	
	<%
		Account user = (Account)session.getAttribute("loggedInUser");
		if (user == null || user.getClass() != User.class) {
			application.getRequestDispatcher("../index.jsp").forward(request, response);
			return;
		}
		
		Map<String, UserSchool> school = ((User)user).getSavedSchools();
		DBInteractions interactions = DBInteractions.getInstance();
	%>
		<table border="2">
			<thead>
				<tr>
					<th colspan="3">Saved Schools</th>
				</tr>
			</thead>
			<tbody>
				<% for (String schoolName: school.keySet()) { %>
					<tr>	
						<td><%= schoolName %></td>
						<td><a href="viewUniversity.jsp?viewing=<%=schoolName%>">View</a></td>
						<td><a href="../actions/remove/unsaveUniversity.jsp?remove=<%=schoolName%>">Remove</a></td>
					</tr>
				<% } %>
			</tbody>
		</table>
	

	<a href="userHome.jsp">Home</a><br>
</body>
</html>