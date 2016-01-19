<%@page import="com.bps.util.DbUtil"%>
<%@page import="java.sql.*"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
 Connection connection = DbUtil.getConnection();
 Statement statement = connection.createStatement();
 String query1 = "select unique(vendor_name) from rns_vendor_company_details where vendor_type="+"'"+request.getParameter("vendorType")+"'";
 String query2 = "select amount from rns_vendor_amount where vendor_Type="+"'"+request.getParameter("vendorType")+"'";
 Statement statement2 = connection.createStatement();;
 ResultSet resultSet1 = statement.executeQuery(query1);
 ResultSet resultSet2 = statement2.executeQuery(query2);
 
 String str=null;

 while(resultSet1.next()){
	 str = resultSet1.getString(1);
 }
 
 while(resultSet2.next()){
	 str = str+","+resultSet2.getDouble(1);
  }

 out.write(str);
 resultSet1.close();
 resultSet2.close();
 statement.close();
 statement2.close();
 connection.close();
 %>   
