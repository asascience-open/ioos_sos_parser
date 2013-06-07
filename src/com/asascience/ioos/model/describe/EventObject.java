package com.asascience.ioos.model.describe;

import org.joda.time.DateTime;

public class EventObject {
	DateTime eventDate;
	String description;
	String documentationLink;
	public EventObject(){
		
	}
	public String toString(){
		return "Date: " + eventDate.toString() +"\n"+
				"Description " + description+"\n"+
				"Reference " + documentationLink;
	}
	public DateTime getEventDate() {
		return eventDate;
	}
	public void setEventDate(DateTime eventDate) {
		this.eventDate = eventDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDocumentationLink() {
		return documentationLink;
	}
	public void setDocumentationLink(String documentationLink) {
		this.documentationLink = documentationLink;
	}
}
