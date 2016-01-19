<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display page</title>
</head>
<body style="text-align: center;"  background="registration.jpg">

<%@include file="header8.html"%>
<br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>
<h1>Payment Details</h1><br>
<h2>Customer Id : <%=request.getAttribute("id") %></h2>
Hi <%=request.getAttribute("id") %> ,your payment is done successfully.Thank you.
</body>
</html>