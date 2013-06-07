package com.asascience.ioos.model.capabilities;

import com.asascience.ioos.model.AddressModel;

public class ServiceProvider extends AddressModel {
	private String providerName;
	private String providerUrl;
	private String contactName;
	private String contactPhone;
	
	
	public ServiceProvider(){
		providerName = null;
		providerUrl = null;
		contactName = null;
		contactPhone = null;

	}

	public String toString(){
		String strRep = "Provider Name " + providerName + "\n";
		strRep += "Provider URL "  + providerUrl  + "\n";
		strRep += "Contact " + contactName + " " + contactPhone+  "\n"; 
		strRep += super.toString();
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



	
}
