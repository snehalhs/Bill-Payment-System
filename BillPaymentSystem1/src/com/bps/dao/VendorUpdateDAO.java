package com.bps.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bps.exceptions.VendorUpdateBusinessException;
import com.bps.exceptions.VendorUpdateException;
import com.bps.to.VendorUpdateTo;

// TODO: Auto-generated Javadoc
/**
 * The Class VendorUpdateDAO.
 */
public class VendorUpdateDAO {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("VendorUpdateDAO");
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	private java.sql.Connection getConnection()throws SQLException, ClassNotFoundException
	{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection	con=DriverManager.getConnection("jdbc:oracle:thin:@10.241.47.160:1521:oracle","training","training");
		LOG.info("Connection Success in DAO");
		
		return con;
	}
	
	/**
	 * Search details of vendor in searchDetailsdao.
	 *
	 * @param vid the vid
	 * @return true, if successful
	 * @throws VendorUpdateException the vendor update exception
	 * @throws VendorUpdateBusinessException the vendor update business exception
	 */
	public boolean searchDetailsdao(int vid) throws VendorUpdateException,VendorUpdateBusinessException
	{
		LOG.info("Method searchDetailsdao Invoked in VendorUpdateDAO" + vid);
	
		boolean flag=false;
	
		try{		
			int count=0;		
			Connection con=getConnection();	
			LOG.debug("We got Connection in searchDetailsdao Method");
			String q="select * from bps_vendor_details where vendor_id=?";
			PreparedStatement pst1=con.prepareStatement(q);	
			LOG.debug("PreparedStatement Object Created");
			pst1.setInt(1,vid);		
			LOG.debug("Values setted to PreparedStatement Object");
			ResultSet rs=pst1.executeQuery();	
			
			while(rs.next())
			{
				count++;
			}
			if(count>0)
			{
				flag=true;
			}		
		}
		 catch (ClassNotFoundException e) {
			 LOG.error("Exception Object in searchDetailsdao Method:"+e);
				throw new VendorUpdateBusinessException(e) ;
			}catch (SQLException e) {
				LOG.error("Exception Object in searchDetailsdao Method:"+e);
				throw new VendorUpdateBusinessException(e) ;
			}
			LOG.info("Returned Value from searchDetailsdao1 in VendorUpdateDAO :"+flag);
		return flag;
	}
	
