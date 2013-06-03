package com.asascience.ioos.model;

public class PropertyObject {
	protected String propertyType;
	protected String sensorUnitOfMeasure;
	protected QualityModel qualityMeasure;
	public PropertyObject(){
		qualityMeasure = null;
	}
	public void setSensorType(String sensorType) {
		this.propertyType = sensorType;
	}

	public String getSensorUnitOfMeasure() {
		return sensorUnitOfMeasure;
	}

	public void setSensorUnitOfMeasure(String sensorUnitOfMeasure) {
		this.sensorUnitOfMeasure = sensorUnitOfMeasure;
	}

	public QualityModel getQualityMeasure() {
		return qualityMeasure;
	}

	public void setQualityMeasure(QualityModel qualityMeasure) {
		this.qualityMeasure = qualityMeasure;
	}


	public String getSensorType() {
		return propertyType;
	}

}
