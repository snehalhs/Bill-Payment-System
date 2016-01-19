package com.bps.dao;



import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bps.util.DbUtil;
public class States {  
	
	int count=0;
	Connection connection=null;
	ResultSet resultset=null;
private List <String>cities = new ArrayList<String>();      
public States(String Country)   
    {   
 setCities(Country);   
    }   
public List<String>getCities() {   
return cities;   
    }   
private void setCities(String Country) {   
if (Country != null)   
        {   
	try {
		connection=DbUtil.getConnection();
				Class.forName("oracle.jdbc.driver.OracleDriver");
		PreparedStatement pst=
connection.prepareStatement("select unique(state) from bps_country where country_name=?");
			pst.setString(1, Country);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					cities.add(rs.getString(1));   
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				
			}   catch (SQLException e) {
				// TODO Auto-generated catch block
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
			
			}                
       }
           }   
 }   
