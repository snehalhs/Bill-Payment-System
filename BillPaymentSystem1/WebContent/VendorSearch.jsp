<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function ValidateForm1()
{
	var a=document.forms["check"]["vendor_id"].value;	
	if (a==null || a=="")
	{
		document.getElementById("message").innerHTML="Vendor Id must be filled out";		
		return false;
	}
}
</script>
<title>Search</title>
</head>
<body background="registration.jpg">

<%@include file="header17.html"%>
<br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>
<%
String message="";
if(request.getAttribute("message")!=null)
	message=request.getAttribute("message").toString();

%>
<h1 style="text-align: center;"> Vendor Search</h1>
<form name="check" style="text-align: center" onsubmit="return validateForm1();" method="post" action="./VendorUpdateController">
<span id="message" style="color:red"></span>
<span style="color: Red"><%=message %></span>
<table align="center">
	<tr>
		<td> Vendor ID</td><td><input type="text" name="vendor_id"  /></td>
	</tr>
	<tr>		
		<td></td><td><input type="submit" face="arial" value="Search" name="Search"/></td>
	</tr>
	</table>
	
</form>
      
</body>
</html>