package com.asascience.ioos.exception;

public class IoosSosParserException extends Exception{

	public enum IoosSosParserErrors{
		MEMBER_REQUIRED("Member observations are required"),
		IDENTIFIER_MISSING("Network ID, short name and long name identifiers are required");
		private String msg;
		IoosSosParserErrors(String msg){
			this.msg = msg;
		}
		public String toString(){
			return this.msg;
		}
	}
	
	public IoosSosParserException(IoosSosParserErrors errorType){
		super(errorType.toString());
	}
}
