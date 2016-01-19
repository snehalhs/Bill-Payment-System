package com.bps.bo;

import java.util.*;


import org.apache.log4j.Logger;

import com.bps.dao.VendorUpdateDAO;
import com.bps.exceptions.VendorUpdateBusinessException;
import com.bps.exceptions.VendorUpdateException;
import com.bps.to.VendorUpdateTo;



// TODO: Auto-generated Javadoc
/**
 * The Class VendorUpdateBO.
 */
public class VendorUpdateBO {
		
		/** The Constant LOG. */
		public static final Logger LOG = Logger.getLogger("VendorUpdateBO");
		
		/** The dao. */
		VendorUpdateDAO dao= new VendorUpdateDAO();
		
		/**
		 * Search details of vendor.
		 *
		 * @param vid1 the vid1
		 * @return true, if successful
		 * @throws VendorUpdateBusinessException the vendor update business exception
		 * @throws VendorUpdateException the vendor update exception
		 */
		public boolean searchDetails(int vid1)throws VendorUpdateBusinessException,VendorUpdateException
		{
			LOG.info("Method searchDetails Invoked in VendorUpdateBO" + vid1);	
			boolean flag=false;			
			try {
				flag=dao.searchDetailsdao(vid1);					
				if(flag==false){
					throw new VendorUpdateBusinessException("Please enter a valid Vendor Id");
				}
			} catch (VendorUpdateBusinessException e) {				
				throw new VendorUpdateBusinessException("Please enter a valid Vendor Id");
			} 
			
			LOG.info("Return value from searchDetails Method in VendorUpdateBO" + flag);		
			return flag;			
		}
		
		/**
		 * Display details of vendor.
		 *
		 * @param vid the vid
		 * @return the vendor update to
		 * @throws VendorUpdateBusinessException the vendor update business exception
		 * @throws VendorUpdateException the vendor update exception
		 */
		public VendorUpdateTo displayDetails(int vid)throws VendorUpdateBusinessException,VendorUpdateException
		{
			LOG.info("Method displayDetails Invoked in VendorUpdateBO" + vid);			
			VendorUpdateTo to=dao.displayDetails(vid);		
			LOG.info("Return value from displayDetails Method in VendorUpdateBO" +to);		
			return to;
	}
		
		/**
		 * Update vendor details.
		 *
		 * @param to the to
		 * @return true, if successful
		 * @throws VendorUpdateBusinessException the vendor update business exception
		 * @throws VendorUpdateException the vendor update exception
		 */
		public boolean updateVendor(VendorUpdateTo to) throws VendorUpdateBusinessException,VendorUpdateException
		{
			LOG.info("Method updateVendor Invoked in VendorUpdateBO" + to);
			boolean successflag=true;		
			successflag=dao.updateVendor(to);			
			LOG.info("Return value from displayDetails Method in VendorUpdateBO" +successflag);
			return successflag;		
		}
}
