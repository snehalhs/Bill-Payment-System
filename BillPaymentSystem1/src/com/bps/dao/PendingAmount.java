package com.bps.dao;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bps.util.DbUtil;
public class PendingAmount {  
	
	int count=0;
	Connection connection=null;
	ResultSet resultset=null;
private String pendingAmount ;      
public PendingAmount(String VendorType)   
    {   
 setPendingAmount(VendorType);   
    }   
public String getPendingAmount() {   
return pendingAmount;   
    }   
private void setPendingAmount(String VendorType) {   
if (VendorType != null)   
        {   
	try {
		connection=DbUtil.getConnection();
				Class.forName("oracle.jdbc.driver.OracleDriver");

		PreparedStatement pst=
connection.prepareStatement("select amount from bps_vendor_type where vendor_Type=?");
			pst.setString(1, VendorType);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					pendingAmount= (rs.getString(1));   
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