package com.bps.to;

public class CustomerUpdateTO {
	
	private int customerid;
	private String customername;
	private String customercountry;
	private String customerstate;
	private String customeraddress;
	private String countryid;
	private String contactno;
	private String mailid;
	private String electricity;
	private String telephone;
	private String insurance;
	private String tax;
	private String cardno;
	private String balance;
	
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustomercountry() {
		return customercountry;
	}
	public void setCustomercountry(String customercountry) {
		this.customercountry = customercountry;
	}
	public String getCustomerstate() {
		return customerstate;
	}
	public void setCustomerstate(String customerstate) {
		this.customerstate = customerstate;
	}
	public String getCustomeraddress() {
		return customeraddress;
	}
	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getElectricity() {
		return electricity;
	}
	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "customerUpdateto [customer_id=" + customerid
				+ ", customername=" + customername + ", customercountry="
				+ customercountry + ", customerstate=" + customerstate
				+ ", customeraddress=" + customeraddress + ", countryid="
				+ countryid + ", contactno=" + contactno + ", mailid="
				+ mailid + ", electricity=" + electricity + ", telephone="
				+ telephone + ", insurance=" + insurance + ", tax=" + tax
				+ ", cardno=" + cardno + ", balance=" + balance + "]";
	}	
}
