package com.bps.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bps.bo.VendorUpdateBO;
import com.bps.exceptions.VendorUpdateBusinessException;
import com.bps.exceptions.VendorUpdateException;
import com.bps.to.VendorUpdateTo;


// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class VendorUpdateController.
 */
public class VendorUpdateController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("VendorUpdateController");
    
    /**
     * Instantiates a new vendor update controller.
     *
     * @see HttpServlet#HttpServlet()
     */
    public VendorUpdateController() {
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
		int vid=0;
		HttpSession httpSession = request.getSession();
		
		try{
			
			try{
				vid=(Integer.parseInt(request.getParameter("vendor_id")));		
			}catch(NumberFormatException e)
			{
				throw new VendorUpdateBusinessException(e);
			}
			
			VendorUpdateBO bo= new VendorUpdateBO();
			if(bo.searchDetails(vid))
			{
				
				VendorUpdateTo to=new VendorUpdateTo();			
				to=bo.displayDetails(vid);
				
				{
					if(to!=null)
					{
						httpSession.setAttribute("result", to);
						LOG.info("Data Set to Request Object in Controller");
					
						LOG.info("RegistartionController Control Transffered to VendorUpdates.jsp:" );
						RequestDispatcher rd=request.getRequestDispatcher("VendorUpdates.jsp");
						rd.forward(request, response);
					}
				}
			}

			}catch (VendorUpdateBusinessException e) {			
				request.setAttribute("message", e.getMessage());
				LOG.error("Exception occured when processing vendorupdate:"+ e.getCause());
				RequestDispatcher rd=request.getRequestDispatcher("VendorSearch.jsp");
				rd.forward(request, response);
			} catch (VendorUpdateException e) {
				// TODO Auto-generated catch block
				request.setAttribute("errMessage", e.getMessage());
				LOG.error("Exception occured when processing vendorupdate:"+ e.getCause());
				RequestDispatcher rd=request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}
				
	}

}
