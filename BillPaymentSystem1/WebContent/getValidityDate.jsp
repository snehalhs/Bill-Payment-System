 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.ParseException"%>     
<% 
      	
        String dateFormat="MM/dd/yyyy";
        SimpleDateFormat fmt= new SimpleDateFormat(dateFormat);
		Date issueDate=null;
		Calendar calendar1=Calendar.getInstance();
		try {
			issueDate = fmt.parse(request.getParameter("issueDate"));
		} catch (ParseException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar1.setTime(issueDate);
		Date curDate= new Date();
		String sCurDate= fmt.format(curDate);
		curDate= fmt.parse(sCurDate);		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(curDate);
		String validityDate=null;
		if((calendar1.getTimeInMillis()-calendar2.getTimeInMillis()) <= 0){
		calendar1.add(Calendar.YEAR, 15);
		validityDate= fmt.format(calendar1.getTime());
		}
		else{
			validityDate="false";
		}
		out.write(validityDate);
		%>
