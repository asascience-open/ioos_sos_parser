package com.asascience.ioos.model;

public class BoundingBox {
	private LatLonPoint lowerLeftCornerBB;
	private LatLonPoint upperRightCornerBB;
	public BoundingBox(){
		lowerLeftCornerBB = null;
		upperRightCornerBB = null;
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
	
	public String toString(){
		return "Lower Corner: " + lowerLeftCornerBB.toString() +"\n" +
				"Upper Corner: " + upperRightCornerBB.toString() +"\n";
	}
}
