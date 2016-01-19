package com.bps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import com.bps.bo.CustomerUpdateBO;
import com.bps.exceptions.CustomerUpdateBusinessException;
import com.bps.exceptions.CustomerUpdateException;
import com.bps.to.CustomerUpdateTO;

/**
 * Servlet implementation class cupdate
 */
public class CustomerUpdate extends HttpServlet {
	public static final Logger LOG = Logger.getLogger("CustomerUpdate");
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try
		{						
			String [] typeofvendor= request.getParameterValues("typeofvendor");			
			CustomerUpdateTO to=new CustomerUpdateTO();			
			to.setCustomername(request.getParameter("customer_name"));
			to.setCustomeraddress(request.getParameter("customer_address"));		
			to.setMailid(request.getParameter("mail_id"));						
			to.setBalance(request.getParameter("balance"));
			to.setCustomercountry(request.getParameter("customer_country"));
			to.setCustomerstate(request.getParameter("customer_state"));
			to.setContactno(request.getParameter("contact_no"));	
			to.setCardno(request.getParameter("card_no"));
			to.setElectricity("null");
			to.setTelephone("null");
			to.setInsurance("null");
			to.setTax("null");			
			
			List<String> typeofvendor1=Arrays.asList(typeofvendor);		
			
			if(typeofvendor1.size()>0)
			{
				for(String a:typeofvendor1)
				{
					if(a.equals("electricity"))
					{
						to.setElectricity("electricity");					
					}				
					if(a.equals("telephone"))
					{
						to.setTelephone("telephone");
					}				
					if(a.equals("insurance"))
					{
						to.setInsurance("insurance");
					}				
					if(a.equals("tax"))
					{
						to.setTax("tax");
					}					
				}
			}
			LOG.info("CustomerUpdate invoked Customer_name:" + to.getCustomername());			
			try {
				
				to.setCustomerid(Integer.parseInt(request.getParameter("customer_id")));
			} catch (NumberFormatException e) {
				throw new CustomerUpdateBusinessException("contact no. and card no. Should be a Numeric");
			}
			
			LOG.info("CustomerUpdate Before Sending data to CustomerUpdateBO:" + to);
			CustomerUpdateBO bo=new CustomerUpdateBO();
			if(bo.updateCustomer(to))
			{		request.setAttribute("cid",request.getParameter("customer_id") );		
				LOG.info("CustomerUpdate Control Transffered to CustomerUpdateSuccess.jsp:" );
				RequestDispatcher rd=request.getRequestDispatcher("CustomerUpdateSuccess.jsp");
				rd.forward(request, response);	
			}
		}
		catch (CustomerUpdateBusinessException e)
		{
				request.setAttribute("message", e.getMessage());
				LOG.error("Exception occured when processing updateCustomer: "+ e.getCause());
				
				RequestDispatcher rd=request.getRequestDispatcher("CustomerUpdateDetails.jsp");
				rd.include(request, response);
		} 
		catch (CustomerUpdateException e)
		{
				request.setAttribute("errMessage", e.getMessage());
				LOG.error("Exception occured when processing updateCustomer: "+ e.getCause());
				RequestDispatcher rd=request.getRequestDispatcher("CustomerUpdateError.jsp");
				rd.forward(request, response);
		} catch (SQLException e) {			
			e.getMessage();
		}		
	}
}
