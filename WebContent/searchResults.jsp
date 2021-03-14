<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>School Results</title>
</head>
<body>
	<% 
	SearchController controller = SearchController.getInstance();
	String schoolName = request.getParameter("schoolName");
	String state = request.getParameter("state");
	String location = request.getParameter("location");
	String control = request.getParameter("control");
	int numStudents1 = Integer.parseInt(request.getParameter("numStudents1"));
	int numStudents2 = Integer.parseInt(request.getParameter("numStudents2"));
	float pFemale1 = Float.parseFloat(request.getParameter("perFemale1"));
	float pFemale2 = Float.parseFloat(request.getParameter("perFemale2"));
	int satv1 = Integer.parseInt(request.getParameter("satv1"));
	
	searchUniversity(String schoolName, String state, String location, String control,
			int numStudents1, int numStudents2, float pFemale1, float pFemale2, int satv1, int satv2, int satm1,int satm2, int exp1,
			int exp2,float pFinAid1, float pFinAid2, int numApps1, int numApps2, float pAdmitted1, float pAdmitted2,
			float pEnrolled1, float pEnrolled2,int academicScale1, int academicScale2, int socialScale1, int socialScale2,
			int qualLife1, int qualLife2, List<String> emphases)
	%>
	<a href="userHome.jsp">Home</a><br>
	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a><br>
	<% session.setAttribute("from", "searchResults.jsp"); %>
</body>
</html>