package com.asascience.ioos.model;


import org.joda.time.DateTime;

public class BaseObservationModel {
	protected String description;
	protected String observationName;
	protected String observationSrsName;
	protected DateTime startSamplingTime;
	protected DateTime endSamplingTime;
	protected ObservedPropertyModel observedProperties;
	
	
	public BaseObservationModel(){
		observedProperties = null;
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
		String memberStr =  "ID " + observationName + "\n" +
				"description: " + description + "\n";
		if(startSamplingTime != null)
			memberStr +=	"start time: " +startSamplingTime.toString() + "\n";
		if(endSamplingTime != null)
		    memberStr += "end time: " + endSamplingTime.toString() + "\n";
	
		if(observedProperties != null)
			memberStr += observedProperties.toString();

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



	public String getObservationName() {
		return observationName;
	}



	public void setObservationName(String observationName) {
		this.observationName = observationName;
	}



	public String getObservationSrsName() {
		return observationSrsName;
	}



	public void setObservationSrsName(String observationSrsName) {
		this.observationSrsName = observationSrsName;
	}

}
