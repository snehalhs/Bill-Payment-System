<%@page import="java.text.SimpleDateFormat"%>
 <%@page import="com.bps.dao.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en"><head> 
 
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"> 
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> 
<link rel="stylesheet" href="/resources/demos/style.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function dateadd(str)
{

		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if(xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  if(!xmlhttp.responseText.match("false")) {
				  
				  var validityDate= xmlhttp.responseText.replace(/^\s+|\s+$/g,'');
				  document.forms["vendor registration"]["certificate_valid_date"].value= validityDate;

			  }
			  else{
				  document.getElementById("message").innerHTML="Issue date cannot be greater than today's date";
			  }
			}
		  };
		xmlhttp.open("GET","getValidityDate.jsp?issueDate="+str,true);
		xmlhttp.send();

	}
function validateForm()
{   
	
	var email=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var num1=/^\d{10}$/;
	var web= /^w{3}\.([a-zA-Z])+\.com$/;
	var a=document.forms["vendor registration"]["VendorName"].value;
	var b=document.forms["vendor registration"]["VendorType"].value;
	var c=document.forms["vendor registration"]["address"].value;
	var d=document.forms["vendor registration"]["email_id"].value;
	var e=document.forms["vendor registration"]["contact_number"].value;
	var f=document.forms["vendor registration"]["web_site"].value;
	var g=document.forms["vendor registration"]["certificate_issue_date"].value;
	var h=document.forms["vendor registration"]["employee_count"].value;
	var i=document.forms["vendor registration"]["customer_count"].value;
	var j=document.forms["vendor registration"]["yoe"].value;

	if(a==null || a=="")
	{
		document.getElementById("message").innerHTML="vendor name must be filled out";
		return false;
	}
	if(b==null || b=="")
	{
		document.getElementById("message").innerHTML="vendor type must be filled out";
		return false;
	}
	if(c==null || c=="")
	{
		document.getElementById("message").innerHTML="address must be filled out";
		return false;
	}
	if(d==null || d=="")
	{
		document.getElementById("message").innerHTML="email id must be filled out";
		return false;
	}
	if(!email.test(d) )	
	{
		document.getElementById("message").innerHTML="Email id should be in the following format 'abc@xyz.com' ";
		return false;
	}
	if(e==null || e=="")
	{
		document.getElementById("message").innerHTML="contact number must be filled out";
		return false;
	}
	if(!num1.test(e))
	{
		document.getElementById("message").innerHTML="Contact number should be 10 digit number";
		return false;
	}	

	if(f==null || f=="")
	{
		document.getElementById("message").innerHTML="web site must be filled out";
		return false;
	}
	 if(!web.test(f))
		{
		document.getElementById("message").innerHTML="website must be in the format _www.abc.com";
		return false;
		}

	if(g==null || g=="")
	{
		document.getElementById("message").innerHTML="certificate issue date must be filled out";
		return false;
	}
	if(h==null || h=="")
	{
		document.getElementById("message").innerHTML="employee count must be filled out";
		return false;
	}
	if(i==null || i=="")
	{
		document.getElementById("message").innerHTML="customer count must be filled out";
		return false;
	}
	
	if(j=="select")
	{
		document.getElementById("message").innerHTML="yoe must be filled out";
		return false;
	}
}


function hell(str)
{	
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if(xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var responseArray= xmlhttp.responseText.split(',');
		
		  document.forms["vendor registration"]["VendorName"].value= responseArray[0];
		  document.forms["vendor registration"]["VendorType"].value= responseArray[1];
		 //document.forms["vendor registration"]["vendor_type"].value= responseArray[2];
	    }
	  };
	xmlhttp.open("GET","GetVendorDetails.jsp?VendorReg="+str,true);
	xmlhttp.send();
}
</script>
<title>Vendor Registration</title>
</head>
<% String message="";
if(request.getAttribute("message")!=null)
{
	message=request.getAttribute("message").toString();
}

%>
<body style="text-align: center;" background="registration.jpg">

<%@include file="header18.html"%>

<br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>

<h1 style="text-align: center;"> Vendor Registration</h1>
<span style="color: Red"><%=message %></span>
<form name="vendor registration" id="vendor registration" style="text-align: center;" onsubmit="return validateForm();" method="post" action="./VendorRegistrationController">
<span id="message" style="color:red"></span>
<table  align="center" border="3" style="text-align: left;">


<tr>
		<td><tr><td>Vendor Reg :</td><td>
<select name="VendorReg" id="VendorReg" onChange="hell(this.value)">
<%
VendorRegNo vendorRegNo = new VendorRegNo (); 
for (String s: vendorRegNo.getVendorRegNo()) 
{ 
if(request.getParameter("option") != null && request.getParameter("option").equals(s)) 
out.println("<option value="+s+" selected=\"selected\">"+s+"</option>"); 
else
out.println("<option value="+s+">"+s+"</option>"); 
} 
%>
</select></td></tr>
<tr><td>Vendor Name</td><td><input type ="text" id="VendorName" name="VendorName"></td></tr>

<tr><td>Vendor Type</td><td><input type ="text" name="VendorType" id="VendorType"></td></tr>

	<tr>
		<td>Address</td><td><input type="text" name="address" id="address" />
		</td>
	</tr>
	<tr><td>
Country</td><td>
<select name="mydropdown1" onChange="location.href='VendorRegister.jsp?option='+this.value">
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
</tr>
<tr><td>
State</td><td>
<select name="mydropdown2" >
<%
States cityBean = new States(request.getParameter("option")); 
for(String s : cityBean.getCities()) 
out.println("<option value="+s+">"+s+"</option>"); 
%>
</select>

		</td>
		</tr>
		
	<tr>
		<td>Email Address</td><td><input type="text" name="email_id" id="email_id" />
		</td>
	</tr>
	<tr>
		<td>Contact Number</td><td><input type="text" name="contact_number" id="contact_number" />
		</td>
	</tr>
	
	
	<tr>
		<td>Web Site</td><td><input type="text" name="web_site" id="web_site" />
		</td>
	</tr>
	<tr><td>Certificate issue date</td><td><input type="text" name="certificate_issue_date" id="datepicker2" onchange=dateadd(this.value)>
</td></tr> 
 
<script> $(function() { $( "#datepicker2" ).datepicker(); });
 </script>

<tr><td>Certificate valid date</td><td><input  type="text" name="certificate_valid_date" id="certificate_valid_date">
</td></tr> 

	<tr>
		<td>Employee Count</td><td><input type="text" name="employee_count" id="employee_count" />
		</td>
	</tr>
	<tr>
		<td>Customer Count</td><td><input type="text" name="customer_count" id="customer_count" />
		</td>
	</tr>
	<tr>
		<td>Year Of Establishment</td><td><select name="yoe" id="yoe">
		<option selected="selected" value="select">--Select--</option>
		<%for(int i=1901;i<=2014;i++)
		 out.println("<option value="+i+">"+i+"</option>");
		%>
			</select> 
			 
		</td>
	</tr>
	
	<tr>
	    <td><input type="submit" value="Register"/></td>
        <td><input type="reset" value="Reset"/></td>
        </tr>
        </table>
        </form>
</body>
</html>