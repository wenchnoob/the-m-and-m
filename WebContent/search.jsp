<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.cmc.model.*, com.cmc.database.DBInteractions"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search for Schools</title>
</head>
<body>

	<h1>Search Schools</h1>
	<form action="searchResults.jsp" method="post">
		<table border=1 width="88%">
			<tr>
				<td>School Name</td>
				<td><input type="text" name="schoolName"></input></td>
			</tr>
			<tr>
				<td>State</td>
				<td><input type="text" name="state"></input></td>
			</tr>
			<tr>
				<td>Location</td>
				<td><input type="text" name="location"> (SUBURBAN,
					URBAN, SMALL-CITY, OR -1 FOR UNKNOWN)</td>
			</tr>
			<tr>
				<td>Control</td>
				<td><input type="text" name="control"> (PRIVATE, STATE,
					CITY, OR -1 FOR UNKNOWN)</td>
			</tr>
			<tr>
				<td>Number of Students</td>
				<td>between<input type="text" name="numStudents1">and<input
					type="text" name="numStudents2"></td>
			</tr>
			<tr>
				<td>Percent Female</td>
				<td>between<input type="text" name="perFemale1">and<input
					type="text" name="perFemale2"></td>
			</tr>
			<tr>
				<td>SAT Verbal</td>
				<td>between<input type="text" name="satv1">and<input
					type="text" name="satv2"></td>
			</tr>
			<tr>
				<td>SAT Math</td>
				<td>between<input type="text" name="satm1">and<input
					type="text" name="satm2"></td>
			</tr>
			<tr>
				<td>Expenses</td>
				<td>between<input type="text" name="exp1">and<input
					type="text" name="exp2"></td>
			</tr>
			<tr>
				<td>Percent Financial Aid</td>
				<td>between<input type="text" name="perFinAid1">and<input
					type="text" name="perFinAid2"></td>
			</tr>
			<tr>
				<td>Number of Applicants</td>
				<td>between<input type="text" name="numApp1">and<input
					type="text" name="numApp2"></td>
			</tr>
			<tr>
				<td>Percent Admitted</td>
				<td>between<input type="text" name="perAdmitted1">and
				<input type="text" name="perAdmitted2"></td>
			</tr>
			<tr>
				<td>Percent Enrolled</td>
				<td>between<input type="text" name="perEnrolled1">and
				<input type="text" name="perEnrolled2"></td>
			</tr>
			<tr>
				<td>Academic Scale (1 - 5)</td>
				<td>between<input type="text" name="acadScale1">and
				<input type="text" name="acadScale2"></td>
			</tr>
			<tr>
				<td>Social Scale (1 - 5)</td>
				<td>between<input type="text" name="socScale1">and
				<input type="text" name="socScale2"></td>
			</tr>
			<tr>
				<td>Quality of Life (1 - 5)</td>
				<td>between<input type="text" name="qualLife1">and
				<input type="text" name="qualLife2"></td>
			</tr>
			<tr>
				<td>Emphases</td>
				<td>contains either:<br>
				<input type="text" name="emphasis1"><br>
				<input type="text" name="emphasis2"><br> <input
					type="text" name="emphasis3"><br>
				<input type="text" name="emphasis4"><br> <input
					type="text" name="emphasis5"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
				<td><input type="reset" value="Reset"></td>
			</tr>
		</table>
	</form>

	<a href="userHome.jsp">Home</a>
	<br>
	<a
		href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go
		Back!</a>
	<br>
	<% session.setAttribute("from", "search.jsp"); %>
</body>
</html>