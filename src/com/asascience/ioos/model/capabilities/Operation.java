package com.asascience.ioos.model.capabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operation {
	List<DCP> dcpList;
	String operationName;
	Map<String, List<String>> parameterAllowedValues;
	
	public Operation(){
		dcpList = new ArrayList<DCP>();
		parameterAllowedValues = new HashMap<String, List<String>>();
	}

	public List<DCP> getDcpList() {
		return dcpList;
	}

	public void setDcpList(List<DCP> dcpList) {
		this.dcpList = dcpList;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Map<String, List<String>> getParameterAllowedValues() {
		return parameterAllowedValues;
	}

	public void setParameterAllowedValues(
			Map<String, List<String>> parameterAllowedValues) {
		this.parameterAllowedValues = parameterAllowedValues;
	}
	
	public String toString(){
		
		String strRep = "DCP \n";
		for(DCP dcp : dcpList)
			strRep += " " + dcp.toString();
		strRep += "Operation " + operationName  + "\n";
		strRep += "Parameters " + "\n";
		for(String key : parameterAllowedValues.keySet()){
			strRep += " " + key  + "\n";
			for(String val : parameterAllowedValues.get(key))
				strRep += "  " +val  + "\n";
		}
		return strRep;
	}
	
}
