package com.asascience.ioos.model;


import org.joda.time.DateTime;

public class MemberObservation extends BaseObservationModel {
	
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


	
	@Override
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



	public SweDataRecord getSweDataRecords() {
		return sweDataRecords;
	}

	public void setSweDataRecords(SweDataRecord sweDataRecords) {
		this.sweDataRecords = sweDataRecords;
	}
}
