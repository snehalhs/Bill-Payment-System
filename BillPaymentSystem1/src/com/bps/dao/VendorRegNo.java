package com.bps.dao;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bps.util.DbUtil;
public class VendorRegNo {   
	int count=0;
	Connection connection=null;
	ResultSet resultset=null;
private List <String>vendorRegNo = new ArrayList<String>();   
public VendorRegNo()   
    {   try {
    	
		Class.forName("oracle.jdbc.driver.OracleDriver");
    connection=DbUtil.getConnection();
		
	PreparedStatement pst=connection.prepareStatement(" select unique(reg_number) from bps_vendor_summ ");
		
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				vendorRegNo.add(rs.getString(1));   
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		}   catch (SQLException e) {
			// TODO Auto-generated catch block
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}             

    }   

public List<String> getVendorRegNo() {   
return vendorRegNo;   
    }   

}   
