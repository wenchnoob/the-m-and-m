<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.cmc.model.*, com.cmc.database.DBInteractions, com.cmc.controller.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
</head>
<body>
	<br> Add User form:
	<br>
	<br>
	<form method="post" action="addAction.jsp" name="addUser">
		<br>
		<table style="text-align: left; width: 266px; height: 228px;"
			border="1" cellpadding="2" cellspacing="2">
			<tbody>
				<tr>
					<td style="vertical-align: top;">First Name<br>
					</td>
					<td style="vertical-align: top;"><input name="firstName"><br>
					</td>
				</tr>
				<tr>
					<td style="vertical-align: top;">Last Name<br>
					</td>
					<td style="vertical-align: top;"><input name="lastName"><br>
					</td>
				</tr>
				<tr>
					<td style="vertical-align: top;">Username<br>
					</td>
					<td style="vertical-align: top;"><input name="username">
					</td>
				</tr>
				<tr>
					<td style="vertical-align: top;">Password<br>
					</td>
					<td style="vertical-align: top;"><input name="password">
					</td>
				</tr>
				<tr>
					<td style="vertical-align: top;">Type<br>
					</td>
					<td><label>ADMIN:<input type="radio" name="type"
							value="ADMIN"></label><br> <label>BASIC_USER:<input
							type="radio" name="type" value="BASIC_USER"></label></td>
				</tr>
				<tr>
					<td style="vertical-align: top;">Status<br>
					</td>
					<td><label>TRUE:<input type="radio" name="status"
							value="TRUE"></label><br> <label>FALSE:<input
							type="radio" name="status" value="FALSE"></label></td>
				</tr>
				<tr>
					<td style="vertical-align: top;"><input value="Add" name="Add"
						type="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td style="vertical-align: top;"><input value="Reset"
						name="Reset" type="reset"></td>
				</tr>
			</tbody>
		</table>
		<br>
	</form>
	<br>
<a href="allAccounts.jsp">View Accounts</a>
<br>
</body>
</html>