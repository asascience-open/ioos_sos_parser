package com.asascience.ioos.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SweDataRecord {
	public enum SweFileType{
		TIME_SERIES("timeSeries"),
		TIME_SERIES_PROFILE("timeSeriesProfile");
		private final String typeName;
		SweFileType(String typeName){
			this.typeName = typeName;
		}
		public String getTypeName(){
			return typeName;
		}
	}
	// Maps the short stationID to the URN station ID
	Map<String, String> stationIdMap;
	
	Map<String, StationModel> stationModelMap;
	SweFileType fileType;
	
	Integer numberDataRows;
	Integer timeColumn;
	Integer sectorStartColumn;
	public SweDataRecord(){
		stationIdMap = new HashMap<String,String>();
		stationModelMap = new HashMap<String, StationModel>();
		numberDataRows = 0;
		sectorStartColumn = 0;
	}
	
	


	

	public Integer getNumberDataRows() {
		return numberDataRows;
	}

	public void setNumberDataRows(Integer numberDataRows) {
		this.numberDataRows = numberDataRows;
	}

	




	public void addStationIdMap(String shortIDKey, String stationIdKey){
		stationIdMap.put(shortIDKey, stationIdKey);
	}

	public Map<String, String> getStationIdMap() {
		return stationIdMap;
	}

	public void setStationIdMap(Map<String, String> stationIdMap) {
		this.stationIdMap = stationIdMap;
	}
	
	public String toString(){
		String strRep = "";
		for(StationModel station : stationModelMap.values()){
			strRep += station.toString() + "\n";
		}		
		
		
		return strRep;
	}

	public Map<String, StationModel> getStationModelMap() {
		return stationModelMap;
	}



	public void setStationModelMap(Map<String, StationModel> stationModelMap) {
		this.stationModelMap = stationModelMap;
	}


	public StationModel findStationWithSensor(String sensorId){
		StationModel retStation = null;
		boolean found = false;
		for(StationModel station : getStationModelMap().values()){
			for( SensorModel sensModel  : station.getSensorIdtoSensorDataMap().values()){
				if(sensModel.getSensorId().equals(sensorId)){
					retStation = station;
					found = true;
					break;
				}
			}
			if(found)
				break;
		}
		return retStation;
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






	public SweFileType getFileType() {
		return fileType;
	}






	public void setFileType(SweFileType fileType) {
		this.fileType = fileType;
	}



	
}
