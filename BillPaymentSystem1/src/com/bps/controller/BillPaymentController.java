package com.bps.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import com.bps.bo.BillPaymentBO;
import com.bps.to.BillPaymentTO;
import com.bps.to.ResultSetTO;
import com.bps.util.PropertyUtil;

import com.bps.constants.ErrorConstants;
import com.bps.exceptions.BusinessException;
import com.bps.exceptions.DatabaseOperationException;

// TODO: Auto-generated Javadoc
//import cts.hms.util.PropertyUtil;
/**
 * Servlet implementation class BillPaymentController.
 */
public class BillPaymentController extends HttpServlet {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("BillPaymentController");
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new bill payment controller.
     *
     * @see HttpServlet#HttpServlet()
     */
    public BillPaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BillPaymentTO billPayment=new BillPaymentTO();
		ResultSetTO result = new ResultSetTO();
		
		BillPaymentBO billPaymentBO= new BillPaymentBO();
		
		
		
	
		try {
			String customer_id=request.getParameter("CustomerID");
			int customer_ID = Integer.parseInt(customer_id);
			String vendor_Type=request.getParameter("VendorType");
		
			String vendor_Name=request.getParameter("VendorName");
			String pending_Amount=request.getParameter("PendingAmount");
			
			String payment_Date=request.getParameter("PaymentDate");
			String amount_To_Pay=request.getParameter("AmountToPay");
			double amount_To_Payy = Double.parseDouble(amount_To_Pay);
			double pending_Amountt = Double.parseDouble(pending_Amount);
			String credit_Card_Type=request.getParameter("CreditCardType");
			String card_Number=request.getParameter("CardNumber");
			String confirm_Card_Number=request.getParameter("ConfirmCardNumber");
			String credit_Card_ValidityMM=request.getParameter("MM");
			int creditCard_ValidityMM = Integer.parseInt(credit_Card_ValidityMM);
			String credit_Card_ValidityYY=request.getParameter("yyyy");
			int creditCard_ValidityYY = Integer.parseInt(credit_Card_ValidityYY);
			String cvv=request.getParameter("cvv");
			
			int cVv = Integer.parseInt(cvv);
		
			
			
			if(billPaymentBO.validateCustomerID(customer_ID)){
				
				if(billPaymentBO.validateAmountToPay(amount_To_Pay,pending_Amount)){
			
					if(billPaymentBO.validateCreditCard(card_Number,confirm_Card_Number)){
					
							if(billPaymentBO.validateCVV(cvv)){
								
			billPayment.setCustomerid(customer_id);		
			billPayment.setCustomerID(customer_ID);
			billPayment.setVendorType(vendor_Type);
			billPayment.setVendorName(vendor_Name);
			billPayment.setPendingAmount(pending_Amount);
			billPayment.setPaymentDate(payment_Date);
			billPayment.setAmountToPay(amount_To_Pay);
			billPayment.setAmountToPayy(amount_To_Payy);
			billPayment.setPendingAmountt(pending_Amountt);
			billPayment.setCreditCardType(credit_Card_Type);
			billPayment.setCardNumber(card_Number);
			billPayment.setConfirmCardNumber(confirm_Card_Number);
			billPayment.setCreditCardValidityMM(creditCard_ValidityMM);
			billPayment.setCreditCardValidityYY(creditCard_ValidityYY);
			billPayment.setCVV(cvv);
			
			RequestDispatcher dispatcher = null;
			LOG.info("Username and password in BillPaymentController "+customer_id);
			result = billPaymentBO.addBillDetails(billPayment);
			LOG.info("Bill payment details in BillPaymentController "+customer_id);
			request.setAttribute("id",customer_ID);
			   dispatcher = request.getRequestDispatcher("display.jsp");
			   dispatcher.forward(request, response);
					}
				}
			
			}
			
			
		}
		}
		catch(BusinessException be){
			
					request.setAttribute("message1",be.getMessage());
					
					final RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}
		catch (DatabaseOperationException databaseOperationException) {// Handles the LoginExceptions and log the errors into the Log	
				
				request.setAttribute("message", PropertyUtil
								.getErrorMessage(ErrorConstants.FATALERROR));	
				LOG.error("Exception occured while processing patient diagnosis details", databaseOperationException.getCause());
				final RequestDispatcher dispatcher = request
						.getRequestDispatcher(ErrorConstants.ERRORPAGE);
				dispatcher.forward(request, response);
			} 
		 catch (Exception exception) {
					// TODO Auto-generated catch block
			 
				 request.setAttribute("message", PropertyUtil
							.getErrorMessage(ErrorConstants.FATALERROR));	
				 LOG.error("Exception occured while processing patient diagnosis details", exception.getCause());
					final RequestDispatcher dispatcher = request.getRequestDispatcher(ErrorConstants.FATALERROR);
					dispatcher.forward(request, response);
				}
	}
		}

