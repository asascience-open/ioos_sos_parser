package com.asascience.ioos.model.capabilities;

import java.util.ArrayList;
import java.util.List;

public class ServiceIdentification {
	private String title;
	private String abstractOfService;
	private List<String> keywordList;
	private String serviceType;
	private String serviceTypeVersion;
	private String feesForService;
	private String accessConstraints;
	
	public ServiceIdentification(){
		keywordList = new ArrayList<String>();
	}

	public String getTitle() {
		return title;
	}

	public String toString(){
		String strRep = "Title: " + title +"\n";
		strRep += "Abstract: " +abstractOfService +"\n";
		strRep += "Keywords \n";
		for(String kw : keywordList)
			strRep += " " + kw +"\n";
		strRep += "Service type" +serviceType + " version " +serviceTypeVersion +"\n";
		strRep += "Fees " + feesForService +"\n";
		strRep += "Access Constraints: " +accessConstraints +"\n";
		return strRep;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstractOfService() {
		return abstractOfService;
	}

	public void setAbstractOfService(String abstractOfService) {
		this.abstractOfService = abstractOfService;
	}

	public List<String> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<String> keywordList) {
		this.keywordList = keywordList;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceTypeVersion() {
		return serviceTypeVersion;
	}

	public void setServiceTypeVersion(String serviceTypeVersion) {
		this.serviceTypeVersion = serviceTypeVersion;
	}

	public String getFeesForService() {
		return feesForService;
	}

	public void setFeesForService(String feesForService) {
		this.feesForService = feesForService;
	}

	public String getAccessConstraints() {
		return accessConstraints;
	}

	public void setAccessConstraints(String accessConstraints) {
		this.accessConstraints = accessConstraints;
	}
	
	
}
