package com.asascience.ioos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asascience.ioos.model.SensorProperty.PropertyType;

public class SensorDataRecords {
	List<SensorProperty> modeledProperties;
	// time is the key to get the sensor data
	Map<Integer, List<Object[]>> sensorDataList;
	String sensorShortName;
	Integer numDataFields;
	
	
	public SensorDataRecords(String sensorShortName,
			List<SensorProperty> modeledProperties){
		sensorDataList = new HashMap<Integer, List<Object[]>>();
		numDataFields = modeledProperties.size();
		this.sensorShortName = sensorShortName;
		this.modeledProperties = modeledProperties;
	}
	
	public Integer getPropertyIndex(PropertyType propertyToFind){
		Integer countIndex = null;
		int currIndex = 0;
		for(SensorProperty prop : modeledProperties){
			if(prop.getPropertyType() == propertyToFind){
				countIndex = currIndex;
				break;
			}
			currIndex++;
				
		}
		return countIndex;
	}
	
	public void addSensorPropertyAtIndex(SensorProperty sensorProp, int index){
		modeledProperties.add(index, sensorProp);
	}
	
	public void addSensorDataRecord(Object[] sensorData, Integer bin){
		List<Object[]> currList = sensorDataList.get(bin);
		if(currList == null){
			currList = new ArrayList<Object[]>();
		}
		currList.add(sensorData);
		sensorDataList.put(bin, currList);
		
	}

	public Integer getNumberProperties(){
		return modeledProperties.size();
	}
	public Map <Integer, List<Object[]>> getTimeSensorDataMap() {
		return sensorDataList;
	}

	public void setTimeSensorDataMap(Map<Integer, List<Object[]>> timeSensorDataMap) {
		this.sensorDataList = timeSensorDataMap;
	}

	public List<SensorProperty> getModeledProperties() {
		return modeledProperties;
	}
	
	public String toString(){
		String strRep = "Data records: " + "\n";
		for(Integer bin : sensorDataList.keySet()){
			strRep += "  bin " + bin+"\n";
			for(Object[] sensorRow :  sensorDataList.get(bin)){
				for(Object entry : sensorRow)
					strRep += entry +" ";
				strRep += "\n";
			}
		}

		return strRep;
	}
}
