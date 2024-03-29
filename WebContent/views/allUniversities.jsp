<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.cmc.controller.*, com.cmc.model.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Universities</title>
</head>
<body>
	<h1>All Universities in our system</h1>
	
	<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>State</th>
				<th>Location</th>
				<th>Control</th>
				<th>Number of Students</th>
				<th>SAT Math</th>
				<th>SAT Verbal</th>
				<th>Expenses</th>
				<th>Number of Applications</th>
				<th>Academic Scale</th>
				<th>Social Scale</th>
				<th>Emphases</th>
				<th>Percent Female</th>
				<th>Percent Financial Aid</th>
				<th>Percent Admitted</th>
				<th>Percent Enrolled</th>
				<th>Quality of Life</th>
				<th>View</th>
			</tr>
		</thead>
		<tbody>
		<%
			for (University uni: AdminFunctionalityController.getInstance().viewAllUniversities()) {
		%>
			<tr>
				<td>
					<%= uni.getName()%>
				</td>
				<td>
					<%= uni.getState() %>
				</td>
				<td>
					<%= uni.getLocation() %>
				</td>
				<td>
					<%= uni.getControl() %>
				</td>
				<td>
					<%= uni.getNumStudents() %>
				</td>
				<td>
					<%= uni.getSatMath() %>
				</td>
				<td>
					<%= uni.getSatVerbal() %>
				</td>
				<td>
					<%= uni.getExpenses() %>
				</td>
				<td>
					<%= uni.getNumOfApps()%>
				</td>
				<td>
					<%= uni.getAcademicScale() %>
				</td>
				<td>
					<%= uni.getSocialScale() %>
				</td>
				<td>
					<%= uni.getEmphases() %>
				</td>
				<td>
					<%= uni.getPerFemale() %>
				</td>
				<td>
					<%= uni.getPerFinAid() %>
				</td>
				<td>
					<%= uni.getPerAdmitted() %>
				</td>
				<td>
					<%= uni.getPerEnrolled() %>
				</td>
				<td>
					<%= uni.getQualityLife() %>
				</td>
				<td>
					<a href="../views/viewUniversity.jsp?viewing=<%=uni.getName()%>">View</a>
				</td>
			</tr>	
		<%
			}
		%>
		</tbody>
	</table>
	
	<a href="../views/userHome.jsp">Home</a><br>
</body>
</html>