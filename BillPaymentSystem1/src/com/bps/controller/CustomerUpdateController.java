package com.bps.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.CustomValue;
import com.bps.exceptions.CustomerUpdateBusinessException;
import com.bps.exceptions.CustomerUpdateException;
import com.bps.to.CustomerUpdateTO;
import com.bps.bo.CustomerUpdateBO;
import com.bps.dao.CustomerUpdateDAO;

/**
 * Servlet implementation class customerUpdateController
 */
public class CustomerUpdateController extends HttpServlet {
	public static final Logger LOG = Logger.getLogger("CustomerUpdateController");
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		CustomerUpdateTO user=new CustomerUpdateTO();
		int cid=0;
		try{			
			try{
				cid=(Integer.parseInt(request.getParameter("cid")));		
			}catch(NumberFormatException e)
			{
				throw new CustomerUpdateBusinessException("Customer Id should be Numeric");
			}
			
			CustomerUpdateBO bo= new CustomerUpdateBO();
			if(bo.searchDetails(cid))
			{								
				LOG.info("CustomerUpdateController Control Transffered to CustomerUpdateDetails.jsp:" );
				CustomerUpdateTO to=bo.displayDetails(cid);
				LOG.info("Data Returned from CustomerUpdateBO :"+to);
				{
					if(to!=null)
					{
						request.setAttribute("result", to);
						LOG.info("Data Set to Request Object in Controller");
						RequestDispatcher rd=request.getRequestDispatcher("CustomerUpdateDetails.jsp");
						rd.forward(request, response);
					}
				}
			}					
		}catch (CustomerUpdateBusinessException e) {			
			request.setAttribute("message", e.getMessage());
			LOG.error("Exception occured when processing searchDetailsdao:"+ e.getCause());
			RequestDispatcher rd=request.getRequestDispatcher("CustomerUpdateSearch.jsp");			
			rd.forward(request, response);
		} catch (CustomerUpdateException e) {			
			request.setAttribute("errMessage", e.getMessage());
			LOG.error("Exception occured when processing searchDetailsdao:"+ e.getCause());
			RequestDispatcher rd=request.getRequestDispatcher("CustomerUpdateError.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {			
			e.getMessage();
		}		
	}
}
