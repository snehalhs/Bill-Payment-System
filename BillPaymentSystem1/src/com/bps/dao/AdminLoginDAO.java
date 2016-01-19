package com.bps.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bps.exceptions.ApplicationException;
import com.bps.exceptions.DatabaseOperationException;
import com.bps.util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminLoginDAO.
 */
public class AdminLoginDAO implements AdminLogin{
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("AdminLoginDAO");
	
	/** The count. */
	int count=0;
	
	/** The connection. */
	Connection connection=null;
	
	/** The resultset. */
	ResultSet resultset=null;
	
	/** The admin details. */
	private List <String>adminDetails = new ArrayList<String>();     
	
	/* (non-Javadoc)
	 * @see com.bps.dao.AdminLogin#adminDetails(java.lang.String, java.lang.String)
	 */
	public  List<String> adminDetails(String user_Name,String user_Password)throws ApplicationException,DatabaseOperationException {
	    {   
	    	LOG.info("Method adminDetails Invoked in AdminLoginDAO" + user_Name+ "   "+user_Password);
		PreparedStatement statement = null;

		try {
			
	if (user_Name!= null && user_Password != null)   
	        {  
		
			connection=DbUtil.getConnection();
			
			String query8= " select User_Name,User_Password from  Admin_Details where User_Name=?";
			statement= connection.prepareStatement(query8);
			statement.setString(1,user_Name);
			resultset=statement.executeQuery();
			
			while(resultset.next())
			{
				adminDetails.add(resultset.getString(1));
				adminDetails.add(resultset.getString(2));				
			}
		
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseOperationException("SQL Exception happened", e);
		} catch (ClassNotFoundException cfe) {
			// TODO Auto-generated catch block
			throw new ApplicationException();
		} catch (IOException io) {
			// TODO Auto-generated catch block
			throw new ApplicationException();
		}
		finally {

			try {
				statement.close();
				
					resultset.close();
				
					connection.close();
			
					
			} catch (SQLException e) {
				throw new DatabaseOperationException("SQL Exception happened", e);
			}
			LOG.info("Returned Value from adminDetails in AdminLoginDAO :"+adminDetails);
			return adminDetails;
		

		}
		}
	}
	
	
		}