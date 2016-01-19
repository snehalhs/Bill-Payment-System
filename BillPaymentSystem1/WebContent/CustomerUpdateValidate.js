function validateForm()
{		
	var num=/^\d{3}$/;
	var a=document.forms["check"]["cid"].value;	
	if (a==null || a=="")
	{
		document.getElementById("message").innerHTML="Customer Id must be filled out";		
		return false;
	}
	if(!num.test(a))
	{
		document.getElementById("message").innerHTML="Customer Id must be 3 digit Numeric value";
		return false;
	}
}

function validateForm1()
{	
	var num1=/^\d{10}$/;
	var num2=/^\d{16}$/;
	var num3=/^\d{3,5}$/;
	var email=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var a=document.forms["check"]["contact_no"].value;
	var b=document.forms["check"]["mail_id"].value;
	var d=document.forms["check"]["card_no"].value;
	var e=document.forms["check"]["balance"].value;	
	var f=document.forms["check"]["electricity"].value;
	var g=document.forms["check"]["telephone"].value;
	var h=document.forms["check"]["insurance"].value;
	var i=document.forms["check"]["tax"].value;
	var c=document.forms["check"]["customer_address"].value;
	if (a==null || a=="")
	{
		document.getElementById("message").innerHTML="Contact number must be filled out";		
		return false;
	}
	if(!num1.test(a))
	{
		document.getElementById("message").innerHTML="Contact number should be 10 digit number";
		return false;
	}	
	if (b==null || b=="")
	{
		document.getElementById("message").innerHTML="Email id must be filled out";		
		return false;
	}
	if(!email.test(b))	
	{
		document.getElementById("message").innerHTML="Email id should be in the following format 'abc@xyz.com' ";
		return false;
	}
	if(d==null || d=="")
	{
		document.getElementById("message").innerHTML="Card number must be filled out";		
		return false;
	}
	if(!num2.test(d))
	{
		document.getElementById("message").innerHTML="Card number should be 16 digit number";
		return false;
	}		
	if(e==null || e=="")
	{
		document.getElementById("message").innerHTML="Balance must be filled out";	
		return false;
	}
	if(!num3.test(e))
	{
		document.getElementById("message").innerHTML="Balance must be between 100-99999";
		return false;
	}	
	if(c==null || c=="")
	{
		document.getElementById("message").innerHTML="Customer address must be filled out";		
		return false;
	}
	if((f==null || f=="")(g==null || g=="")(h==null || h=="")(i==null || i==""))
	{
		alert("Control in java script if blcok");
		document.getElementById("message").innerHTML="check box status";	
		return false;
	}	
	
}