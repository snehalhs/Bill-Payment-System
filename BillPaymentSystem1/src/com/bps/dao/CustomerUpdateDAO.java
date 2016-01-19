package com.bps.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.bps.exceptions.CustomerUpdateBusinessException;
import com.bps.exceptions.CustomerUpdateException;

import com.bps.to.CustomerUpdateTO;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerUpdateDAO.
 */
public class CustomerUpdateDAO {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("CustomerUpdateDAO");
	
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
		LOG.info("Connection Success in CustomerUpdateDAO");
		return con;
	}
	
	/**
	 * Searches the Customer ID in the database 
	 *
	 * @param customer_id 
	 * @return true, if successful
	 * @throws CustomerUpdateException the customer update exception
	 * @throws CustomerUpdateBusinessException the customer update business exception
	 * @throws SQLException 
	 */
	public boolean searchDetailsdao(String customer_id) throws CustomerUpdateException,CustomerUpdateBusinessException, SQLException{
		Connection con = null;
		LOG.info("Method searchDetailsdao Invoked in CustomerUpdateDAO" + customer_id);
		boolean flag=false;
		try{		
			int count=0;
			long startTime = System.currentTimeMillis();
			con=getConnection();			
			String q="select * from bps_customer_details where customer_id=?";
			PreparedStatement pst1=con.prepareStatement(q);		
			pst1.setString(1, customer_id);				
			ResultSet rs1=pst1.executeQuery();		
			while(rs1.next())
			{
				count++;
			}
			if(count>0){
				flag=true;
			}		
			LOG.info("total time taken to exexute search details is - "+(System.currentTimeMillis() - startTime));
			
		}catch (ClassNotFoundException e) {
				throw new CustomerUpdateBusinessException(e) ;
		}catch (SQLException e) {
				throw new CustomerUpdateBusinessException(e) ;
		}finally{
			con.close();
		}
		LOG.info("Returned Value from searchDetailsdao in CustomerUpdateDAO :"+flag);			
		return flag;
	}
	
	/**
	 * Fetches the data of the customer from the database
	 *
	 * @param cid the cid
	 * @return the CustomerUpdateTO to
	 * @throws CustomerUpdateException the customer update exception
	 * @throws CustomerUpdateBusinessException the customer update business exception
	 * @throws SQLException 
	 */
	public CustomerUpdateTO displayDetails(int cid)throws CustomerUpdateException,CustomerUpdateBusinessException, SQLException
	{
		Connection con=null;
		LOG.info("Method displayDetails Invoked in CustomerUpdateDAO" + cid);	
		CustomerUpdateTO to=new CustomerUpdateTO();
		try{			
			con=getConnection();			
			String customer_id=Integer.toString(cid);			
			PreparedStatement pst1=con.prepareStatement("select customer_id,customer_name,country_id,address,contact_number,email_id,electricity_check,telephone_check,insurance_check,tax_check,card_number,balance from bps_customer_details where customer_id=?");
			pst1.setString(1, customer_id);			
			ResultSet rs1=pst1.executeQuery();			
			while(rs1.next())
			{				
				to.setCustomerid(rs1.getInt(1));
				to.setCustomername(rs1.getString(2));
				to.setCountryid(rs1.getString(3));
				to.setCustomeraddress(rs1.getString(4));
				to.setContactno(rs1.getString(5));
				to.setMailid(rs1.getString(6));
				to.setElectricity(rs1.getString(7));
				to.setTelephone(rs1.getString(8));
				to.setInsurance(rs1.getString(9));
				to.setTax(rs1.getString(10));
				to.setCardno(rs1.getString(11));
				to.setBalance(rs1.getString(12));								
			}
			String country_id=to.getCountryid();
			ResultSet rs2;
			PreparedStatement pst2=con.prepareStatement("select country_name,state from bps_country where country_id=?");
			pst2.setString(1, country_id);			
			 rs2=pst2.executeQuery();			
			while(rs2.next())
			{
				to.setCustomercountry(rs2.getString(1));
				to.setCustomerstate(rs2.getString(2));
			}							
	
		}catch ( ClassNotFoundException e) {			
			LOG.error("Exception Object in displayDetails Method:"+e);
			throw new CustomerUpdateException(e); 
		}catch (SQLException e) {			
			LOG.error("Exception Object in displayDetails Method:"+e);			
			throw new CustomerUpdateException(e);
		}finally{
			con.close();
		
		}
		LOG.info("Data Obtained from displayDetails in CustomerUpdateDAO :"+to);
		return to;		
		}
	
	/**
	 * Updates the Customer details into the database. 
	 *
	 * @param to the to
	 * @return true, if successful
	 * @throws CustomerUpdateException the customer update exception
	 * @throws CustomerUpdateBusinessException the customer update business exception
	 * @throws SQLException 
	 */
	public boolean updateCustomer(CustomerUpdateTO to) throws CustomerUpdateException,CustomerUpdateBusinessException, SQLException{
		boolean flag=false;
		Connection con=null;
		LOG.info("Method updateCustomer Invoked in CustomerUpdateDAO" + to);	
		try{
			con=getConnection();
			LOG.debug("We got Connection in registerUser Method");
			String customer_id=Integer.toString(to.getCustomerid());
			String query="update bps_customer_details set address=?,contact_number=?,email_id=?,ELECTRICITY_CHECK=?,TELEPHONE_CHECK=?,INSURANCE_CHECK=?,TAX_CHECK=?,CARD_NUMBER=?,BALANCE=? where customer_id=?";
			PreparedStatement pst=con.prepareStatement(query);			
			LOG.debug("PreparedStatement Object Created");
			pst.setString(1,to.getCustomeraddress());
			pst.setString(2, to.getContactno());
			pst.setString(3, to.getMailid());
			pst.setString(4, to.getElectricity());
			pst.setString(5, to.getTelephone());
			pst.setString(6, to.getInsurance());
			pst.setString(7, to.getTax());
			pst.setString(8, to.getCardno());
			pst.setString(9, to.getBalance());
			pst.setString(10, customer_id);
						
			LOG.debug("Values setted to PreparedStatement Object");
			int i=pst.executeUpdate();		
			LOG.info("Return value of executeUpdate Method: "+i);
			if(i>0)
			{
				flag=true;
			}
		}
		catch (ClassNotFoundException e) 
		{
			LOG.error("Exception Object in updateCustomer Method:"+e);
			throw new CustomerUpdateBusinessException(e);
		}
		catch (SQLException e) 
		{
			LOG.error("Exception Object in updateCustomer Method:"+e);
			throw new CustomerUpdateException(e);
		}finally{
			con.close();
		}
		LOG.info("Returned Value of flag inside updateCustomer in CustomerUpdateDAO: "+flag);		
		return flag;
	}	
}
