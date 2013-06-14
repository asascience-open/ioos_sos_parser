package com.asascience.ioos.model.describe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.asascience.ioos.model.BoundingBox;


public class DescribeSensorStation {

	private BoundingBox boundingBox;
	private String description;
	private String name;
	private Map<String, DescriptionObject> identifierMap;
	private Map<String, DescriptionObject> classificationMap;
	private Map<String, DescriptionObject> capabilitiesMap;
	private DateTime startSamplingTime;
	private DateTime endSamplingTime;
	private List<ContactData> contactList;
	private List<ComponentData> componentList;
	private List<DocumentationObject> documentationList;
	private List<EventObject> historyList;
	private String serviceVersion;
	public String toString(){
		String strRep = "Name: " + name +"\n"+
				"description: " + description + "\n"+
				"version " + serviceVersion +"\n";
		if(boundingBox != null)
			strRep +=	"Bounding box: \n  " + boundingBox.toString();
		if(startSamplingTime != null)
			strRep+="Start Time: " + startSamplingTime.toString() + "\n";
		if(endSamplingTime != null)
			strRep +="End Time: " + endSamplingTime.toString() + "\n";
		strRep += "Identifiers\n"; 
		for(String key : identifierMap.keySet()){
			strRep += key + "\n";
			strRep += "   " + identifierMap.get(key).toString();
		}
		strRep += "Classification " +"\n";
		for(String key : classificationMap.keySet()){
			strRep += key + "\n";
			strRep += "   " + classificationMap.get(key).toString();
		}
		strRep += "Capabilities" +"\n";
		for(String key : capabilitiesMap.keySet()){
			strRep += key + "\n";
			strRep += "   " + capabilitiesMap.get(key).toString();
		}
		strRep += "Documenation: \n ";
		for(DocumentationObject doc : documentationList){
			strRep += doc.toString();
		}
		strRep += "History Events: \n ";
		for(EventObject event : historyList){
			strRep += event.toString();
		}
		strRep += "Contact List: \n";
		for(ContactData con : contactList)
			strRep += con.toString();
		strRep += "Components:\n";
		for(ComponentData comp : componentList)
			strRep += comp.toString();
		return strRep;
	}
	
	public DescribeSensorStation(){
		boundingBox = null;
		identifierMap = new HashMap<String, DescriptionObject>();
		classificationMap = new HashMap<String, DescriptionObject>();
		capabilitiesMap = new HashMap<String, DescriptionObject>();
		contactList = new ArrayList<ContactData>();
		componentList = new ArrayList<ComponentData>();
		documentationList = new ArrayList<DocumentationObject>();
		historyList = new ArrayList<EventObject>();
	}
	public List<EventObject> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<EventObject> historyList) {
		this.historyList = historyList;
	}

	public Map<String, DescriptionObject> getCapabilitiesMap() {
		return capabilitiesMap;
	}

	public void setCapabilitiesMap(Map<String, DescriptionObject> capabilitiesMap) {
		this.capabilitiesMap = capabilitiesMap;
	}

	public List<ComponentData> getComponentList() {
		return componentList;
	}
	public void setComponentList(List<ComponentData> componentList) {
		this.componentList = componentList;
	}
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, DescriptionObject> getIdentifierMap() {
		return identifierMap;
	}
	public void setIdentifierMap(Map<String, DescriptionObject> identifierMap) {
		this.identifierMap = identifierMap;
	}
	public Map<String, DescriptionObject> getClassificationMap() {
		return classificationMap;
	}
	public void setClassificationMap(
			Map<String, DescriptionObject> classificationMap) {
		this.classificationMap = classificationMap;
	}
	
	public DateTime getStartSamplingTime() {
		return startSamplingTime;
	}
	public void setStartSamplingTime(DateTime startSamplingTime) {
		this.startSamplingTime = startSamplingTime;
	}
	public DateTime getEndSamplingTime() {
		return endSamplingTime;
	}
	public void setEndSamplingTime(DateTime endSamplingTime) {
		this.endSamplingTime = endSamplingTime;
	}
	public List<ContactData> getContactList() {
		return contactList;
	}
	public void setContactList(List<ContactData> contactList) {
		this.contactList = contactList;
	}

	public List<DocumentationObject> getDocumentationList() {
		return documentationList;
	}

	public void setDocumentationList(List<DocumentationObject> documentationList) {
		this.documentationList = documentationList;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}



}
