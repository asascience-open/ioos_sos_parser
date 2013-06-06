package com.asascience.ioos.model.capabilities;

public class ServiceProvider {
	private String providerName;
	private String providerUrl;
	private String contactName;
	private String contactPhone;
	private String addressDeliveryPoint;
	private String addressCity;
	private String addressAdminArea;
	private String addressZipCode;
	private String addressCountry;
	private String contactEmail;
	
	public ServiceProvider(){
		providerName = null;
		providerUrl = null;
		contactName = null;
		contactPhone = null;
		addressDeliveryPoint = null;
		addressCity = null;
		 addressAdminArea = null;
		addressZipCode = null;
		addressCountry = null;
		contactEmail = null;
	}

	public String toString(){
		String strRep = "Provider Name " + providerName + "\n";
		strRep += "Provider URL "  + providerUrl  + "\n";
		strRep += "Contact " + contactName + " " + contactPhone+  "\n"; 
		strRep += addressDeliveryPoint  + "\n";
		strRep += addressCity + ", " + addressAdminArea + " " + addressZipCode  + "\n";
		strRep += addressCountry + "\n";
		strRep += contactEmail  + "\n";
		return strRep;
	}
	
	
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getAddressDeliveryPoint() {
		return addressDeliveryPoint;
	}

	public void setAddressDeliveryPoint(String addressDeliveryPoint) {
		this.addressDeliveryPoint = addressDeliveryPoint;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressAdminArea() {
		return addressAdminArea;
	}

	public void setAddressAdminArea(String addressAdminArea) {
		this.addressAdminArea = addressAdminArea;
	}

	public String getAddressZipCode() {
		return addressZipCode;
	}

	public void setAddressZipCode(String addressZipCode) {
		this.addressZipCode = addressZipCode;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
}
