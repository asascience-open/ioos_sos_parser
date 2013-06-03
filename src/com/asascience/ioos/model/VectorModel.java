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
	
	public String toString(){
		return vectorCoordinates[0].toString() + "," +
				vectorCoordinates[1].toString() + "," +
				vectorCoordinates[2].toString();
	}
}
