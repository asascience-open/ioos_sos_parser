package com.asascience.ioos.model;


import org.joda.time.DateTime;

public class MemberObservation {
	private String description;
	private DateTime startSamplingTime;
	private DateTime endSamplingTime;
	private ObservedPropertyModel observedProperties;
	private FeatureCollectionModel featureCollectionProperties;
	private SweDataRecord sweDataRecords;
	
	public MemberObservation(){
		observedProperties = null;
		featureCollectionProperties = null;
	}

	public FeatureCollectionModel getFeatureCollectionProperties() {
		return featureCollectionProperties;
	}

	public void setFeatureCollectionProperties(
			FeatureCollectionModel featureCollectionProperties) {
		this.featureCollectionProperties = featureCollectionProperties;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartSamplingTime(DateTime startTime){
		startSamplingTime = startTime;
		
	}
	
	public void setEndSamplingTime(DateTime endTime){
		endSamplingTime = endTime;
	}
	

	public String toString() {
		String memberStr =  "description: " + description + "\n";
		if(startSamplingTime != null)
			memberStr +=	"start time: " +startSamplingTime.toString() + "\n";
		if(endSamplingTime != null)
		    memberStr += "end time: " + endSamplingTime.toString() + "\n";
	
		if(observedProperties != null)
			memberStr += observedProperties.toString();
		if(featureCollectionProperties != null)
			memberStr += featureCollectionProperties.toString();
		if(sweDataRecords != null)
			memberStr += sweDataRecords.toString();
		return memberStr;
	}



	public ObservedPropertyModel getObservedProperties() {
		return observedProperties;
	}

	public void setObservedProperties(ObservedPropertyModel observedProperties) {
		this.observedProperties = observedProperties;
	}

	public DateTime getStartSamplingTime() {
		return startSamplingTime;
	}

	public DateTime getEndSamplingTime() {
		return endSamplingTime;
	}

	public SweDataRecord getSweDataRecords() {
		return sweDataRecords;
	}

	public void setSweDataRecords(SweDataRecord sweDataRecords) {
		this.sweDataRecords = sweDataRecords;
	}
}
