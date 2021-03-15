<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.cmc.model.*, com.cmc.database.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View University</title>
</head>
<body>
	<%
		University uni = DBInteractions.getInstance().getUniversityByName(request.getParameter("viewing"));
		session.setAttribute("viewing", uni);
	%>
	
	<p>University Information:</p>
	<% if (uni != null) { %>
		<table border=1 width="75%">
		<thead>
			<tr>
				<th>Attribute</th>
				<th>Value</th>
			</tr>
		</thead>	
			<tbody>
				<tr>
					<td>Name</td>
					<td><%= uni.getName()%></td>
				</tr>
				<tr>
					<td>State</td>
					<td><%= uni.getState() %></td>
				</tr>
				<tr>
					<td>Location</td>
					<td><%= uni.getLocation() %></td>
				</tr>
				<tr>
					<td>Control</td>
					<td><%= uni.getControl() %></td>
				</tr>
				<tr>
					<td>Number of Students</td>
					<td><%= uni.getNumStudents() %></td>
				</tr>
				<tr>
					<td>SAT Math</td>
					<td><%= uni.getSatMath() %></td>
				</tr>
				<tr>
					<td>SAT Verbal</td>
					<td><%= uni.getSatVerbal() %></td>
				</tr>
				<tr>
					<td>Expenses</td>
					<td><%= uni.getExpenses() %></td>
				</tr>
				<tr>
					<td>Number of Applications</td>
					<td><%= uni.getNumOfApps()%></td>
				</tr>
				<tr>
					<td>Academic Scale</td>
					<td><%= uni.getAcademicScale() %></td>
				</tr>
				<tr>
					<td>Social Scale</td>
					<td><%= uni.getSocialScale() %></td>
				</tr>
				<tr>
					<td>Emphases</td>
					<td><%= uni.getEmphases() %></td>
				</tr>
				<tr>
					<td>Percent Female</td>
					<td><%= uni.getPerFemale() %></td>
				</tr>
				<tr>
					<td>Percent Financial Aid</td>
					<td><%= uni.getPerFinAid() %></td>
				</tr>
				<tr>
					<td>Percent Admitted</td>
					<td><%= uni.getPerAdmitted() %></td>
				</tr>
				<tr>
					<td>Percent Enrolled</td>
					<td><%= uni.getPerEnrolled() %></td>
				</tr>
				<tr>
					<td>Quality of Life</td>
					<td><%= uni.getQualityLife() %></td>
				</tr>	
			</tbody>
		</table>
		<%
			Account curUser = (Account)session.getAttribute("loggedInUser");
			if (curUser != null && curUser.getType() == Account.AccountType.BASIC_USER) {
		%>
			<a href="saveUniversity.jsp?viewing=<%=uni.getName()%>">Save</a><br>
		<% } %>
	<% } %>
	<a href="editUniversity.jsp">Edit University</a><br>
	<a href="userHome.jsp">Home</a><br>

</body>
</html>