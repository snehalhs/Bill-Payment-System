package com.bps.controller;

import java.util.*;
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bps.to.VendorTo;
import com.bps.bo.VendorBO;
import com.bps.exceptions.RegistrationBusinessException;
import com.bps.exceptions.RegistrationException;

// TODO: Auto-generated Javadoc

/**
 * Servlet implementation class VedorRegistrationController.
 */
public class VendorRegistrationController extends HttpServlet {
	public static final Logger LOG = Logger.getLogger("VendorRegistrationController");
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date d=new Date();
		int ye=d.getYear()+1900;
		VendorTo vendor=new VendorTo();
		try{
		vendor.setVendorname(request.getParameter("VendorName"));
		vendor.setVendortype(request.getParameter("VendorType"));
		vendor.setVendorregnumber(request.getParameter("VendorReg"));
		vendor.setAddress(request.getParameter("address"));
		vendor.setContactnumber(request.getParameter("contact_number"));
		vendor.setEmailid(request.getParameter("email_id"));
		vendor.setWebsite(request.getParameter("web_site"));
		vendor.setCertificateissuedate(request.getParameter("certificate_issue_date"));
		vendor.setCertificatevaliddate(request.getParameter("certificate_valid_date"));
		vendor.setCountry(request.getParameter("mydropdown1"));
		vendor.setState(request.getParameter("mydropdown2"));
		
		try {
			vendor.setEmployeecount(Integer.parseInt(request.getParameter("employee_count")));
			vendor.setCustomercount(Integer.parseInt(request.getParameter("customer_count")));
			vendor.setYoe(Integer.parseInt(request.getParameter("yoe")));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			
		}
		vendor.setYos(ye-vendor.getYoe());
	
		
		VendorBO bo=new VendorBO();
		if(bo.registerVendorbo(vendor))
		{	
			int vendorId=vendor.getVendorid();
			request.setAttribute("vid",vendorId);
		LOG.info("VendorRegistrationController Control Transffered to Success.jsp:" );
		
		RequestDispatcher rd=request.getRequestDispatcher("VendorRegisterationsuccess.jsp");
		rd.forward(request,response);	
	}
}
	catch (RegistrationBusinessException e)
	{
		
		request.setAttribute("message", e.getMessage());
		LOG.error("Exception occured when processing registerVendorbo :" + e.getCause());
		request.setAttribute("result", vendor);
		RequestDispatcher rd=request.getRequestDispatcher("VendorRegister.jsp");
		rd.forward(request, response);
	} 
catch (RegistrationException e)
	{
	
		request.setAttribute("errMessage", e.getMessage());
		LOG.error("Exception occured when processing registerVendorbo:"+ e.getCause());
		RequestDispatcher rd=request.getRequestDispatcher("VendorRegisterationError.jsp");
		rd.forward(request,response);
	}
}
}



