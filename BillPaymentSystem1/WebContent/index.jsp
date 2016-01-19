  <%@page import="com.bps.util.DbUtil"%>
<%@page import="java.sql.*"%> 
  <%@page import="com.bps.dao.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
  Date date=new Date();
  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
  String payment_Date = format.format(date);
 %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Payment</title>
<script>
function validate(){
	
	if( document.forms["billpayment"]["CustomerID"].value == "" || 
	    document.forms["billpayment"]["VendorType"].value == "" ||
	    document.forms["billpayment"]["VendorName"].value == "" ||
	    document.forms["billpayment"]["PendingAmount"].value == "" ||
	    document.forms["billpayment"]["PaymentDate"].value == "" ||
	   
	    document.forms["billpayment"]["AmountToPay"].value == "" || 
	    document.forms["billpayment"]["CreditCardType"].value ==  "" || 
	    document.forms["billpayment"]["CardNumber"].value == "" ||
	    document.forms["billpayment"]["ConfirmCardNumber"].value == "" ||
	    document.forms["billpayment"]["cvv"].value == ""
	    
	){
		alert("One or more fields are empty...");
		return false;		
	}
	else
		return true;
}

var monthtext=['01','02','03','04','05','06','07','08','09','10','11','12'];

function populatedropdown( monthfield, yearfield){
var today=new Date();

var monthfield=document.getElementById(monthfield);
var yearfield=document.getElementById(yearfield);

for (var m=0; m<12; m++)
monthfield.options[m]=new Option(monthtext[m], monthtext[m]);
monthfield.options[today.getMonth()]=new Option(monthtext[today.getMonth()], monthtext[today.getMonth()], true, true) ;

var thisyear=today.getFullYear();
for (var y=0; y<20; y++){
yearfield.options[y]=new Option(thisyear, thisyear);
thisyear+=1;
}
yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true);

}

</script>

</head>
  <% String message1="";
if(request.getAttribute("message1")!=null)
{
	message1=request.getAttribute("message1").toString();
}

%>


<%@include file="header13.html"%>
<br><br><br><br><br><br>
<br><a href="homepage.jsp"> <span style="float:left">Home</span></a>
<a href="logout.jsp"> <span style="float:right">Logout</span></a><br><br><br><br><br><br>
<body style="text-align: center;" background="registration.jpg">

<h1>Bill Payment Details</h1>


<h1></h1>
<form name="billpayment" action="./BillPaymentController"  method="post" onsubmit="return validate()" >
 <span style="color: Red"><%=message1 %></span>
<table align="center" style="text-align: left" border=1>
<tr></tr><tr></tr><tr></tr>
<tr><td>Customer ID</td><td><input type ="text" name="CustomerID"></td></tr>

<tr><td>Vendor type :</td><td>
<select name="Vendortype" onChange="location.href='index.jsp?option='+this.value">
<%
	VendorTypeBill vendorType = new VendorTypeBill(); 
for (String s: vendorType.getVendorType()) 
{ 
if(request.getParameter("option") != null && request.getParameter("option").equals(s)) 
out.println("<option value="+s+" selected=\"selected\">"+s+"</option>"); 
else
out.println("<option value="+s+">"+s+"</option>"); 
}
%>
</select>
</td></tr>
<tr><td>Vendor Name :</td><td>

<select name="VendorName" >
<%
	VendorNameBill vendorName = new VendorNameBill(request.getParameter("option")); 
for(String s : vendorName.getVendorName()) 
out.println("<option value="+s+">"+s+"</option>");
%>
</select>
</td></tr>

<% 
PendingAmount pendingAmount = new PendingAmount(request.getParameter("option")); 
%>

<tr><td>Pending Amount:</td><td><input type ="text" 
 value= <%=pendingAmount.getPendingAmount()%> name="PendingAmount" ></td></tr>
<tr><td>Payment Date:</td><td><input type ="text" 
 value= <%=payment_Date%> name="PaymentDate" ></td></tr>
<tr><td>Amount to pay</td><td><input type ="text" name="AmountToPay"></td></tr>
<tr><td>Credit card type :</td><td>

<select name="CreditCardType">

  <option value="P2"> Debit Card</option>
  <option value="P3"> Credit Card</option>

</select>

</td></tr>
<tr><td>Card number</td><td><input type ="text" name="CardNumber"></td></tr>
<tr><td>Confirm Card number</td><td><input type ="text" name="ConfirmCardNumber"></td></tr>

<tr><td>Confirm Card validity:</td><td><select name="MM"  id="monthdropdown">
</select> 
<select name="yyyy" id="yeardropdown">
</select> 

<script type="text/javascript">

//populatedropdown(id_of_day_select, id_of_month_select, id_of_year_select)
window.onload=function(){
populatedropdown("monthdropdown", "yeardropdown");
}
</script>

<tr><td>CVV</td><td><input type ="text" name="cvv"></td></tr>
<td  align="left" ><input type="checkbox" >I Agree</td></tr>
<tr ><td align="center"><input type="submit" value="Submit"></td>
<td  align="center" ><input type="reset" value="Reset"></td></tr>


</table>
</form>
</body>
</html>