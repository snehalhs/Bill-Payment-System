<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout page</title>
<SCRIPT type="text/javascript">    
window.history.forward();    
function noBack() 
{ window.history.forward(); 
}
</SCRIPT> 
</head>
<%
session.setAttribute("AdminName", null); 

session.invalidate(); 


%>

<body background="bg1.jpg" > 

<%@include file="header14.html"%>
<br><br><br><br><br><br><br><br><br><br><br><br><br>
<center>you are successfully logged out</center>
<br><a href="adminlogin.jsp"> <span style="float:center" ><font size="200"><center>Log In Here<center></center></font> 

</span></a><br><br><br><br><br><br>
</body>
</html>