	/**
	 * Display details of vendor.
	 *
	 * @param vid the vid
	 * @return the vendor update to
	 * @throws VendorUpdateException the vendor update exception
	 * @throws VendorUpdateBusinessException the vendor update business exception
	 */
	public VendorUpdateTo displayDetails(int vid)throws VendorUpdateException,VendorUpdateBusinessException
	{
		LOG.info("Method displayDetails Invoked in VendorUpdateDAO" + vid);
		VendorUpdateTo to=new VendorUpdateTo();
		try{			
			Connection con1=getConnection();
					
			PreparedStatement pst=con1.prepareStatement("select * from bps_vendor_details where vendor_id=?");
			pst.setInt(1,vid);			
			ResultSet rs1=pst.executeQuery();
			
			
			while(rs1.next())
			{
				to.setVendorid(rs1.getInt(1));
				to.setVendorname(rs1.getString(2));
				to.setVendortype(rs1.getString(3));
				to.setVendorregnumber(rs1.getString(4));
				to.setCountryid(rs1.getString(5));
				to.setEmailid(rs1.getString(6));
				to.setContactnumber(rs1.getString(7));
				to.setWebsite(rs1.getString(8));
				to.setCertificateissuedate(rs1.getString(9));
				to.setCertificatevaliddate(rs1.getString(10));
				to.setEmployeecount(rs1.getInt(11));
				to.setCustomercount(rs1.getInt(12));
				to.setYoe(rs1.getInt(13));
				to.setAddress(rs1.getString(14));
				to.setCertificate(rs1.getString(15));
				
			}
			Connection con2=getConnection();
	
			PreparedStatement pst2=con2.prepareStatement("select * from bps_country where country_id=?");
			pst2.setString(1,to.getCountryid());
			ResultSet rs2=pst2.executeQuery();
				if(rs2.next())
				{
			
					to.setCountry(rs2.getString(2));
					to.setState(rs2.getString(3));
				}
							
			
		}catch ( ClassNotFoundException e) {
			// TODO: handle exception		
			LOG.error("Exception Object in displayDetails Method:"+e);
		
			throw new VendorUpdateException(e); 
		}catch (SQLException e) {
			// TODO: handle exception
			LOG.error("Exception Object in displayDetails Method:"+e);		
	
			
		}		
		
		LOG.info("Returned Value from displayDetails in VendorUpdateDAO :"+to);
		return to;		
			
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public ArrayList<String> getCountry() throws SQLException, ClassNotFoundException
	{
		LOG.info("Method getCountry Invoked in VendorUpdateDAO" );
	
		ArrayList<String> country = new ArrayList<String>();
		try{
			
			Connection con5=getConnection();
		
			PreparedStatement pst5=con5.prepareStatement("select distinct country_name from bps_country");
			
			ResultSet rs5 = pst5.executeQuery();
			
			while(rs5.next())
			{
			
				country.add(rs5.getString("country_name"));
			}
						
		}catch (Exception e) {
			// TODO: handle exception
			
			throw	new  SQLException(e);
		}
		LOG.info("Returned Value from getCountry in VendorUpdateDAO :"+country);
		return country;
	}
	
	/**
	 * Gets the state.
	 *
	 * @param country the country
	 * @return the state
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public ArrayList<String> getState(String country) throws SQLException, ClassNotFoundException
	{
		LOG.info("Method getState Invoked in VendorUpdateDAO" );
	
		ArrayList<String> state = new ArrayList<String>();
		try{
			
			Connection con4=getConnection();
		
			PreparedStatement pst4=con4.prepareStatement("select state from bps_country where country_name=?");
			pst4.setString(1,country);
			ResultSet rs4 = pst4.executeQuery();
			
			
			while(rs4.next())
			{
		
				state.add(rs4.getString("state"));
			}
				
		}catch (Exception e) {
			// TODO: handle exception
		
			throw	new  SQLException(e);
		}
		LOG.info("Returned Value from getState in VendorUpdateDAO :"+state);
		return state;
	}
	
	/**
	 * Update vendor.
	 *
	 * @param to the to
	 * @return true, if successful
	 * @throws VendorUpdateException the vendor update exception
	 * @throws VendorUpdateBusinessException the vendor update business exception
	 */
	public boolean updateVendor(VendorUpdateTo to) throws VendorUpdateException,VendorUpdateBusinessException{
		boolean flag=false;
		LOG.info("Method updateVendor Invoked in VendorUpdateDAO "+ to);
		try{
			Connection con=getConnection();
	
			
			String query1="select country_id from bps_country where country_name=? and state=?";
			PreparedStatement pst3=con.prepareStatement(query1);		
			pst3.setString(1,to.getCountry());
			pst3.setString(2,to.getState());
			ResultSet rs3 = pst3.executeQuery();
			
			if(rs3.next())
			{
				to.setCountryid(rs3.getString(1));
			}
			
			LOG.debug("We got Connection in updateVendor Method");
			
			String query="update bps_vendor_details set country_id=?,address=?,contact_number=?,email_id=?,web_site=?,employee_count=?,customer_count=? where vendor_id=?";
			PreparedStatement pst=con.prepareStatement(query);			
			LOG.debug("PreparedStatement Object Created");
			pst.setString(1,to.getCountryid());
			pst.setString(2, to.getAddress());
			pst.setString(3, to.getContactnumber());
			pst.setString(4, to.getEmailid());
			pst.setString(5, to.getWebsite());
			pst.setInt(6, to.getEmployeecount());
			pst.setInt(7, to.getCustomercount());
			pst.setInt(8,to.getVendorid());
	
			LOG.debug("Values set to PreparedStatement Object");
			int update=pst.executeUpdate();
	
			LOG.info("Return value of executeUpdate Method:"+update);
			if(update>0)
			{
				flag=true;
			}
		}
		catch (ClassNotFoundException e) 
		{
			LOG.error("Exception Object in updateVendor Method:"+e);
			throw new VendorUpdateBusinessException(e);
		}
		catch (SQLException e) 
		{
			LOG.error("Exception Object in updateVendor Method:"+e);
	
			throw new VendorUpdateException(e);
		}
		LOG.info("Returned Value from updateVendor in VendorUpdateDAO :"+flag);
		
		return flag;
	}
	
}
