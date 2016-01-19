package com.bps.dao;



import java.util.List;

import com.bps.exceptions.ApplicationException;




// TODO: Auto-generated Javadoc
/**
 * The Interface AdminLogin.
 */
public interface AdminLogin{
	
	/**
	 * Admin details.
	 *
	 * @param user_Name the user_ name
	 * @param user_Password the user_ password
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	List<String> adminDetails(String user_Name,String user_Password) throws ApplicationException;
	

}
