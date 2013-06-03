package com.asascience.ioos.model;

public class LatLonPoint {
	Double latitude;
	Double longitude;
	
	public LatLonPoint(Double latitude, Double longitude){
		setLatLon(latitude, longitude);
	}
	
	public void setLatLon(Double latitude, Double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Double getLongitude(){
		return longitude;
	}
	
	public Double getLatitude(){
		return latitude;
	}
	public String toString(){
		return String.valueOf(latitude) + "," + String.valueOf(longitude);
	}
}
