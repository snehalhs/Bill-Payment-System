package com.bps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



import com.bps.bo.AdminLoginBO;
import com.bps.constants.ErrorConstants;
import com.bps.exceptions.BusinessException;
import com.bps.exceptions.DatabaseOperationException;

import com.bps.to.LoginTO;
import com.bps.util.PropertyUtil;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class AdminLoginController.
 */
public class AdminLoginController extends HttpServlet {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("AdminLoginController");
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new admin login controller.
     *
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginController() {
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
		HttpSession session=request.getSession(true);
		session.setAttribute("AdminName", request.getParameter("AdminName"));
		
	
	
		LoginTO login=new LoginTO();
		
		
		AdminLoginBO loginbo=new AdminLoginBO();
		
		try {
			String user_Name=request.getParameter("AdminName");
			
			String user_Password=request.getParameter("password");
			LOG.info("Username and password in AdminLoginController " + user_Name+ "  " +user_Password);
			login.setUserName(user_Name);
			login.setUserPassword(user_Password);
			if(loginbo.validateUserName(user_Name,user_Password)){
				LOG.info("Inside AdminLoginController after validation ");
				RequestDispatcher rd= request.getRequestDispatcher("homepage.jsp");
			
				  
			rd.forward(request, response);
				
	}
			else
			{
				response.sendRedirect("adminerror.jsp");
			}

}
		catch(BusinessException be){
			
				request.setAttribute("message1",be.getMessage());
				
				final RequestDispatcher dispatcher = request.getRequestDispatcher("adminerror.jsp");
				dispatcher.forward(request, response);
			}
	catch (DatabaseOperationException databaseOperationException) {// Handles the LoginExceptions and log the
			// errors into the Log	
			
			request.setAttribute("message", PropertyUtil
							.getErrorMessage(ErrorConstants.FATALERROR));	
			LOG.error("Exception occured while processing admin login details", databaseOperationException.getCause());
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		} 
	 catch (Exception exception) {				
		 		
			 request.setAttribute("message", PropertyUtil.getErrorMessage(ErrorConstants.FATALERROR));	
			 LOG.error("Exception occured while processing admin login details", exception.getCause());
				final RequestDispatcher dispatcher = request.getRequestDispatcher(ErrorConstants.FATALERROR);
				dispatcher.forward(request, response);
			}
	}
}
