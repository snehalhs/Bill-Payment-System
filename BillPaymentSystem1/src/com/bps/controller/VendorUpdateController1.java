package com.bps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bps.bo.VendorUpdateBO;
import com.bps.exceptions.VendorUpdateBusinessException;
import com.bps.exceptions.VendorUpdateException;
import com.bps.to.VendorUpdateTo;


// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class VendorUpdateController1.
 */
public class VendorUpdateController1 extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("VendorUpdateController1");  
    
    /**
     * Instantiates a new vendor update controller1.
     *
     * @see HttpServlet#HttpServlet()
     */
    public VendorUpdateController1() {
        super();
        // TODO Auto-generated constructor stub
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
		try
		{
			VendorUpdateTo to=new VendorUpdateTo();			
			to.setCountry(request.getParameter("country"));
			to.setState(request.getParameter("state"));
			to.setAddress(request.getParameter("address"));
			to.setContactnumber(request.getParameter("contact_number"));
			to.setEmailid(request.getParameter("email_id"));
			to.setWebsite(request.getParameter("web_site"));
			
	
			try {
				to.setEmployeecount(Integer.parseInt(request.getParameter("empcount")));	
				to.setCustomercount(Integer.parseInt(request.getParameter("Custcount")));
				to.setVendorid(Integer.parseInt(request.getParameter("vid")));
				request.setAttribute("vid", request.getParameter("vid"));
			} catch (NumberFormatException e) {
				throw new VendorUpdateBusinessException("Employee count and Customer count Should be a Numeric");
			}
			LOG.info("VendorUpdateController1 invoked UserName:" + to.getVendorid());
		
			LOG.info("VendorUpdateController1 Before Sending data to VendorUpdateBO:" + to);
			VendorUpdateBO bo=new VendorUpdateBO();
			if(bo.updateVendor(to))
			{
			
				LOG.info("VendorUpdatecontroller1 Control Transffered to VendorUpdateSuccess.jsp:" );
				RequestDispatcher rd=request.getRequestDispatcher("VendorUpdateSuccess.jsp");
				rd.forward(request, response);	
			}
		}
			catch (VendorUpdateBusinessException e)
			{
				request.setAttribute("message", e.getMessage());
			
				LOG.error("Exception occured when processing vendorupdate:"+ e.getCause());
				RequestDispatcher rd=request.getRequestDispatcher("VendorUpdates.jsp");
				rd.forward(request, response);
			} 
			catch (VendorUpdateException e)
			{
				request.setAttribute("errMessage", e.getMessage());
				LOG.error("Exception occured when processing vendorupdate:"+ e.getCause());
				RequestDispatcher rd=request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}
	}

}
