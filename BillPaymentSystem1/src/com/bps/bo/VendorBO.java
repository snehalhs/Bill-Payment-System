package com.bps.bo;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bps.dao.VendorDAO;
import com.bps.exceptions.RegistrationBusinessException;
import com.bps.exceptions.RegistrationException;

import com.bps.to.VendorTo;

// TODO: Auto-generated Javadoc
/**
 * The Class VendorBO.
 */
public class VendorBO 
{
	public static final Logger LOG = Logger.getLogger("VendorBO");
	/** The dao. */
	VendorDAO dao=new VendorDAO();
	
	/** The rto. */
	VendorTo vendor = new VendorTo();
	
	/**
	 * Gets the certificate.
	 *
	 * @param year the year
	 * @param emp the emp
	 * @return the certificate
	 */
	public String getCertificate(int year, int emp)
	{
		String certi;
		if(year>1 && year<=5 && emp>=30 && emp<50)
			certi="A+";
		else if(year>5 && year<=50 && emp>=50 && emp<75)
			certi="B+";
		else if(year>10 && year<=15 && emp>=75 && emp<100)
			certi="C+";
		else if(year>15 && year<=25 && emp>=100 && emp<200)
			certi="D+";
		else if(year>25 && year<=50 && emp>=200 && emp<500)
			certi="C+";
		else
			certi="F+";
		return certi;
	}
	
	/**
	 * Gets the vendor details.
	 *
	 * @param regnum the regnum
	 * @return the vendor details
	 * @throws SQLException the sQL exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public VendorTo getVendorDetails(String regnum) throws SQLException, ClassNotFoundException
	{
		LOG.info("Method getVendorDetails Invoked in VendorBO" + regnum);
		vendor = dao.getDetails(regnum);		
		LOG.info("Returned Value in getVendorDetails BO:"+vendor);
		return vendor;		
	}
	
	
	/**
	 * Register vendorbo.
	 *
	 * @param vendor the vendor
	 * @return true, if successful
	 * @throws RegistrationException the registration exception
	 * @throws RegistrationBusinessException the registration business exception
	 */
	public boolean registerVendorbo(VendorTo vendor) throws RegistrationException,RegistrationBusinessException
	{
		LOG.info("Method registerVendorbo Invoked in VendorBO" + vendor);
		boolean successflag=false;
		boolean b1=false;
		boolean b2=false;
		int m=0;
		String cnum=vendor.getContactnumber();
		char[] num=cnum.toCharArray();
		int l2=num.length;
		for(int i=0;i<l2;i++)
		{
			if(Character.isDigit(num[i]))
				m++;
		}if(m==10)
			b1=true;
		else
			throw new RegistrationBusinessException("contact number should be 10 digits");
		 String website=vendor.getWebsite();
		 String web=website.toLowerCase();
		 if(web.startsWith("www.")&&web.endsWith(".com"))
			 b2=true;
		 else
			 throw new RegistrationBusinessException("website is in the wrong format");
		
		 if(b1==true && b2==true)
		 successflag=dao.registerVendor(vendor);
		 LOG.info("Returned Value in registerVendorbo BO:"+successflag);
		 return successflag;
		
	}
}