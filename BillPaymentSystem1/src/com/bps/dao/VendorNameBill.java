package com.bps.dao;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bps.util.DbUtil;
public class VendorNameBill {  
	
	int count=0;
	Connection connection=null;
	ResultSet resultset=null;
private List <String>vendorName = new ArrayList<String>();      
public VendorNameBill(String VendorType)   
    {   
 setVendorName(VendorType);   
    }   
public List<String>getVendorName() {   
return vendorName;   
    }   
private void setVendorName(String VendorType) {   
if (VendorType != null)   
        {   
	try {
		connection=DbUtil.getConnection();
				Class.forName("oracle.jdbc.driver.OracleDriver");
PreparedStatement pst=
connection.prepareStatement("select unique(vendor_name) from bps_vendor_summ where vendor_type=?");
			pst.setString(1, VendorType);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					vendorName.add(rs.getString(1));   
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