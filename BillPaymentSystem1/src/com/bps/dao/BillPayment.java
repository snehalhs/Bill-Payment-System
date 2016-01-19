package com.bps.dao;



import com.bps.exceptions.ApplicationException;
import com.bps.to.BillPaymentTO;
import com.bps.to.ResultSetTO;



// TODO: Auto-generated Javadoc
/**
 * The Interface BillPayment.
 */
public interface BillPayment{
	
	/**
	 * Customer exists.
	 *
	 * @param customer_id the customer_id
	 * @return the int
	 * @throws ApplicationException the application exception
	 */
	int customerExists(int customer_id) throws ApplicationException;
	
	/**
	 * Adds the.
	 *
	 * @param BillPayment the bill payment
	 * @return the result set to
	 * @throws ApplicationException the application exception
	 */
	ResultSetTO add(BillPaymentTO BillPayment) throws ApplicationException;

}
