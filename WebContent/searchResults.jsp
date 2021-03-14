<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.cmc.model.*, com.cmc.database.DBInteractions, com.cmc.controller.*, java.util.*"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>School Results</title>
</head>
<body>
	<% 
	SearchController controller = SearchController.getInstance();
	
	String schoolName = request.getParameter("schoolName");
	
	System.out.println(schoolName);
	
	String state = request.getParameter("state");
	
	String location = request.getParameter("location");
	
	String control = request.getParameter("control");
	
	String numStudents1req = request.getParameter("numStudents1");
	int numStudents1 = numStudents1req.equals("") ? -1: Integer.parseInt(numStudents1req);
	
	System.out.println(numStudents1);
	
	String numStudents2req = request.getParameter("numStudents2");
	int numStudents2 = numStudents1req.equals("") ? -1: Integer.parseInt(numStudents2req);
	
	String pFemale1req = request.getParameter("perFemale1");
	float pFemale1 = numStudents1req.equals("") ? -1: Float.parseFloat(pFemale1req);
	
	String pFemale2req = request.getParameter("perFemale2");
	float pFemale2 = numStudents1req.equals("") ? -1: Float.parseFloat(pFemale2req);
	
	String satv1req = request.getParameter("satv1");
	int satv1 = numStudents1req.equals("") ? -1: Integer.parseInt(satv1req);
	
	String satv2req = request.getParameter("satv2");
	int satv2 = numStudents1req.equals("") ? -1: Integer.parseInt(satv2req);
	
	String satm1req = request.getParameter("satm1");
	int satm1 = numStudents1req.equals("") ? -1: Integer.parseInt(satm1req);
	
	String satm2req = request.getParameter("satm2");
	int satm2 = numStudents1req.equals("") ? -1: Integer.parseInt(satm2req);
	
	String exp1req = request.getParameter("exp1");
	int exp1 = numStudents1req.equals("") ? -1: Integer.parseInt(exp1req);
	
	String exp2req = request.getParameter("exp2");
	int exp2 = numStudents1req.equals("") ? -1: Integer.parseInt(exp2req);
	
	String pFinAid1req = request.getParameter("perFinAid1");
	float pFinAid1 = numStudents1req.equals("") ? -1: Float.parseFloat(pFinAid1req);
	
	String pFinAid2req = request.getParameter("perFinAid2");
	float pFinAid2 = numStudents1req.equals("") ? -1: Float.parseFloat(pFinAid2req);
	
	String pEnrolled1req = request.getParameter("perEnrolled1");
	float pEnrolled1 = numStudents1req.equals("") ? -1: Float.parseFloat(pEnrolled1req);
	
	String pEnrolled2req = request.getParameter("perEnrolled2");
	float pEnrolled2 = numStudents1req.equals("") ? -1: Float.parseFloat(pEnrolled2req);
	
	String numApps1req = request.getParameter("numApp1");
	int numApps1 = numStudents1req.equals("") ? -1: Integer.parseInt(numApps1req);
	
	String numApps2req = request.getParameter("numApp2");
	int numApps2 = numStudents1req.equals("") ? -1: Integer.parseInt(numApps2req);
	
	String pAdmitted1req = request.getParameter("perAdmitted1");
	float pAdmitted1 = numStudents1req.equals("") ? -1: Float.parseFloat(pAdmitted1req);
	
	String pAdmitted2req = request.getParameter("pAdmitted2");
	float pAdmitted2 = numStudents1req.equals("") ? -1: Float.parseFloat(pAdmitted2req);
	
	String academicScale1req = request.getParameter("acadScale1");
	int academicScale1 = numStudents1req.equals("") ? -1: Integer.parseInt(academicScale1req);
	
	String academicScale2req = request.getParameter("acadScale1");
	int academicScale2 = numStudents1req.equals("") ? -1: Integer.parseInt(academicScale2req);
	
	String socialScale1req = request.getParameter("socScale1");
	int socialScale1 = numStudents1req.equals("") ? -1: Integer.parseInt(socialScale1req);
	
	String socialScale2req = request.getParameter("socScale2");
	int socialScale2 = numStudents1req.equals("") ? -1: Integer.parseInt(socialScale2req);
	
	String qualLife1req = request.getParameter("qualLife1");
	int qualLife1 = numStudents1req.equals("") ? -1: Integer.parseInt(qualLife1req);
	
	String qualLife2req = request.getParameter("qualLife2");
	int qualLife2 = numStudents1req.equals("") ? -1: Integer.parseInt(qualLife2req);
	
	List<String> emphases = new ArrayList<>();
	emphases.add(request.getParameter("emphasis1"));
	emphases.add(request.getParameter("emphasis2"));
	emphases.add(request.getParameter("emphasis3"));
	emphases.add(request.getParameter("emphasis4"));
	emphases.add(request.getParameter("emphasis5"));
	
	List<University> unis = controller.searchUniversity(schoolName, state, location, control, numStudents1, numStudents2, pFemale1, pFemale2, satv1, satv2, satm1,satm2, exp1,
			exp2, pFinAid1, pFinAid2, numApps1, numApps2, pAdmitted1, pAdmitted2, pEnrolled1, pEnrolled2,academicScale1, academicScale2,  socialScale1, socialScale2,
			qualLife1, qualLife2, emphases);
	System.out.println(unis);
	%>
	<table><th></th>
	<%
	for (University uni: unis) {
%>
	<tr>
		<td>
			<%= uni.getName()%>
		</td>
		<td>
			<a href="viewUniversity.jsp?viewing=<%=uni.getName()%>">View</a>
		</td>
	</tr>	
<%
	} %>
	</table>
	<a href="userHome.jsp">Home</a><br>
	<a href="<%=(String)session.getAttribute("from") == null ? "index.jsp":  (String)session.getAttribute("from")%>">Go Back!</a><br>
	<% session.setAttribute("from", "searchResults.jsp"); %>
</body>
</html>