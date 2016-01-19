<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="CustomerUpdateValidate.js">

</script>
<title>Search</title>
</head>
<body background="registration.jpg">
<center>
<%@include file="header11.html"%>
<br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>
<%
String message="";
if(request.getAttribute("message")!=null)
	message=request.getAttribute("message").toString();

%>

<h1>Update Customer Details</h1>
<form name="check" style="text-align: center" onsubmit="return validateForm();" method="post" action="./CustomerUpdateController">
<span id="message" style="color:red"></span>
<span style="color: Red"><%=message %></span>
<table align="center">
	<tr>
		<td> Customer ID</td><td><input type="text" name="cid"  /></td>
	</tr>
	<tr>		
		<td></td><td><input type="submit" face="arial" value="Search" name="Search"/></td>
	</tr>
	</table>
	
</form>
</center>

</body>
</html>