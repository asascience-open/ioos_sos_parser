package com.asascience.ioos.model;

public class Coordinate {
	private String coordinateName;
	private String axisId;
	private String unitOfMeasure;
	private Double coordinateValue;
	private String referenceFrame;
	public Coordinate(){
		coordinateName = null;
		axisId = null;
		unitOfMeasure = null;
		coordinateValue = null;
		referenceFrame = null;
	}
	
	public String getReferenceFrame() {
		return referenceFrame;
	}

	public void setReferenceFrame(String referenceFrame) {
		this.referenceFrame = referenceFrame;
	}

	public String getCoordinateName() {
		return coordinateName;
	}
	public void setCoordinateName(String coordinateName) {
		this.coordinateName = coordinateName;
	}
	public String getAxisId() {
		return axisId;
	}
	public void setAxisId(String axisId) {
		this.axisId = axisId;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public Double getCoordinateValue() {
		return coordinateValue;
	}
	public void setCoordinateValue(Double coordinateValue) {
		this.coordinateValue = coordinateValue;
	}
	public String toString(){
		return  coordinateName + " " + coordinateValue;
	}
}
