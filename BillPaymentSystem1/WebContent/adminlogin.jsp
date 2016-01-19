<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
</head>
<% String message1="";
if(request.getAttribute("message1")!=null)
{
	message1=request.getAttribute("message1").toString();
}

%>
<body background="bg1.jpg" >
<div id="Bheader" style="height:20%;">
<%@include file="Bheader.html"%>
</div>
<IMG STYLE="position:absolute; TOP:225px; LEFT:65px; WIDTH:200; HEIGHT:150px" SRC="admin.bmp">
<center>
<br><br><br><br><br><br><br> Login here
<form action="AdminLoginController" method="post">
<%=message1 %>
<table>
<tr>
	<td style="color: black">
		AdminName
	</td>
	<td>
		<input type="text" name="AdminName"/>
	</td>
	</tr>
	<tr>
	<td style="color: black">
		Password
	</td>
	<td>
		<input type="password" name="password"/>
	</td>
</tr>
</table><br>
<input type="submit" name=submit" value="submit"/>
</form>
<br>

</center>
</body>
<div
      style="height:10%;bottom:0%;position:relative;"><%@include file="Bfooter.html" %>
</div>
</html>