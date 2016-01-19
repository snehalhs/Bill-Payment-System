package com.bps.bo;

import org.apache.log4j.Logger;

import com.bps.exceptions.ApplicationException;

import com.bps.to.BillPaymentTO;
import com.bps.to.ResultSetTO;
import com.bps.util.PropertyUtil;

import com.bps.constants.ErrorConstants;
import com.bps.dao.BillPaymentDAO;

import com.bps.exceptions.BusinessException;
import com.bps.exceptions.DatabaseOperationException;



// TODO: Auto-generated Javadoc
/**
 * The Class BillPaymentBO.
 */
public class BillPaymentBO {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("BillPaymentBO");

	/** The bill payment dao. */
	BillPaymentDAO billPaymentDAO  = new BillPaymentDAO();
	
	/** The result. */
	ResultSetTO result = new ResultSetTO();

/**
 * Validate customer id.
 *
 * @param customer_id the customer_id
 * @return true, if successful
 * @throws BusinessException the business exception
 * @throws DatabaseOperationException the database operation exception
 * @throws ApplicationException the application exception
 */
public boolean validateCustomerID(int customer_id) throws BusinessException, DatabaseOperationException, ApplicationException{
	LOG.info("Method validateCustomerID Invoked in BillPaymentBO" + customer_id);	
	int result1;	
		result1 = billPaymentDAO.customerExists(customer_id);	
	
		
		
		if (result1 == 2){
					
			throw new BusinessException("Invalid Customer ID");
		}
		
		return true;
	}
	
/**
 * Validate amount to pay.
 *
 * @param amount_To_Pay the amount_ to_ pay
 * @param pending_Amount the pending_ amount
 * @return true, if successful
 * @throws BusinessException the business exception
 * @throws DatabaseOperationException the database operation exception
 * @throws ApplicationException the application exception
 */
public boolean validateAmountToPay( String amount_To_Pay,String pending_Amount) throws BusinessException, DatabaseOperationException, ApplicationException{
	LOG.info("Method validateAmountToPay Invoked in BillPaymentBO" + amount_To_Pay + "  "+pending_Amount);
	boolean flag1=true;

	double amount_To_Payy = Double.parseDouble(amount_To_Pay);
	double pending_Amountt = Double.parseDouble(pending_Amount);
	if(pending_Amountt>=amount_To_Payy)
	{
	if(amount_To_Pay.matches("\\d+.\\d\\d")){
	if(amount_To_Payy<0){
		throw new BusinessException("Amount should not be negative");
	  }
	  flag1=true;
	}
	else{
		throw new BusinessException("Amount should be two decimal places");
	}
	}
	else
	{
		throw new BusinessException("Amount to pay  should be less than or equal to pending amount");
	}
	LOG.info("Return value in validateAmountToPay inside BillPaymentBO" + flag1 );
	return flag1;
		}

/**
 * Validate credit card.
 *
 * @param card_Number the card_ number
 * @param confirm_Card_Number the confirm_ card_ number
 * @return true, if successful
 * @throws BusinessException the business exception
 * @throws DatabaseOperationException the database operation exception
 * @throws ApplicationException the application exception
 */
public boolean validateCreditCard(String card_Number,String confirm_Card_Number) throws BusinessException, DatabaseOperationException, ApplicationException{
	
	LOG.info("Method validateCreditCard Invoked in BillPaymentBO" + card_Number + "  "+confirm_Card_Number);
	
	
	
	if (card_Number.equalsIgnoreCase(confirm_Card_Number)){
				
	return true;
	}
	
	else
	{
	throw new BusinessException("Credit card number not matching");
	}
}
	
	
/**
 * Validate cvv.
 *
 * @param cvv the cvv
 * @return true, if successful
 * @throws BusinessException the business exception
 * @throws DatabaseOperationException the database operation exception
 * @throws ApplicationException the application exception
 */
public boolean validateCVV(String cvv) throws BusinessException, DatabaseOperationException, ApplicationException{
	
	LOG.info("Method validateCreditCard Invoked in BillPaymentBO" + cvv );
	

	
	
	if (cvv.length()==3){
			
	return true;
	}
	
	else
	{
	throw new BusinessException("cvv should be of 3 digits");
	}
}
	
	
	
	
	/**
	 * Adds the bill details.
	 *
	 * @param BillPayment the bill payment
	 * @return the result set to
	 * @throws ApplicationException the application exception
	 */
	public ResultSetTO addBillDetails(BillPaymentTO BillPayment) throws ApplicationException{
		LOG.info("Method addBillDetails Invoked in BillPaymentBO" + BillPayment );
		result = billPaymentDAO .add(BillPayment);		
		LOG.info("Return value in addBillDetails inside BillPaymentBO" + result );
		return result;
		
	}
}
