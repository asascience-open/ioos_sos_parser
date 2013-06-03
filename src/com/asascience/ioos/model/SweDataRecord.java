package com.asascience.ioos.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SweDataRecord {
	
	// Maps the short stationID to the URN station ID
	Map<String, String> stationIdMap;
	
	// Maps the short stationID to the loccation
	Map<String, VectorModel> stationPlatformLocationMap;
	
	// Maps the station to a quality list
	Map<String, List<QualityModel>> stationQualityMap;
	
    //Map the sensors to the sensor model record
	Map<String, SensorModel> sensorIdtoSensorDataMap;
	
	
	//Map sensor short id to full id
	Map<String, String> sensorIdMap;
	
	Integer numberDataRows;
	Integer timeColumn;
	Integer sectorStartColumn;
	public SweDataRecord(){
		stationIdMap = new HashMap<String,String>();
		stationPlatformLocationMap = new HashMap<String, VectorModel>();
		sensorIdtoSensorDataMap = new HashMap<String, SensorModel>();
		stationQualityMap = new HashMap<String, List<QualityModel>>();
		sensorIdMap = new HashMap<String, String>();

		numberDataRows = 0;
	}
	
	
	
	public void addQualityMapEntry(String stationKey, List<QualityModel> qualityModel ){
		stationQualityMap.put(stationKey, qualityModel);
	}
	
	public Map<String, List<QualityModel>> getStationQualityMap() {
		return stationQualityMap;
	}


	public void setStationQualityMap(
			Map<String, List<QualityModel>> stationQualityMap) {
		this.stationQualityMap = stationQualityMap;
	}


	

	public Integer getNumberDataRows() {
		return numberDataRows;
	}

	public void setNumberDataRows(Integer numberDataRows) {
		this.numberDataRows = numberDataRows;
	}

	


	public void addPlatformLocationData(String stationShortKey, VectorModel stationLocation){
		stationPlatformLocationMap.put(stationShortKey, stationLocation);
	}
	
	public Map<String, VectorModel> getStationPlatformLocationMap() {
		return stationPlatformLocationMap;
	}

	public void setStationPlatformLocationMap(
			Map<String, VectorModel> stationPlatformLocationMap) {
		this.stationPlatformLocationMap = stationPlatformLocationMap;
	}

	public void addStationIdMap(String shortIDKey, String stationIdKey){
		System.out.println("adding "+shortIDKey + " " +stationIdKey);
		stationIdMap.put(shortIDKey, stationIdKey);
		System.out.println(stationIdMap.size());
	}

	public Map<String, String> getStationIdMap() {
		return stationIdMap;
	}

	public void setStationIdMap(Map<String, String> stationIdMap) {
		this.stationIdMap = stationIdMap;
	}
	
	public String toString(){
		String strRep = "";
		for(String stationId : stationIdMap.keySet()){
			VectorModel vecMod = stationPlatformLocationMap.get(stationId);
			strRep += "sweStation: " + stationId + " sensors=" +
					vecMod.toString() + "\n";
					for(SensorModel sensor : sensorIdtoSensorDataMap.values() ){
						strRep += "   sensor " + sensor.toString() + "\n";
						
						}
					}
		
		
		return strRep;
	}

	public void addSensorIdMap(String fullKey, String shortKey){
		sensorIdMap.put(fullKey, shortKey);
	}
	public Map<String, String> getSensorIdMap() {
		return sensorIdMap;
	}

	public void setSensorIdMap(Map<String, String> sensorIdMap) {
		this.sensorIdMap = sensorIdMap;
	}

	public Integer getTimeColumn() {
		return timeColumn;
	}


	public void setTimeColumn(Integer timeColumn) {
		this.timeColumn = timeColumn;
	}


	public Integer getSectorStartColumn() {
		return sectorStartColumn;
	}


	public void setSectorStartColumn(Integer sectorStartColumn) {
		this.sectorStartColumn = sectorStartColumn;
	}



	public Map<String, SensorModel> getSensorIdtoSensorDataMap() {
		return sensorIdtoSensorDataMap;
	}



	public void setSensorIdtoSensorDataMap(
			Map<String, SensorModel> sensorIdtoSensorDataMap) {
		this.sensorIdtoSensorDataMap = sensorIdtoSensorDataMap;
	}

	public void addSensorIdtoSensorDataMapEntry(String sensornId, List<SensorModel> sensorList){

		for(SensorModel sens : sensorList){
			String longSensName = sens.getSensorId();
			if(longSensName != null) {
				String shortName = sensorIdMap.get(longSensName);
				if(shortName != null)
					sensorIdtoSensorDataMap.put(shortName, sens);
			}
		}
	}
}
