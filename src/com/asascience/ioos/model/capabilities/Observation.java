package com.asascience.ioos.model.capabilities;

import java.util.ArrayList;
import java.util.List;

import com.asascience.ioos.model.BaseObservationModel;
import com.asascience.ioos.model.BoundingBox;
import com.asascience.ioos.model.LatLonPoint;

public class Observation extends BaseObservationModel{
	String procedureLink;
	List<String> observedPropertiesRefList;
	String featureOfInterestLink;
	String responseFormat;
	String resultModel;
	String responseMode;
	String offeringID;
	BoundingBox boundingBox;
	public Observation(){
		observedPropertiesRefList = new ArrayList<String>();
	}
	
	public String toString() {
		String strRep = super.toString();
		strRep += "procedure: " + procedureLink + "\n";
		strRep += "feature of interest: " + featureOfInterestLink  + "\n";
		strRep += "response format: " + responseFormat + "\n";
		strRep += "result model: " + resultModel  + "\n";
		strRep += "response mode: " + responseMode  + "\n";
		strRep += boundingBox.toString();
		return strRep;
	}
	public String getProcedureLink() {
		return procedureLink;
	}
	public void setProcedureLink(String procedureLink) {
		this.procedureLink = procedureLink;
	}
	public List<String> getObservedPropertiesRefList() {
		return observedPropertiesRefList;
	}
	public void setObservedPropertiesRefList(List<String> observedPropertiesRefList) {
		this.observedPropertiesRefList = observedPropertiesRefList;
	}
	public String getFeatureOfInterestLink() {
		return featureOfInterestLink;
	}
	public void setFeatureOfInterestLink(String featureOfInterestLink) {
		this.featureOfInterestLink = featureOfInterestLink;
	}
	public String getResponseFormat() {
		return responseFormat;
	}
	public void setResponseFormat(String responseFormat) {
		this.responseFormat = responseFormat;
	}
	public String getResultModel() {
		return resultModel;
	}
	public void setResultModel(String resultModel) {
		this.resultModel = resultModel;
	}
	public String getResponseMode() {
		return responseMode;
	}
	public void setResponseMode(String responseMode) {
		this.responseMode = responseMode;
	}


	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	public String getOfferingID() {
		return offeringID;
	}

	public void setOfferingID(String offeringID) {
		this.offeringID = offeringID;
	}
}
