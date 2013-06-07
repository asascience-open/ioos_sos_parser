package com.asascience.ioos.model;

public class AddressModel {
	protected String addressDeliveryPoint;
	protected String addressCity;
	protected String addressAdminArea;
	protected String addressZipCode;
	protected String addressCountry;
	protected String contactEmail;
	protected String contactPhone;

	public AddressModel(){
		addressDeliveryPoint = null;
		addressCity = null;
		 addressAdminArea = null;
		addressZipCode = null;
		addressCountry = null;
		contactEmail = null;
	}

	public String toString(){
		return addressDeliveryPoint + "\n" + addressCity + ", " + addressAdminArea + 
				" " + addressZipCode + "\n" + addressCountry + "\n" + contactEmail +"\n" +
				contactPhone +"\n";
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
	
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
}
