package com.bps.bo;


import org.apache.log4j.Logger;

import com.bps.dao.UserDAO;
import com.bps.exceptions.CustomerRegistrationBussinessException;
import com.bps.exceptions.CustomerRegistrationExecption;
import com.bps.to.UserTo;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerRegistrationBO.
 */
public class CustomerRegistrationBO 
{
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("CustomerRegistrationBO");
	
	/** The dao. */
	UserDAO dao=new UserDAO();
	
	/**
	 * Register user.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws CustomerRegistrationExecption the customer registration exception
	 * @throws CustomerRegistrationBussinessException the customer registration business exception
	 */
	public boolean registerUser(UserTo user) throws CustomerRegistrationExecption,CustomerRegistrationBussinessException
	{
		LOG.info("Method registerUser Invoked in CustomerRegistrationBO" + user);
		boolean flag=false;
		flag=dao.registerUser(user);
		LOG.info("Returned Value in registerUser BO:"+flag);
		return flag;
	}
}
