package com.asascience.ioos.model;


public class SensorProperty extends PropertyObject {

	
	private PropertyType propertyType;
	private String nillValue;
	
	public enum PropertyType{
		TIME,
		VECTOR,
		QUANTITY,
		QUANTITY_RANGE,
		QUALITY,
		COUNT,
		INDEX
	}
	
	public SensorProperty(){
	}

	
	
	public SensorProperty getCopy(){
		SensorProperty copy = new SensorProperty();
		copy.propertyType = propertyType;
		copy.sensorUnitOfMeasure = sensorUnitOfMeasure;
		copy.qualityMeasure = qualityMeasure;
		return copy;
	}
	

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public String getNillValue() {
		return nillValue;
	}

	public void setNillValue(String nillValue) {
		this.nillValue = nillValue;
	}
	
	
}
