<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	
	
	<h1>Login</h1>
	<form action="../actions/update/loginPortal.jsp" method="post">
		<label>Username: <input type="text" name="username"></label><br>
		<label>Password: <input type="password" name="password"></label><br>
		<input type="submit" value="Submit">
	</form>
	
	<a href="../index.jsp">Home</a><br>
</body>
</html>