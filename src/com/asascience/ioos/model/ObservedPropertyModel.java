package com.asascience.ioos.model;

import java.util.ArrayList;
import java.util.List;

public class ObservedPropertyModel {
	private String phonemononId;
	private String phonemononName;
	private List<String> observedPropertiesList;
	private Integer dimension;
	public ObservedPropertyModel(){
		observedPropertiesList = new ArrayList<String>();		
	}

	public Integer getDimension(){
		return dimension;
	}
	
	public void setDimension(String dimensionStr){
		if(dimensionStr == null)
			dimension = null;
		else {
			try{
				dimension = Integer.valueOf(dimensionStr);

			}
			catch(NumberFormatException e){
				dimension = null;
				e.printStackTrace();
			}
		}
	}
	
	public void addObservedProperty(String observedProp){
		observedPropertiesList.add(observedProp);
	}
	
	public String getPhonemononId() {
		return phonemononId;
	}

	public void setPhonemononId(String phonemononId) {
		this.phonemononId = phonemononId;
	}

	public String getPhonemononName() {
		return phonemononName;
	}

	public void setPhonemononName(String phonemononName) {
		this.phonemononName = phonemononName;
	}

	public List<String> getObservedPropertiesList() {
		return observedPropertiesList;
	}

	public void setObservedPropertiesList(List<String> observedPropertiesList) {
		this.observedPropertiesList = observedPropertiesList;
	}
	
	public String toString(){
		String repStr = "ID: " + phonemononId + "  name: " + phonemononName + 
				" dimension: "+ dimension +  "\n";
		for(String propStr : observedPropertiesList){
			repStr += propStr + "\n";
		}
		return repStr;
	}
}
