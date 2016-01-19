function validateForm()
{	
	var num1=/^\d{10}$/;
	var num2=/^\d{16}$/;
	var num3=/^\d{3,5}$/;
	var email=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+com+$/;
	var letters = /^[A-Za-z]+$/; 
	var a=document.forms["customerRegistartion"]["cname"].value;
	var b=document.forms["customerRegistartion"]["address"].value;
	var c=document.forms["customerRegistartion"]["phno"].value;
	var d=document.forms["customerRegistartion"]["mydropdown1"].value;	
	var e=document.forms["customerRegistartion"]["mydropdown2"].value;
	var f=document.forms["customerRegistartion"]["eid"].value;
	var g=document.forms["customerRegistartion"]["iddoc"].value;
	var h=document.forms["customerRegistartion"]["idno"].value;
	var i=document.forms["customerRegistartion"]["regdate"].value;
	var j=document.forms["customerRegistartion"]["electricity"].value;
	var k=document.forms["customerRegistartion"]["telephone"].value;
	var l=document.forms["customerRegistartion"]["insurance"].value;
	var m=document.forms["customerRegistartion"]["tax"].value;
	var n=document.forms["customerRegistartion"]["cardno"].value;
	var o=document.forms["customerRegistartion"]["balance"].value;
	if (a==null || a=="")
	{
		document.getElementById("message").innerHTML="Customer name must be filled out";		
		return false;
	}	
	if((!letters.test(a)) && (!isNaN(a))
)
	{
		document.getElementById("message").innerHTML="Customer name should have alphabets only";
		return false;
	}
	if (b==null || b=="")
	{
		document.getElementById("message").innerHTML="Customer Address must be filled out";		
		return false;
	}
	if(c==null || c=="")
	{
		document.getElementById("message").innerHTML="Contact number must be filled out";		
		return false;
	}
	if(!num1.test(c))
	{
		document.getElementById("message").innerHTML="Contact number should be 10 digit number";
		return false;
	}	
	if(d==null || d=="")
	{
		document.getElementById("message").innerHTML="Country must be filled out";		
		return false;
	}	
	if(e==null || e=="")
	{
		document.getElementById("message").innerHTML="State must be filled out";	
		return false;
	}
	if(f==null || f=="")
	{
		document.getElementById("message").innerHTML="Email ID must be filled out";	
		return false;
	}
	if(!email.test(f))	
	{
		document.getElementById("message").innerHTML="Email ID should be in the following format 'abc@xyz.com' ";
		return false;
	}
	if(g==null || g=="")
	{
		document.getElementById("message").innerHTML="Identification Document must be filled out";		
		return false;
	}
	if(h==null || h=="")
	{
		document.getElementById("message").innerHTML="Identification Document number must be filled out";		
		return false;
	}
	if(i==null || i=="")
	{
		document.getElementById("message").innerHTML="registration Date must be filled out";		
		return false;
	}
	if((j==null || j=="")&&(k==null || k=="")&&(l==null || l=="")&&(m==null || m==""))
	{
		document.getElementById("message").innerHTML="Vendor type must be filled out";
		return false;
	}	
	if((j==null || j=="")&&(k==null || k=="")&&(l==null || l=="")&&(m==null || m==""))
	{
		document.getElementById("message").innerHTML="Vendor type must be filled out";
		return false;
	}
	if(n==null || n=="")
	{
		document.getElementById("message").innerHTML="Card Number must be filled out";
		return false;
	}
	if(!num2.test(n))
	{
		document.getElementById("message").innerHTML="Card number should be 16 digit number";
		return false;
	}		
	if(o==null || o=="")
	{
		document.getElementById("message").innerHTML="Balance must be filled out";
		return false;
	}
	if(!num3.test(o))
	{
		document.getElementById("message").innerHTML="Balance must be between 100-99999";
		return false;
	}
	
}