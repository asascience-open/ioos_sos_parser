package com.asascience.ioos.model;

public class VectorModel {
	Coordinate[] vectorCoordinates;
	
	public VectorModel(){
		vectorCoordinates = new Coordinate[3];		
	}
	
	public void setCoordinates(Coordinate xCoord, 
							   Coordinate yCoord,
							   Coordinate zCoord){
		vectorCoordinates[0] = xCoord;
		vectorCoordinates[1] = yCoord;
		vectorCoordinates[2] = zCoord;
	}

	
	public Coordinate getXCoordinate(){
		return vectorCoordinates[0];
	}
	
	public Coordinate getYCoordinate(){
		return vectorCoordinates[1];
	}
	
	public Coordinate getZCoordinate(){
		return vectorCoordinates[2];
	}
	
	public String toString(){
		String strRep = "";
		if(vectorCoordinates[0] != null && 
				vectorCoordinates[1] != null &&
				vectorCoordinates[2] != null)
			strRep = vectorCoordinates[0].toString() + "," +
					vectorCoordinates[1].toString() + "," +
					vectorCoordinates[2].toString();
		return strRep;
	}
}
