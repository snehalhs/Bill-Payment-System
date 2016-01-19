<%@page import="com.bps.util.DbUtil"%>
<%@page import="java.sql.*"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
 Connection connection = DbUtil.getConnection();
 String query3 = "select vendor_name, vendor_type from bps_vendor_summ where reg_number="+"'"+request.getParameter("VendorReg")+"'";
 Statement statement2 = connection.createStatement();;
 //ResultSet resultSet1 = statement.executeQuery(query1);
 // ResultSet resultSet2 = statement2.executeQuery(query2);
 ResultSet resultSet3 = statement2.executeQuery(query3);
 
 String str=null;
 resultSet3.next();
 str= resultSet3.getString(1);
 str= str+","+resultSet3.getString(2);
 out.write(str);
 statement2.close();
 connection.close();
 %>   
