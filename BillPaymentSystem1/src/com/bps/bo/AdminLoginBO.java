package com.bps.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bps.dao.AdminLoginDAO;
import com.bps.exceptions.ApplicationException;
import com.bps.exceptions.BusinessException;
import com.bps.exceptions.DatabaseOperationException;
import com.bps.to.ResultSetTO;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminLoginBO.
 */
public class AdminLoginBO {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("AdminLoginBO");
 
 /** The admin logindao. */
 AdminLoginDAO adminLogindao = new AdminLoginDAO();
	
	/** The result. */
	ResultSetTO result = new ResultSetTO();

/**
 * Validate user name.
 *
 * @param user_Name the user_ name
 * @param user_Password the user_ password
 * @return true, if successful
 * @throws BusinessException the business exception
 * @throws DatabaseOperationException the database operation exception
 * @throws ApplicationException the application exception
 */
public boolean validateUserName(String user_Name,String user_Password) throws BusinessException, DatabaseOperationException, ApplicationException{
	LOG.info("Method validateUserName Invoked in AdminLoginBO" + user_Name+ " ," +user_Password);	
	List <String>adminDetails = new ArrayList<String>(); 
	
		
		adminDetails = adminLogindao.adminDetails(user_Name,user_Password);
		
		
		
		
		if (adminDetails.get(0).equals(user_Name) &&adminDetails.get(1).equals(user_Password)){
						
			return true;
		}
		else
		{
			throw new BusinessException("invalid  id and password");
		}			
	}	
}
