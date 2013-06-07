package com.asascience.ioos.model.describe;

public class DocumentationObject {
	String name;
	String description;
	String format;
	String onlineResource;
	String role;
	
	public DocumentationObject(){
		
	}

	
	public String toString(){
		return "name: " + name +"\n"+
				"description: " + description +"\n"+
				"format: " + format + "\n"+
				"online resource" + onlineResource + "\n" +
				"role: " +role +"\n";
	}
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getOnlineResource() {
		return onlineResource;
	}

	public void setOnlineResource(String onlineResource) {
		this.onlineResource = onlineResource;
	}
	
}
