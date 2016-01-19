
function validateForm()
{
	var num1=/^\d{10}$/;
	var email=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var web= /^w{3}\.([a-zA-Z])+\.com$/;
	var c=document.forms["f1"]["address"].value;
	var d=document.forms["f1"]["contact_number"].value;
	var e=document.forms["f1"]["email_id"].value;
	var f=document.forms["f1"]["web_site"].value;
	var g=document.forms["f1"]["Custcount"].value;


if(c==null || c=="")
{
	document.getElementById("message").innerHTML="address must be filled out";
	return false;
}
if(d==null || d=="")
{
	document.getElementById("message").innerHTML="Contact number must be filled out";
	return false;
}
if(!num1.test(d))
{
	document.getElementById("message").innerHTML="Contact number should be 10 digit number";
	return false;
}	
if(e==null || e=="")
{
	document.getElementById("message").innerHTML="email id must be filled out";
	return false;
}
if(!email.test(e))	
{
	document.getElementById("message").innerHTML="Email id should be in the following format 'abc@xyz.com' ";
	return false;
}
if(f==null || f=="")
{
	document.getElementById("message").innerHTML="website must be filled out";
	return false;
}
if(!web.test(f))
	{
	document.getElementById("message").innerHTML="website must be in the format www.abc.com";
	return false;
	}
if(g==null || g=="")
{
	document.getElementById("message").innerHTML="Customer count must be filled out";
	return false;
}
}