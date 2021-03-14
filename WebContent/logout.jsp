<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cmc.model.*, com.cmc.database.DBInteractions, com.cmc.controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>You have logged out of CMC</title>
</head>
<%
	AccountController.getInstance().logout(request.getParameter("username"));
	session.removeAttribute("loggedInUser");
%>
<body>
<p>You have logged out successfully.</p>
<a href = "index.jsp">Main Page</a>
</body>
