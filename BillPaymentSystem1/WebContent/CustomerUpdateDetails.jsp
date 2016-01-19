<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*" import="com.bps.to.CustomerUpdateTO"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="CustomerUpdateValidate.js">

</script>
<title>Display page</title>
</head>
<body background="registration.jpg" align="center">



<%@include file="header11.html"%>
<br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>
 <center>

 <br><br>Welcome To User ${result.customername}
 
<form name=check style="text-align: center" onsubmit="return validateForm1();" method="post" action="./CustomerUpdate">
 <span id="message" style="color:red"></span>
<table align="center" border="1" style="text-align: left;">
<tr>
	<td>Customer ID</td><td><input type="text" name="customer_id" id="customer_id" value="${result.customerid }" style="background-color: lightgrey" READONLY/></td>
	<td>Customer Name</td><td><input type="text" name="customer_name" id="customer_name" value="${result.customername }" style="background-color: lightgrey" READONLY/></td>
</tr>


<tr>
	<td>Country</td><td><input type="text" name="customer_country" id="customer_country" value="${result.customercountry }" style="background-color: lightgrey" READONLY/></td>
	<td>State</td><td><input type="text" name="customer_state" id="customer_state" value="${result.customerstate }" style="background-color: lightgrey" READONLY/></td>
</tr>
<tr>
	<td>Contact Number</td><td><input type="text" name="contact_no" id="contact_no" value="${result.contactno }"/></td>
	<td>Email ID</td><td><input type="text" name="mail_id" id="mail_id" value="${result.mailid }"/></td>
</tr>
<tr>
	<td>Card Number</td><td><input type="text" name="card_no" id="card_no" value="${result.cardno }"/></td>
	<td>Balance</td><td><input type="text"  name="balance" id="balance" value="${result.balance }"/></td>
</tr>
<tr>
	<td>Type Of Vendor</td>
	<td>
		 <c:choose>
		 <c:when test="${result.electricity=='null'}">
		 	<input type="checkbox" name="typeofvendor" id="electricity" value="electricity" />Electricity<br>
		 </c:when>
		 <c:otherwise>
		 	<input type="checkbox" name="typeofvendor" id="electricity" value="electricity" checked="checked" />Electricity<br>
		 </c:otherwise>
		 </c:choose>
		 
		 <c:choose>
		 <c:when test="${result.telephone=='null'}">
		 	<input type="checkbox" name="typeofvendor" id="telephone" value="telephone"/>Telephone<br>
		 </c:when>
		 <c:otherwise>
		 	<input type="checkbox" name="typeofvendor" id="telephone" value="telephone" checked="checked"/>Telephone<br>
		 </c:otherwise>
		 </c:choose>
		 <c:choose>
		 <c:when test="${result.insurance=='null'}">
		 	<input type="checkbox" name="typeofvendor" id="insurance" value="insurance" />Insurance<br>
		 </c:when>
		 <c:otherwise>
		 	<input type="checkbox" name="typeofvendor" id="insurance" value="insurance" checked="checked" />Insurance<br>
		 </c:otherwise>
		  </c:choose>
		  <c:choose>
		 <c:when test="${result.tax=='null'}">
		 	<input type="checkbox" name="typeofvendor" id="tax" value="tax"/>Tax<br>
		 </c:when>
		 <c:otherwise>
		 	<input type="checkbox" name="typeofvendor" id="tax" value="tax" checked="checked"/>Tax<br>
		 </c:otherwise>
		 </c:choose>
		</td> 				
	
	<td>Address</td><td><input type="text" name="customer_address" id="customer_address" value="${result.customeraddress }"/></td>
	
</tr>

<tr>
	
</tr>


</table>
<table align="center">
<tr><td><input type="submit" id="submit" Value="Update"/></td></tr>
</table>

</form>

</center>
</body>
</html>