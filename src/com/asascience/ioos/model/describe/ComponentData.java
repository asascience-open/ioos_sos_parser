package com.asascience.ioos.model.describe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.asascience.ioos.model.LatLonPoint;

public class ComponentData {
	String componentName;
	Map<String, DescriptionObject> identificationMap;
	DateTime componentStartDate;
	DateTime componentEndDate;
	List<LatLonPoint> locationPoints;
	Map<String, QuantityObject> outputQuantitiesMap;
	Map<String, String> quantitiesUnitMeasureMap;
	String description;
	String identificationReference;
	String documentationReference;
	public ComponentData(){
		identificationMap = new HashMap<String, DescriptionObject>();
		locationPoints = new ArrayList<LatLonPoint>();
		outputQuantitiesMap = new HashMap<String, QuantityObject>();
		
	}



	public Map<String, DescriptionObject> getIdentificationMap() {
		return identificationMap;
	}


	public String toString(){
		String strRep = "Component name: " + componentName + "\n" +
					    "Description: " + description + "\n" + 
				         "Identiciation Reference: " + identificationReference + "\n"+
					    "Documentation Reference " + documentationReference +"\n";
		if(componentStartDate != null)
			strRep +=  " Start Date: " + componentStartDate.toString() +"\n";
		if(componentEndDate != null)
			strRep += " End Date: " + componentEndDate.toString() +"\n";
		
		strRep +=  " Identifiers: \n";
		for(String key : identificationMap.keySet()){
			strRep += key + "\n";
			strRep += "   " + identificationMap.get(key).toString();
		}
		strRep += " Location: \n";
		for(LatLonPoint ll : locationPoints){
			strRep += "   " + ll.toString() +"\n";
		}
		strRep += "  Output\n";
		for(String key : outputQuantitiesMap.keySet()){
			strRep += key + "\n";
			strRep += "   " + outputQuantitiesMap.get(key).toString() +"\n";
		}
		return strRep;
	}
	

	public void setIdentificationMap(
			Map<String, DescriptionObject> identificationMap) {
		this.identificationMap = identificationMap;
	}



	public DateTime getComponentStartDate() {
		return componentStartDate;
	}

	public void setComponentStartDate(DateTime componentStartDate) {
		this.componentStartDate = componentStartDate;
	}

	public DateTime getComponentEndDate() {
		return componentEndDate;
	}

	public void setComponentEndDate(DateTime componentEndDate) {
		this.componentEndDate = componentEndDate;
	}

	public List<LatLonPoint> getLocationPoints() {
		return locationPoints;
	}

	public void setLocationPoints(List<LatLonPoint> locationPoints) {
		this.locationPoints = locationPoints;
	}




	public Map<String, QuantityObject> getOutputQuantitiesMap() {
		return outputQuantitiesMap;
	}



	public void setOutputQuantitiesMap(
			Map<String, QuantityObject> outputQuantitiesMap) {
		this.outputQuantitiesMap = outputQuantitiesMap;
	}



	public Map<String, String> getQuantitiesUnitMeasureMap() {
		return quantitiesUnitMeasureMap;
	}



	public void setQuantitiesUnitMeasureMap(
			Map<String, String> quantitiesUnitMeasureMap) {
		this.quantitiesUnitMeasureMap = quantitiesUnitMeasureMap;
	}



	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getIdentificationReference() {
		return identificationReference;
	}



	public void setIdentificationReference(String identificationReference) {
		this.identificationReference = identificationReference;
	}



	public String getDocumentationReference() {
		return documentationReference;
	}



	public void setDocumentationReference(String documentationReference) {
		this.documentationReference = documentationReference;
	}
	
}
