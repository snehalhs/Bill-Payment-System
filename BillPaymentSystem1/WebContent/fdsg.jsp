  <%@page import="com.bps.util.DbUtil"%>
<%@page import="java.sql.*"%> 
<%@page import="java.util.Date"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
  Date date=new Date();
  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
  String payment_Date = format.format(date);


  
  
  
  Connection connection = DbUtil.getConnection();
 Statement statement = connection.createStatement();
 String query = "select unique(vendor_Type) from rns_vendor_company_details";
 ResultSet resultSet = statement.executeQuery(query);
 if(resultSet == null)
	 out.println("null");
 %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Payment</title>
<script>
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
		  alert(xmlhttp.responseText);
		  document.forms["billpayment"]["VendorName"].value= responseArray[0];
		  document.forms["billpayment"]["PendingAmount"].value= responseArray[1];
	    }
	  };
	xmlhttp.open("GET","getVendorName.jsp?vendorType="+str,true);
	xmlhttp.send();
}

function validate(){
	
	if( document.forms["billpayment"]["CustomerID"].value == "" || 
	    document.forms["billpayment"]["VendorType"].value == "" ||
	    document.forms["billpayment"]["VendorName"].value == "" ||
	    document.forms["billpayment"]["PendingAmount"].value == "" ||
	    document.forms["billpayment"]["PendingDate"].value == "" ||
	   
	    document.forms["billpayment"]["AmountToPay"].value == "" || 
	    document.forms["billpayment"]["CreditCardType"].value ==  "" || 
	    document.forms["billpayment"]["CardNumber"].value == "" ||
	    document.forms["billpayment"]["ConfirmCardNumber"].value == "" ||
	    document.forms["billpayment"]["CreditCardValidity"].value == ""
	    
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
//var dayfield=document.getElementById(dayfield);
var monthfield=document.getElementById(monthfield);
var yearfield=document.getElementById(yearfield);
//for (var i=0; i<31; i++)
//dayfield.options[i]=new Option(i, i+1);
//dayfield.options[today.getDate()]=new Option(today.getDate(), today.getDate(), true, true); 
//select today's day
for (var m=0; m<12; m++)
monthfield.options[m]=new Option(monthtext[m], monthtext[m]);
monthfield.options[today.getMonth()]=new Option(monthtext[today.getMonth()], monthtext[today.getMonth()], true, true) ;
//select today's month
var thisyear=today.getFullYear();
for (var y=0; y<20; y++){
yearfield.options[y]=new Option(thisyear, thisyear);
thisyear+=1;
}
yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true);
//select today's year
}

</script>

</head>
  <% String message1="";
if(request.getAttribute("message1")!=null)
{
	message1=request.getAttribute("message1").toString();
}

%>
 
<body style="text-align: center">
<h1>Bill Payment Details</h1>


<h1></h1>
<form name="billpayment" action="./BillPaymentController"  method="post" onsubmit="return validate()" >
 <%=message1 %>
<table style="text-align: left" border=1>
<tr></tr><tr></tr><tr></tr>
<tr><td>Customer ID</td><td><input type ="text" name="CustomerID"></td></tr>
<tr><td>Vendor type :</td><td>

<select name="VendorType" id="VendorType" onchange="hell(this.value)">
<%while(resultSet.next()){
out.println("<option value=\""+resultSet.getString(1)+"\">"+resultSet.getString(1)+"</option>"); 
} %> 

</select>
</td></tr>
<tr><td>Vendor Name</td><td><input type ="text" id="VendorName" name="VendorName"></td></tr>

<tr><td>Pending Amount</td><td><input type ="text" name="PendingAmount"></td></tr>
<tr><td>Payment Date:</td><td><input type ="text"  value= <%=payment_Date%> name="PendingDate" ></td></tr>
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