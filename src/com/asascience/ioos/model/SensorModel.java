package com.asascience.ioos.model;

import java.util.ArrayList;
import java.util.List;

public class SensorModel {
	private String sensorId;
	private Coordinate sensorHeight;
	private List<QualityModel> sensorQualityModel;
	private VectorModel sensorLocation;
	private VectorModel sensorOrientation;
	private String longSensorId;
	//bin definition for this sensor
	SensorDataRecords binDefRecord;
	
	// Data records for this sensor
	SensorDataRecords sensorDataRecord;
	
	public SensorModel(){
		sensorQualityModel = new ArrayList<QualityModel>();
		sensorLocation = null;
		sensorOrientation = null;
		sensorDataRecord = null;
		binDefRecord = null;

	}

	public VectorModel getSensorLocation() {
		return sensorLocation;
	}

	public void setSensorLocation(VectorModel sensorLocation) {
		this.sensorLocation = sensorLocation;
	}

	public VectorModel getSensorOrientation() {
		return sensorOrientation;
	}

	public void setSensorOrientation(VectorModel sensorOrientation) {
		this.sensorOrientation = sensorOrientation;
	}

	public List<QualityModel> getSensorQualityModel() {
		return sensorQualityModel;
	}

	public void setSensorQualityModel(List<QualityModel> sensorQualityModel) {
		this.sensorQualityModel = sensorQualityModel;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public Coordinate getSensorHeight() {
		return sensorHeight;
	}

	public void setSensorHeight(Coordinate sensorHeight) {
		this.sensorHeight = sensorHeight;
	}
	
	public String sensorDataRecordToString(SensorDataRecords sensorDataRecord, String typeStr){
		String strRep  = typeStr +"\n";
		if(sensorDataRecord != null){
			List<SensorProperty> sensorData = sensorDataRecord.getModeledProperties();

			if(sensorData != null){
				for(SensorProperty modeledProp : sensorData){
					strRep += "       " + modeledProp.getSensorType() + "  " + modeledProp.getSensorUnitOfMeasure() + "\n";

				}
			}
			strRep += sensorDataRecord.toString();
		}
		return strRep;
	}
	public String toString() {
		String strRep = "SensorID: " + sensorId + " Height: " + sensorHeight +"\n";

		strRep += sensorDataRecordToString(binDefRecord,"      Bin Definition");
		strRep += sensorDataRecordToString(sensorDataRecord,"      Sensor Recorded Properties");
	
		return strRep;
	}



	
	public SensorDataRecords getSensorDataRecord() {
		return sensorDataRecord;
	}

	public void setSensorDataRecord(SensorDataRecords sensorDataRecord) {
		this.sensorDataRecord = sensorDataRecord;
	}

	public SensorDataRecords getBinDefRecord() {
		return binDefRecord;
	}

	public void setBinDefRecord(SensorDataRecords binDefRecord) {
		this.binDefRecord = binDefRecord;
	}

	public String getLongSensorId() {
		return longSensorId;
	}

	public void setLongSensorId(String longSensorId) {
		this.longSensorId = longSensorId;
	}

	
}
