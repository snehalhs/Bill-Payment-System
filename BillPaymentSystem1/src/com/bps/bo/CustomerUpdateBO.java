package com.bps.bo;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.bps.dao.CustomerUpdateDAO;
import com.bps.exceptions.CustomerUpdateBusinessException;
import com.bps.exceptions.CustomerUpdateException;
import com.bps.to.CustomerUpdateTO;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerUpdateBO.
 */
public class CustomerUpdateBO {	
	public static final Logger LOG = Logger.getLogger("CustomerUpdateBO");	
	CustomerUpdateDAO dao=new CustomerUpdateDAO();
	
	/**
	 * Searches the entered customer ID in the database and returns true if customer id exits in the database.
	 *
	 * @param cid1 the cid1
	 * @return true, if successful
	 * @throws CustomerUpdateBusinessException the customer update business exception
	 * @throws CustomerUpdateException the customer update exception
	 * @throws SQLException 
	 */
	public boolean searchDetails(int cid1)throws CustomerUpdateBusinessException,CustomerUpdateException, SQLException
	{
		LOG.info("Method searchDetails Invoked in CustomerUpdateBO" + cid1);
		boolean flag=false;	
		String cid=Integer.toString(cid1);		
		try {
			flag=dao.searchDetailsdao(cid);					
			if(flag==false){
				throw new CustomerUpdateBusinessException("Please enter the correct Phone Number");
			}
		} catch (CustomerUpdateBusinessException e) {				
			throw new CustomerUpdateBusinessException("Please enter a valid Customer Id");
		} 	
		LOG.info("Returned Value in searchDetails CustomerUpdateBO:"+flag);		
		return flag;
	}
	
	/**
	 * Fetches the details for a specific Customer ID
	 *
	 * @param Customer ID
	 * @return the CustomerUpdate to object with values.
	 * @throws CustomerUpdateBusinessException the customer update business exception
	 * @throws CustomerUpdateException the customer update exception
	 * @throws SQLException 
	 */
	public CustomerUpdateTO displayDetails(int cid)throws CustomerUpdateBusinessException,CustomerUpdateException, SQLException
	{
		LOG.info("Method displayDetails Invoked in CustomerUpdatebo");		
		CustomerUpdateTO to=dao.displayDetails(cid);
		LOG.info("Return Value in CustomerUpdateBO:"+to);				
		return to;
}
	
	/**
	 * Updates the details of the customer ID.
	 *
	 * @param to the to
	 * @return true, if successful
	 * @throws CustomerUpdateBusinessException the customer update business exception
	 * @throws CustomerUpdateException the customer update exception
	 * @throws SQLException 
	 */
	public boolean updateCustomer(CustomerUpdateTO to) throws CustomerUpdateBusinessException,CustomerUpdateException, SQLException
	{
		LOG.info("Method Updatecustomer Invoked in CustomerUpdateBO");	
		boolean successflag=false;		
		successflag=dao.updateCustomer(to);		
		LOG.info("Returned Value in Updatecustomer CustomerUpdateBO:"+successflag);	
		return successflag;		
	}
}
