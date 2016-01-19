  <%@page import="com.bps.util.DbUtil"%>
  <%@page import="java.sql.*"%> 
  <%@page import="com.bps.dao.*"%>
<%@page import="java.util.Date"%>
<%@page import="com.bps.to.UserTo"%>
<%@page import="com.bps.dao.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head> <meta charset="utf-1"> 
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"> 
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> 
<link rel="stylesheet" href="/resources/demos/style.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<%
String message="";
if(request.getAttribute("message")!=null)
{
	message=request.getAttribute("message").toString();
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="CustomerValidation.js">
</script>
<title>DISPLAY</title>
</head>
<body background="registration.jpg">
<%@include file="header5.html"%>
<center><br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>
<h1 style="text-align: center;">Registration Form</h1>
<span style="color: Red"><%=message %></span>
<form name=customerRegistartion method="post" onsubmit="return validateForm();" action="./CustomerRegistrationController">
<span style="color: Red" id="message"></span>
<table align="center" border=1 style="text-align: left;">
<tr>
<td>Customer Name</td><td><input type="text" name="cname" /></td>
<td>Identification Document</td><td><select name="iddoc">
  <option>--Select--</option>
  <option value="voter_id">Voter Id</option>
  <option value="passport">Pass Port</option>
  <option value="driving_license">Driving License</option>
  <option value="pan_card">PAN Card</option>
</select> 
</td>
</tr>
<tr>
<td>
Country</td><td>
<select name="mydropdown1" onChange="location.href='CustomerRegistration.jsp?option='+this.value">
<option>--Select--</option>
<%
Countries countryBean = new Countries(); 
for (String s: countryBean.getCountries()) 
{ 
if(request.getParameter("option") != null && request.getParameter("option").equals(s)) 
out.println("<option value="+s+" selected=\"selected\">"+s+"</option>"); 
else
out.println("<option value="+s+">"+s+"</option>"); 
} 
%>
</select>
</td>
<td>ID Number</td><td><input type="text" name="idno"/></td>
</tr>
<tr>
<td>
State</td><td>
<select name="mydropdown2" >
<option>--Select--</option>
<%
States cityBean = new States(request.getParameter("option")); 
for(String s : cityBean.getCities()) 
out.println("<option value="+s+">"+s+"</option>"); 
%>
</select>
</td>
<td>Registration date</td><td><input type="text" name="regdate" id="datepicker"></td></tr> 
<script> $(function() { $( "#datepicker" ).datepicker(); });
</script>
</tr>

<tr>
<td>Address</td><td><input type="text" name="address"></td>
<td>Type Of Vendor</td><td>
				<input type="checkbox" name="typeofvendor" id="electricity" value="electricity" checked="checked"/>Electricity<br>
				<input type="checkbox" name="typeofvendor" id="telephone" value="telephone"/>Telephone<br>
				<input type="checkbox" name="typeofvendor" id="insurance" value="insurance" />Insurance<br>
				<input type="checkbox" name="typeofvendor" id="tax" value="tax"/>Tax
																</td>

</tr>
<tr>
<td>Contact Number</td><td><input type="text" name="phno"/></td>
<td>Card Number</td><td><input type="text" name="cardno"/></td>
</tr>
															
																											
<tr>
<td>Mail ID</td><td><input type="text" name="eid"/></td>
<td>Balance</td><td><input type="text" name="balance"/></td>
</tr>
</table>

<table>
<tr><td><input type="submit" id="submit" Value="Register"/></td><td><input type="reset" id="reset" value="Reset"/></td></tr>
</table>
</form>
</center>
</body>
</html>





