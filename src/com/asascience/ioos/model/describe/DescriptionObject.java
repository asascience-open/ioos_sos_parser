package com.asascience.ioos.model.describe;

public class DescriptionObject {
	String name;
	String codeSpace;
	String value;
	public DescriptionObject(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCodeSpace() {
		return codeSpace;
	}
	public void setCodeSpace(String codeSpace) {
		this.codeSpace = codeSpace;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String toString(){
		return "Name: " + name +"\n value: " + value +"\n CodeSpace: " + codeSpace + "\n";
	}
}
