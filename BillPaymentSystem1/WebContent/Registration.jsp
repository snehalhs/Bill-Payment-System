<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function f1()
{
var xmlhttp;
var a=f.vendor_reg_number.value;            
/* var b=f.upass.value; */
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
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("i1").innerHTML=xmlhttp.responseText;
    }
  }
/* xmlhttp.open("post","UpdateController?ename="+a+"&upass="+b,true); */
 xmlhttp.open("post","RegistrationController1?vendor_reg_number="+a,true);
 xmlhttp.send();
return false;
}
</script>
</head>
<script type="text/javascript">

function validateForm()
{   
	var x=true;
	var message="The following fields should not be empty";
	var a=document.forms["vendor registration"]["address"].value;
	var b=document.forms["vendor registration"]["country"].value;
	//var c=document.forms["vendor registration"]["state"].value;
	var d=document.forms["vendor registration"]["email_id"].value;
	var e=document.forms["vendor registration"]["contact_number"].value;
	var f=document.forms["vendor registration"]["web_site"].value;
	var g=document.forms["vendor registration"]["certificate_issue_date"].value;
	var h=document.forms["vendor registration"]["certificate_valid_date"].value;
	var i=document.forms["vendor registration"]["employee_count"].value;
	var j=document.forms["vendor registration"]["customer_count"].value;
	var k=document.forms["vendor registration"]["yoe"].value;

if(a==null || a=="")
	{
		message=message+"\nAddress";
		x=false;
	}
if(b=="select")
{
	message=message+"\nCountry";
	x=false;
}
/* if(c==null || c=="")
{
	alert("contact number must be filled out");
	return false;
} */
if(d==null || d=="")
{
	message=message+"\nEmail id";
	x=false;
}
if(e==null || e=="")
{
	message=message+"\nContact number";
	x=false;
}
if(f==null || f=="")
{
	message=message+"\nWeb site";
	x=false;
}
if(g==null || g=="")
{
	message=message+"\nCerti issue date";
	x=false;
}
if(h==null || h=="")
{
	message=message+"\nCerti valid date";
	x=false;
}
if(i==null || i=="")
{
	message=message+"\nEmployee count";
	x=false;
}
if(j==null || j=="")
{
	message=message+"\nCustomer count";
	x=false;
}
if(k==null || k=="")
{
	message=message+"\nYOE";
	x=false;
}
if(x==false)
	{
	alert(message);
	}
}
function enable(){
	$(function() { $( "#certificate_issue_date" ).datepicker(); });
	$(function() { $( "#certificate_valid_date" ).datepicker(); });
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
<body background="registration.jpg">


<br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>

<form name=f method="post" onsubmit="validateForm();" >
Vendor Reg Number<input type="text" name="vendor_reg_number" onchange="hell(this.value)"/>
<br><br>
<input type="submit" value="submit" onclick="return f1()" >
</form>
<div id="i1">
</div>

</body>
</html>