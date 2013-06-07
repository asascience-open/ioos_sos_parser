package com.asascience.ioos.model.describe;

import com.asascience.ioos.model.AddressModel;

public class ContactData extends AddressModel {
	private String organizationName;
	private String onlineResource;
	
	public ContactData(){
		
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOnlineResource() {
		return onlineResource;
	}

	public void setOnlineResource(String onlineResource) {
		this.onlineResource = onlineResource;
	}
	
	public String toString(){
		return "Organization: " + organizationName +"\n"
					   + super.toString() + "Resource:  "  + onlineResource +"\n";
	}
}
