package com.asascience.ioos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationModel {
	String stationName;
	VectorModel platformLocation;
    //Map the sensors to the sensor model record
	Map<String, SensorModel> sensorIdtoSensorDataMap;
	 List<QualityModel> stationQuality;
		
	 //Map sensor short id to full id
	Map<String, String> sensorIdMap;
		
	 public StationModel(){
			sensorIdtoSensorDataMap = new HashMap<String, SensorModel>();
			stationQuality = new ArrayList<QualityModel>();
			sensorIdMap = new HashMap<String, String>();

			platformLocation = new VectorModel();
	 }

	public VectorModel getPlatformLocation() {
		return platformLocation;
	}

	public void setPlatformLocation(VectorModel platformLocation) {
		this.platformLocation = platformLocation;
	}

	public Map<String, SensorModel> getSensorIdtoSensorDataMap() {
		return sensorIdtoSensorDataMap;
	}

	public void setSensorIdtoSensorDataMap(
			Map<String, SensorModel> sensorIdtoSensorDataMap) {
		this.sensorIdtoSensorDataMap = sensorIdtoSensorDataMap;
	}

	public List<QualityModel> getStationQuality() {
		return stationQuality;
	}

	public void setStationQuality(List<QualityModel> stationQuality) {
		this.stationQuality = stationQuality;
	}

	public String toString(){
		String strRep = "Station: " + stationName + " Location: " + platformLocation.toString() +"\n"; 
		for(SensorModel sensor : sensorIdtoSensorDataMap.values() ){
			strRep += "   sensor " +  sensor.toString() + "\n";
		}
		return strRep;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	public Map<String, String> getSensorIdMap() {
		return sensorIdMap;
	}

	public void setSensorIdMap(Map<String, String> sensorIdMap) {
		this.sensorIdMap = sensorIdMap;
	}
}
