package com.asascience.ioos.exception;

public class GetObservationException extends Exception{

	public enum GetObservationErrors{
		MEMBER_REQUIRED("Member observations are required");
		private String msg;
		GetObservationErrors(String msg){
			this.msg = msg;
		}
		public String toString(){
			return this.msg;
		}
	}
	
	public GetObservationException(GetObservationErrors errorType){
		super(errorType.toString());
	}
}
