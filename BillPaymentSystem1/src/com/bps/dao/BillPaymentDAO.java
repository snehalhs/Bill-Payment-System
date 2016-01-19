package com.bps.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.apache.log4j.Logger;

import com.bps.exceptions.ApplicationException;
import com.bps.exceptions.BusinessException;
import com.bps.to.BillPaymentTO;
import com.bps.to.ResultSetTO;

import com.bps.exceptions.DatabaseOperationException;
import com.bps.util.DbUtil;

import com.bps.exceptions.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Class BillPaymentDAO.
 */
public class BillPaymentDAO implements BillPayment {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("BillPaymentDAO");
	
	/** The count. */
	int count=0;
	
	/** The connection. */
	Connection connection=null;
	
	/** The resultset. */
	ResultSet resultset=null;
	
	/* (non-Javadoc)
	 * @see com.bps.dao.BillPayment#customerExists(int)
	 */
	public int customerExists(int customer_id) throws ApplicationException,DatabaseOperationException {
		// TODO Auto-generated method stub
		int response=2;		
		PreparedStatement statement = null;
		LOG.info("customerExists Method Invoked in BillPaymentDAO "+customer_id);
		try {
			connection=DbUtil.getConnection();
			String query8= " select CUSTOMER_ID from  bps_Customer_Details where customer_id=?";
			statement= connection.prepareStatement(query8);
			statement.setInt(1,customer_id);
			resultset=statement.executeQuery();
			while(resultset.next())
			{
					response=1;
					break;
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseOperationException("SQL Exception happened", e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException(e);
		}
		finally {

			try {
				statement.close();
			
					resultset.close();
				
					connection.close();
				
			} catch (SQLException e) {
				throw new DatabaseOperationException("SQL Exception happened", e);
			}

		}
		LOG.info("Returned Value from customerExists in BillPaymentDAO :"+response);
		return response;
		}
	

	/* (non-Javadoc)
	 * @see com.bps.dao.BillPayment#add(com.bps.to.BillPaymentTO)
	 */
	public ResultSetTO add(BillPaymentTO BillPayment)throws ApplicationException{
		LOG.info("add Method Invoked in BillPaymentDAO "+BillPayment);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	    ResultSetTO result = new ResultSetTO();
	    Double amountPaid=BillPayment.getAmountToPayy();

	   Double balance = null;
	   String balancee = null;

	   String finalBalance3=null;
	   
		try {
			
				
			connection = DbUtil.getConnection();		
			String query="select count(CUSTOMER_ID) from  bps_Customer_Details";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery(); 
		 	resultSet.next();
		 	
		 	
		 	
		 	connection = DbUtil.getConnection();		
			String queryy="select balance from  bps_Customer_Details where customer_id=? ";
			
			preparedStatement = connection.prepareStatement(queryy);
			preparedStatement.setInt(1, BillPayment.getCustomerID());
			resultSet = preparedStatement.executeQuery(); 
		 	while(resultSet.next())
		 	{
		 	 balancee=resultSet.getString(1);
		 	}
		 
			balance=Double.parseDouble(balancee);
				Double finalBalance=balance-amountPaid;	
				
				
				Double finalBalancee = new Double(finalBalance);
				int tempstring = finalBalancee.intValue();
				
				
				finalBalance3=""+tempstring;
				
				String query5="update bps_customer_details set BALANCE=? where CUSTOMER_ID=?";
			    PreparedStatement pst5=connection.prepareStatement(query5);
			    pst5.setString(1, finalBalance3);
			    pst5.setString(2, BillPayment.getCustomerid());
			  
			    pst5.executeUpdate();
			
			   
			    
			    
			    
			    
			String query1="insert into G4_Bill_Payment(bill_id ,customer_id ,vendor_name," +
					"amount_paid ,card_number,cvv ,card_validity_month," +
					"card_validity_year ,card_type,payment_date ) values (bill_id_seq_bk.nextval,?,?,?,?,?,?,?,?,?)";
			
			preparedStatement = connection.prepareStatement(query1);
			
			preparedStatement.setInt(1, BillPayment.getCustomerID());
			preparedStatement.setString(2, BillPayment.getVendorName());	
			preparedStatement.setDouble(3, BillPayment.getAmountToPayy());			   
			preparedStatement.setString(4, BillPayment.getCardNumber());
			preparedStatement.setInt(5, BillPayment.getCvv());			
			preparedStatement.setInt(6, BillPayment.getCreditCardValidityMM());
			preparedStatement.setInt(7, BillPayment.getCreditCardValidityYY());
			
			preparedStatement.setString(8, BillPayment.getCreditCardType());
			preparedStatement.setString(9, BillPayment.getPaymentDate());
		    int return_value=preparedStatement.executeUpdate();	
		   
		    
		    if(return_value>0){
			
				
				result.setId(BillPayment.getCustomerid());
		    
			}
		    else
			{
			throw new BusinessException("Not inserted successfully");
			}
		// TODO Auto-generated method stub
		    
		    
		} 
		catch(BusinessException be){
			
			}
		catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);

		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}catch(Exception e){
			throw new SystemException(e);
		}
		finally {

			try {
				preparedStatement.close();
				if(connection!=null)
				{
					connection.close();
				}
			} catch (SQLException sqlException) {
				new DatabaseOperationException(	sqlException);
			}
		}
		LOG.info("Returned Value from add in BillPaymentDAO :"+result);
		return result;
	}

}
