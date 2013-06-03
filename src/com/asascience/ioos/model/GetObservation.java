package com.asascience.ioos.model;

import java.util.ArrayList;
import java.util.List;

public class GetObservation {
	
	private String gmlDescription;
	private String ioosSosVersion;
	private String ioosSchemaUrl;
	private List<MemberObservation> memberObservations;
	
	public GetObservation(){
		memberObservations = new ArrayList<MemberObservation>();
	}

	public String getGmlDescription() {
		return gmlDescription;
	}

	public void setGmlDescription(String gmlDescription) {
		this.gmlDescription = gmlDescription;
	}

	public String getIoosSosVersion() {
		return ioosSosVersion;
	}

	public void setIoosSosVersion(String ioosSosVersion, String ioosSchemaUrl) {
		this.ioosSosVersion = ioosSosVersion;
		this.ioosSchemaUrl = ioosSchemaUrl;
	}
	
	
	public void addMemberObservation(MemberObservation memberObs){
		memberObservations.add(memberObs);
	}
	
	public List<MemberObservation> getMemberObservation(){
		return memberObservations;
	}
	public String toString(){
		String getObsStringRep = "Description: " + gmlDescription + "\n"+
				"IOOS SOS version: " + ioosSosVersion + "\n" +
				"IOOS Scheam Verison url: " + ioosSchemaUrl + "\n" + 
				"Member Observations (" +  memberObservations.size() +")" + "\n";
		for(MemberObservation member :  memberObservations){
			getObsStringRep += member.toString() + "\n";
		}
		return getObsStringRep;
	}
	
	public int getNumberMemberObservations(){
		return memberObservations.size();
	}
	
}
