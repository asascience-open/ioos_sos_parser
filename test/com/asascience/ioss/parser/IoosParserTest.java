package com.asascience.ioss.parser;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;

import com.asascience.ioos.exception.GetObservationException;
import com.asascience.ioos.model.GetObservation;
import com.asascience.ioos.model.MemberObservation;
import com.asascience.ioos.parser.GetObservationParser;
import com.asascience.ioos.parser.SweDataRecordParser;

public class IoosParserTest {

	public static void main(String[] args){
		GetObservationParser gop = new GetObservationParser();
		 String gopFile;
		 String sweRecordFile;
		 String sweMultiRecordFile;
		 String sweMultiQcRecordFile;
		 String sweSingleStationProfile;
		try {
			gopFile = new java.io.File( "./TestFiles/OM-GetObservation.xml" ).getCanonicalPath();
			sweRecordFile  = new java.io.File( 
					"./TestFiles/SWE-SingleStation-SingleProperty-TimeSeries.xml" ).getCanonicalPath();
			sweMultiRecordFile = new java.io.File( 
					"./TestFiles/SWE-MultiStation-TimeSeries.xml" ).getCanonicalPath();
			sweMultiQcRecordFile = new java.io.File( 
					"./TestFiles/SWE-MultiStation-TimeSeries_QC.xml" ).getCanonicalPath();
			sweSingleStationProfile = new java.io.File( 
					"./TestFiles/SWE-SingleStation-TimeSeriesProfile.xml" ).getCanonicalPath();
			 System.out.println("xmlFile: " +gopFile);
			 try {
				 GetObservation getObsModel = gop.parseGO(gopFile);
				 List<MemberObservation> memObs = getObsModel.getMemberObservation();
				 if(memObs != null){
					 if(!true){
					 SweDataRecordParser sweParser = new SweDataRecordParser("timeSeries");
					 sweParser.parseSweDataRecord(sweMultiQcRecordFile, memObs.get(0));
					 }
					 else {
					 SweDataRecordParser sweParser = new SweDataRecordParser("timeSeriesProfile");
					 sweParser.parseSweDataRecord(sweSingleStationProfile, memObs.get(0));
					 }
				 }
				 System.out.println("Results: ");
				 System.out.println(getObsModel.toString());
				
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GetObservationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
