package com.bps.dao;


import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bps.exceptions.CustomerRegistrationBussinessException;
import com.bps.util.DbUtil;
public class Countries {   
	int count=0;
	Connection connection=null;
	ResultSet resultset=null;
private List <String>countries = new ArrayList<String>();   
public Countries() throws CustomerRegistrationBussinessException   
    {   try {
    	
		Class.forName("oracle.jdbc.driver.OracleDriver");
    connection=DbUtil.getConnection();
		
	PreparedStatement pst=connection.prepareStatement(" select unique(country_name) from bps_country");
		
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				countries.add(rs.getString(1));   
			}
		} catch (ClassNotFoundException e) {
			throw new CustomerRegistrationBussinessException();
		}   catch (SQLException e) {
			throw new CustomerRegistrationBussinessException();
		} catch (IOException e) {
			throw new CustomerRegistrationBussinessException();
		}             
    }  
public List<String> getCountries() {   
return countries;   
    }   

}   
