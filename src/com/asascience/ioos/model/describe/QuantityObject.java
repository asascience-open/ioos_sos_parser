package com.asascience.ioos.model.describe;

public class QuantityObject {
	String quantityDef;
	String unitOfMeasure;
	public String toString (){
		return (quantityDef + " Units " +unitOfMeasure);
	}
	public QuantityObject(){
		
	}
	public String getQuantityDef() {
		return quantityDef;
	}
	public void setQuantityDef(String quantityDef) {
		this.quantityDef = quantityDef;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
}
