package com.bps.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.apache.log4j.Logger;

import com.bps.bo.VendorBO;
import com.bps.exceptions.RegistrationBusinessException;
import com.bps.exceptions.RegistrationException;

import com.bps.to.VendorTo;



// TODO: Auto-generated Javadoc
/**
 * The Class VendorDAO.
 */
public class VendorDAO {
	public static final Logger LOG = Logger.getLogger("VendorDAO");
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	private java.sql.Connection getConnection()throws SQLException, ClassNotFoundException
	{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection	con=DriverManager.getConnection("jdbc:oracle:thin:@10.241.47.160:1521:oracle","training","training");
		
		
		return con;
	}
	
	/**
	 * Gets the details.
	 *
	 * @param regnum the regnum
	 * @return the details
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public VendorTo getDetails(String regnum) throws SQLException, ClassNotFoundException
	{	
		LOG.info("Method getDetails Invoked in VendorDAO" + regnum);
		
		VendorTo vendor = new VendorTo();
	
		Connection con=getConnection();
		String query="select * from bps_vendor_summ where reg_number=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,regnum);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			  
			   vendor.setVendorregnumber(rs.getString(1));
			    vendor.setVendorname(rs.getString(2));
			    vendor.setVendortype(rs.getString(3));
		}
		LOG.info("Returned Value from getDetails in VendorDAO :"+vendor);
		return vendor;
			
	}
	
	/**
	 * Register vendor.
	 *
	 * @param vendor the vendor
	 * @return true, if successful
	 * @throws RegistrationException the registration exception
	 * @throws RegistrationBusinessException the registration business exception
	 */
	public boolean registerVendor(VendorTo vendor) throws RegistrationException, RegistrationBusinessException{
		LOG.info("Method registerVendor Invoked in VendorDAO" + vendor);
		boolean flag=false;
		VendorBO vbo=new VendorBO();
		String certificate=vbo.getCertificate(2014-vendor.getYoe(),vendor.getEmployeecount());
		vendor.setCertificate(certificate);
	
		try{
	
			Connection con=getConnection();
			
			
			PreparedStatement pst2=con.prepareStatement("select country_id from bps_country where country_name=? and state=?");
			pst2.setString(1,vendor.getCountry());
			pst2.setString(2,vendor.getState());
			
			ResultSet rs2=pst2.executeQuery();
			if(rs2.next())
			{
			vendor.setCountryid(rs2.getString(1));
			
			}
			int vendor_id=0;
			String sqlIdentifier = "select vendors_seq.nextval from dual";
			PreparedStatement pst1 = con.prepareStatement(sqlIdentifier);
			 ResultSet rs = pst1.executeQuery();
			   if(rs.next()){
				   
			      vendor_id = rs.getInt(1);
			   }

			vendor.setVendorid(vendor_id);
			
			String query="insert into bps_vendor_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			
			pst.setInt(1, vendor.getVendorid());
			pst.setString(2, vendor.getVendorname());
			pst.setString(3, vendor.getVendortype());
			pst.setString(4, vendor.getVendorregnumber());
			pst.setString(5, vendor.getCountryid());
			pst.setString(6, vendor.getEmailid());
			pst.setString(7, vendor.getContactnumber());
			pst.setString(8, vendor.getWebsite());
			pst.setString(9, vendor.getCertificateissuedate());
			pst.setString(10, vendor.getCertificatevaliddate());
			pst.setInt(11, vendor.getEmployeecount());
			pst.setInt(12, vendor.getCustomercount());
			pst.setInt(13, vendor.getYoe());
			pst.setString(14, vendor.getAddress());
			pst.setInt(15,vendor.getYos());
			pst.setString(16, vendor.getCertificate());
			
			int update=pst.executeUpdate();
			
			if(update>0)
			{	
				
				flag=true;
				
			}
			
		}
			
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		}catch (SQLException e) {
			// TODO: handle exception
			
		}
		
		LOG.info("Returned Value from registerVendor in VendorDAO :"+flag);
		return flag;
	}
			
}