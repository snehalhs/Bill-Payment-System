package com.bps.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bps.exceptions.CustomerRegistrationBussinessException;
import com.bps.exceptions.CustomerRegistrationExecption;
import com.bps.to.UserTo;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 */
public class UserDAO {
    
    /** The Constant LOG. */
    public static final Logger LOG = Logger.getLogger("UserDAO");
	
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
		LOG.info("Connection Success in UserDAO");
		return con;
	}
	
	/**
	 * Register user.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws CustomerRegistrationBussinessException the customer registration bussiness exception
	 * @throws CustomerRegistrationExecption the customer registration execption
	 */
	public boolean registerUser(UserTo user) throws CustomerRegistrationBussinessException, CustomerRegistrationExecption{
		boolean flag=false;
		LOG.info("In DAO registerUser Method:"+user);
		try{
			Connection con=getConnection();
			LOG.debug("We got Connection in registerUser Method");
			
			int myId=0;
			String sqlIdentifier = "select customers_sequence.nextval from dual";
			PreparedStatement pst1 = con.prepareStatement(sqlIdentifier);
			ResultSet rs1 = pst1.executeQuery();
			   if(rs1.next()){
			      myId = rs1.getInt(1);
			   }
			   user.setCustomerId(myId);
		

		    con=getConnection();
			String query2="select country_id from bps_country where country_name=? and state=?";
			PreparedStatement pst2=con.prepareStatement(query2);
			pst2.setString(1, user.getCountry());
			pst2.setString(2, user.getState());
			ResultSet rs2=pst2.executeQuery();
			String country_id="";
			while(rs2.next())
			{
				country_id=rs2.getString(1);
			}
			
			con=getConnection();
			String query3="select prefix_format from bps_id_documents where document_type=?";
			PreparedStatement pst3=con.prepareStatement(query3);
			pst3.setString(1, user.getIdentificationDocument());
			ResultSet rs3=pst3.executeQuery();
			String d_prefix="";
			while(rs3.next())
			{
				d_prefix=rs3.getString(1);
				
			}
		String d_prefix1=d_prefix+user.getIdNo();
			
			user.setIdNo(d_prefix1);
			
			String query="insert into bps_customer_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			LOG.debug("PreparedStatement Object Created"); 
			pst.setInt(1,user.getCustomerId());
			pst.setString(2, user.getCustomerName());
			pst.setString(3, user.getAddress());
			pst.setString(4, user.getContactNo());
			pst.setString(5, country_id);
			pst.setString(6, user.getMailId());			
			pst.setString(7, user.getIdNo());
			pst.setString(8, user.getRegistrationDate());
			pst.setString(9, user.getCardNo());
			pst.setString(10, user.getBalance());
			pst.setString(11, user.getElectricity());
			pst.setString(12, user.getTelephone());
			pst.setString(13, user.getInsurance());
			pst.setString(14, user.getTax());
			pst.setString(15, user.getIdentificationDocument());
		
			LOG.debug("Values setted to PreparedStatement Object");
			int i=pst.executeUpdate();
			LOG.info("Return value of executeUpdate Method:"+i);
			
			if(i>0)
			{
				flag=true;
			}
		}
		catch (ClassNotFoundException e) 
		{
			LOG.error("Exception Object in registerUser Method:"+e);
			throw new CustomerRegistrationExecption(e);
		}
		catch (SQLException e) 
		{
			LOG.error("Exception Object in registerUser Method:"+e);
			throw new CustomerRegistrationExecption(e);
		}
		LOG.info("Returned Value in DAO:"+flag);
	
		return flag;
	}
		
	/**
	 * Gets the locality.
	 *
	 * @return the locality
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public ArrayList<UserTo> getLocality() throws IOException, SQLException, ClassNotFoundException
	{
		LOG.info("Method getLocality Invoked in UserDAO" );
		Connection conn=getConnection();
		Statement statement=conn.createStatement();
		String query="select city from city_details";
		ResultSet rs= null;
		rs=statement.executeQuery(query);
		ArrayList<UserTo> l1=new ArrayList<UserTo>();
		while(rs.next())
		{
			UserTo art=new UserTo();
			art.setLocal(rs.getString("city"));
			l1.add(art);
		}
		LOG.info("Returned Value in UserDAO:"+l1);
		return l1;
	}
}
	
