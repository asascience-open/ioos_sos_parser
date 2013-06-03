package com.asascience.ioos.model;

import java.util.ArrayList;
import java.util.List;

public class QualityModel  {
	List<String> allowedTokens;
	String qualityLabel;
	String referenceLink;
	String allowedTokensId;
	
	public QualityModel(){
		qualityLabel = null;
		referenceLink = null;
		allowedTokensId = null;
		allowedTokens = new ArrayList<String>();
	}
	
	public void addAllowedToken(String allowedToken){
		allowedTokens.add(allowedToken);
	}
	public List<String> getAllowedTokens() {
		return allowedTokens;
	}
	public void setAllowedTokens(List<String> allowedTokens) {
		this.allowedTokens = allowedTokens;
	}
	public String getQualityLabel() {
		return qualityLabel;
	}
	public void setQualityLabel(String qualityLabel) {
		this.qualityLabel = qualityLabel;
	}
	public String getReferenceLink() {
		return referenceLink;
	}
	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}
	public String getAllowedTokensId() {
		return allowedTokensId;
	}
	public void setAllowedTokensId(String allowedTokensId) {
		this.allowedTokensId = allowedTokensId;
	}
}
