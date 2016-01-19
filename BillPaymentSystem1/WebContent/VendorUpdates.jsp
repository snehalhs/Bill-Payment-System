<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ page import="com.bps.dao.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% ArrayList<String> list= new ArrayList<String>();
   %>
<script type="text/javascript">
function getState()
{
	var country = document.getElementById("country");
	VendorUpdateDAO vdao = new VendorUpdateDAO() ;
	list = vdao.getState(country);
}

</script>
<script src = "VendorUpdateValidation.js">

</script>
<title>Vendor Update</title>
</head>
<body background="registration.jpg">
<%@include file="header15.html"%>

<br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>

<br><br><center>Welcome User ${result.vendorname}</center>

<form name="f1" style="text-align: center;" onsubmit="return validateForm();" method="post"  action="./VendorUpdateController1">
<span id="message" style="color:red"></span>
<table align="center" border="1" style="text-align: left;">
<tr>
	<td>Vendor ID</td><td><input type="text" name="vid" value="${result.vendorid}" style="background-color: lightgrey" READONLY/></td>
	<td>Country</td><td><select name="country" id="country" onChange="location.href='VendorUpdates.jsp?option='+this.value">
	<option selected="selected" value="${result.country}" >${result.country}</option>
	<%
			VendorUpdateDAO vdao = new VendorUpdateDAO() ;
			for(String s : vdao.getCountry())
			{ 
				if(request.getParameter("option") != null && request.getParameter("option").equals(s)) 
				out.println("<option value="+s+" selected=\"selected\">"+s+"</option>"); 
				else
				out.println("<option value="+s+">"+s+"</option>"); 
		    } 
	%>
	</select></td>
</tr>
<tr>
	<td>Company Registration Number</td><td><input type="text" name="RegNum" value="${result.vendorregnumber}" style="background-color: lightgrey" READONLY /></td>
	<td>State</td><td><select name="state" id="state ">
	<option selected="selected" value="${result.state}">${result.state}</option>
	<%
States cityBean = new States(request.getParameter("option")); 
for(String s : cityBean.getCities()) 
out.println("<option value="+s+">"+s+"</option>"); 
%></select></td>
</tr>
<tr>
	<td>Vendor Name</td><td><input type="text" name="Vendor Name" value="${result.vendorname}" style="background-color: lightgrey" READONLY/></td>
	<td>Address</td><td><input type="text" name="address" id="address" value="${result.address}"  /></td>
</tr>
<tr>
	<td>Vendor Type</td><td><input type="text" name="Vendor Type" value="${result.vendortype}" style="background-color: lightgrey" READONLY /></td>
	<td>Contact Number</td><td><input type="text" name="contact_number" id="contact_number" value="${result.contactnumber}" /></td>
</tr>
<tr>
	<td>Certificate</td><td><input type="text" name="certificate" value="${result.certificate}" style="background-color: lightgrey" READONLY/></td>
	<td>Email Address</td><td><input type="text" name="email_id" id="email_id" value="${result.emailid}" /></td>
</tr>
<tr>
	<td>Certificate Issued Date</td><td><input type="text" name="certificate_issue_date" value="${result.certificateissuedate}" style="background-color: lightgrey" READONLY/></td>
	<td>Web Site</td><td><input type="text" name="web_site" id="web_site" value="${result.website}" /></td>
</tr>
<tr>
	<td>Certificate Validity Date</td><td><input type="text" name="certificate_valid_date" value="${result.certificatevaliddate}" style="background-color: lightgrey" READONLY/></td>
	<td>Customer Count</td><td><input type="text" name="Custcount" id="Custcount" value="${result.customercount}"/></td>
</tr>
<tr>
	<td>Year of Establishment</td><td><input type="text" name="Year of Establishment" value="${result.yoe}" style="background-color: lightgrey" READONLY /></td>
	<td>Employee Count</td><td><input type="text" name="empcount" id="empcount" value="${result.employeecount}" style="background-color: lightgrey" READONLY /></td>
</tr>
<tr>
<td></td>
	<td><input type="submit" Value="Update"/></td>
<td></td><td><input type="reset" value="Reset"/></td>
</tr>
</table>
</form>
</body>
</html>