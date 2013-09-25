package com.asascience.ioos.model.capabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asascience.ioos.model.describe.DescribeSensorStation;

public class GetCapabilities {
	private ServiceIdentification serviceId;
	private ServiceProvider serviceProvider;
	private List<Operation> operationsList;
	private Map<String, List<String>> capabilitiesParameters;
	private List<Observation> observationList;
	String version;
	
	private List<DescribeSensorStation> describeSensors;
	String refTitle;
	String refLink;

	public GetCapabilities(){
		operationsList = new ArrayList<Operation>();
		capabilitiesParameters = new HashMap<String, List<String>>();
		observationList = new ArrayList<Observation>();
		describeSensors = new ArrayList<DescribeSensorStation>();
	}
	
	public List<DescribeSensorStation> getDescribeSensors() {
		return describeSensors;
	}

	public void setDescribeSensors(List<DescribeSensorStation> describeSensors) {
		this.describeSensors = describeSensors;
	}

	public String toString(){
		String strRep = "";
		if(serviceId != null)
			strRep += serviceId.toString() +"\n";
		if(serviceProvider != null)
			strRep += serviceProvider.toString() + "\n";
		strRep += "Parameters " + "\n";
		for(String key : capabilitiesParameters.keySet()){
			strRep += "  " + key + "\n";
			for(String param : capabilitiesParameters.get(key))
				strRep += "   " +param + "\n";
		}
		strRep += "Operations " + "\n";
		for(Operation op : operationsList)
			strRep += op.toString();
		strRep += "Observations " + "\n";
		for(Observation ob : observationList)
			strRep += ob.toString();
		strRep += "Version " + version+ "\n";
		strRep += "refTitle " + refTitle + "\n";
		strRep += "refLink " + refLink + "\n";
 		return strRep;
	}
	public List<Observation> getObservationList() {
		return observationList;
	}

	public void setObservationList(List<Observation> observationList) {
		this.observationList = observationList;
	}
	
	public Map<String, List<String>> getCapabilitiesParameters() {
		return capabilitiesParameters;
	}

	public void setCapabilitiesParameters(
			Map<String, List<String>> capabilitiesParameters) {
		this.capabilitiesParameters = capabilitiesParameters;
	}
	
	public ServiceIdentification getServiceId() {
		return serviceId;
	}

	public void setServiceId(ServiceIdentification serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public List<Operation> getOperationsList() {
		return operationsList;
	}

	public void setOperationsList(List<Operation> operationsList) {
		this.operationsList = operationsList;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRefTitle() {
		return refTitle;
	}

	public void setRefTitle(String refTitle) {
		this.refTitle = refTitle;
	}

	public String getRefLink() {
		return refLink;
	}

	public void setRefLink(String refLink) {
		this.refLink = refLink;
	}
	
	public Operation getOperation(String operationName){
		Operation op = null;
		for(Operation tempOp : this.operationsList){
			if(operationName.equals(tempOp.getOperationName())){
				op = tempOp;
				break;
			}
		}
		
		return op;
	}
	
	public Observation getObservation(String offeringName){
		Observation observationOffering = null;
		for(Observation obs : observationList){
			if(obs.getObservationName().equals(offeringName)) {
				observationOffering = obs;
				break;
			}
				
		}
		return observationOffering;
	}
}
