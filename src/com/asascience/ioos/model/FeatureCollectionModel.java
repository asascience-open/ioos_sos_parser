package com.asascience.ioos.model;

import java.util.HashMap;
import java.util.Map;

public class FeatureCollectionModel {
	private String featureType;
	private String featureCodeSpace;
	private String boundingBoxSrsName;
	private LatLonPoint lowerLeftCornerBB;
	private LatLonPoint upperRightCornerBB;
	private Map<String, LatLonPoint> stationPositionsMap;

	public FeatureCollectionModel(){
		stationPositionsMap = new HashMap<String, LatLonPoint>();

		lowerLeftCornerBB = null;
		upperRightCornerBB = null;
	}

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public String getFeatureCodeSpace() {
		return featureCodeSpace;
	}

	public void setFeatureCodeSpace(String featureCodeSpace) {
		this.featureCodeSpace = featureCodeSpace;
	}
	public String toString(){
		String stringRep;
		stringRep = "featureType: " + featureType  + " codeSpace: " + featureCodeSpace + "\n";
		if(lowerLeftCornerBB != null)
			stringRep += "feature bounding box lower left: " + lowerLeftCornerBB.getLatitude() + "," + lowerLeftCornerBB.getLongitude()  + "\n";
		if(upperRightCornerBB != null)
			stringRep += "feature bounding box upper right: " + upperRightCornerBB.getLatitude() + "," + upperRightCornerBB.getLongitude()  + "\n";
		for(String key : stationPositionsMap.keySet()){
			stringRep += "station: " + key + "  position: " + stationPositionsMap.get(key) + "\n";
		}
		return stringRep;
	}

	public LatLonPoint getLowerLeftCornerBB() {
		return lowerLeftCornerBB;
	}

	public void setLowerLeftCornerBB(LatLonPoint lowerLeftCornerBB) {
		this.lowerLeftCornerBB = lowerLeftCornerBB;
	}

	public LatLonPoint getUpperRightCornerBB() {
		return upperRightCornerBB;
	}

	public void setUpperRightCornerBB(LatLonPoint upperRightCornerBB) {
		this.upperRightCornerBB = upperRightCornerBB;
	}

	public String getBoundingBoxSrsName() {
		return boundingBoxSrsName;
	}

	public void setBoundingBoxSrsName(String boundingBoxSrsName) {
		this.boundingBoxSrsName = boundingBoxSrsName;
	}
	public void addStationKey(String stationKey){
		stationPositionsMap.put(stationKey, null);
}
	public Map<String, LatLonPoint> getStationPositionsMap() {
		return stationPositionsMap;
	}

	public void addStationData(String stationKey, LatLonPoint stationLoc){
		if(stationKey != null){
			stationPositionsMap.put(stationKey,  stationLoc);
		}
	}
	
	public void setStationPositionsMap(Map<String, LatLonPoint> stationPositionsMap) {
		this.stationPositionsMap = stationPositionsMap;
	}
}
