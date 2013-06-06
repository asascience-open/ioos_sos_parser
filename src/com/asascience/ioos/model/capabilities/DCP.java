package com.asascience.ioos.model.capabilities;

public class DCP {
	String getLink;
	String postLink;
	String dcpType;
	public DCP(){
		getLink = null;
		postLink = null;
		dcpType = null;
	}
	public String getDcpType() {
		return dcpType;
	}
	public void setDcpType(String dcpType) {
		this.dcpType = dcpType;
	}
	public String getGetLink() {
		return getLink;
	}
	public void setGetLink(String getLink) {
		this.getLink = getLink;
	}
	public String getPostLink() {
		return postLink;
	}
	public void setPostLink(String postLink) {
		this.postLink = postLink;
	}
	public String toString(){
		return ( "Type " + dcpType + " Get " + getLink + "Post "+postLink  + "\n");
	}
}
