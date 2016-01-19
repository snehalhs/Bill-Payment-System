package com.bps.controller;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bps.bo.CustomerRegistrationBO;
import com.bps.controller.CustomerRegistrationController;
import com.bps.dao.UserDAO;
import com.bps.exceptions.CustomerRegistrationBussinessException;
import com.bps.exceptions.CustomerRegistrationExecption;
import com.bps.to.UserTo;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerRegistrationController.
 */
public class CustomerRegistrationController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(CustomerRegistrationController.class);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String []typeofvendor= request.getParameterValues("typeofvendor");
			
			UserTo user=new UserTo();
			user.setCustomerName(request.getParameter("cname"));
			user.setAddress(request.getParameter("address"));
			user.setContactNo(request.getParameter("phno"));
			user.setCountry(request.getParameter("mydropdown1"));
			user.setState(request.getParameter("mydropdown2"));
			user.setMailId(request.getParameter("eid"));
			user.setIdentificationDocument(request.getParameter("iddoc"));
			user.setIdNo(request.getParameter("idno"));
			
			 user.setRegistrationDate(request.getParameter("regdate"));
				SimpleDateFormat sf1=new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat sf2=new SimpleDateFormat("dd-MMM-yyyy");
				String oldDate=request.getParameter("regdate");
				Date oldDate1=new Date();
				try {
					 oldDate1=sf1.parse(oldDate);
				} catch (ParseException e1) {
				
					throw new CustomerRegistrationBussinessException("Invalid date format");
				}
				String newDate=sf2.format(oldDate1);
			    user.setRegistrationDate(newDate);
					
			    user.setElectricity("null");
			    user.setTelephone("null");
				user.setInsurance("null");
				user.setTax("null");
				if(typeofvendor.length!=0)
				{
					for(String a:typeofvendor)
					{
						if(a.equals("electricity"))
						{
							user.setElectricity("electricity");					
						}				
						if(a.equals("telephone"))
						{
							user.setTelephone("telephone");
						}				
						if(a.equals("insurance"))
						{
							user.setInsurance("insurance");
						}				
						if(a.equals("tax"))
						{
							user.setTax("tax");
						}					
					}
				}
				LOG.info("CustomerRegistartionController invoked UserName:" + user.getCustomerName());
				
			user.setCardNo(request.getParameter("cardno"));
			user.setBalance(request.getParameter("balance"));
			LOG.info("CustomerRegistartionController invoked UserName:" + user.getCustomerName());
			
			LOG.info("CustomerRegistartionController Before Sending data to CustomerRegistrationBO:" + user);
			CustomerRegistrationBO bo=new CustomerRegistrationBO();
			if(bo.registerUser(user))
			{ int CustomerId=user.getCustomerId();
			request.setAttribute("cid",CustomerId);
				LOG.info("CustomerRegistartionController Control Transffered to Success.jsp:" );
				RequestDispatcher rd=request.getRequestDispatcher("Success.jsp");
				rd.forward(request, response);	
			}
		}
			catch (CustomerRegistrationBussinessException e)
			{
				request.setAttribute("message", e.getMessage());
				LOG.error("Exception occured when processing registerUser:"+ e.getCause());
				RequestDispatcher rd=request.getRequestDispatcher("CustomerRegistration.jsp");
				rd.forward(request, response);
			} 
			catch (CustomerRegistrationExecption e)
			{
				request.setAttribute("errMessage", e.getMessage());
				LOG.error("Exception occured when processing registeruser:"+ e.getCause());
				RequestDispatcher rd=request.getRequestDispatcher("CustomerRegError.jsp");
				rd.forward(request, response);
			}
		}
	}
