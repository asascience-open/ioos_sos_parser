package com.asascience.ioss.parser;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;

import com.asascience.ioos.exception.IoosSosParserException;
import com.asascience.ioos.model.GetObservation;
import com.asascience.ioos.model.MemberObservation;
import com.asascience.ioos.model.capabilities.GetCapabilities;
import com.asascience.ioos.model.describe.DescribeSensorStation;
import com.asascience.ioos.parser.DescribeSensorStationParser;
import com.asascience.ioos.parser.GetCapabilitiesParser;
import com.asascience.ioos.parser.GetObservationParser;
import com.asascience.ioos.parser.SweDataRecordParser;

public class IoosParserTest {

	public static void main(String[] args){
		GetObservationParser gop = new GetObservationParser();
		GetCapabilitiesParser gc = new GetCapabilitiesParser();
		DescribeSensorStationParser dss = new DescribeSensorStationParser();
		 String gopFile;
		 String sweRecordFile;
		 String sweMultiRecordFile;
		 String sweMultiQcRecordFile;
		 String sweSingleStationProfile;
		 String sweSingleProfileQc;
		 String getCapFile;
		 String describeSensNetFile;
		 String describeSensStatFile;
		try {
			gopFile = new java.io.File( "./TestFiles/OM-GetObservation.xml" ).getCanonicalPath();
			getCapFile = new java.io.File( "./TestFiles/SOS-GetCapabilities.xml" ).getCanonicalPath();
			sweRecordFile  = new java.io.File( 
					"./TestFiles/SWE-SingleStation-SingleProperty-TimeSeries.xml" ).getCanonicalPath();
			sweMultiRecordFile = new java.io.File( 
					"./TestFiles/SWE-MultiStation-TimeSeries.xml" ).getCanonicalPath();
//			sweMultiQcRecordFile = new java.io.File( 
//					"./TestFiles/SWE-MultiStation-TimeSeries_QC.xml" ).getCanonicalPath();
			sweMultiQcRecordFile = new java.io.File( 
					"./TestFiles/new-TimeSeries_QC.xml" ).getCanonicalPath();
			sweSingleStationProfile = new java.io.File( 
					"./TestFiles/SWE-SingleStation-TimeSeriesProfile.xml" ).getCanonicalPath();
//			sweSingleProfileQc = new java.io.File( 
//					"./TestFiles/SWE-SingleStation-TimeSeriesProfile_QC.xml" ).getCanonicalPath();
			sweSingleProfileQc = new java.io.File( 
					"./TestFiles/new-TimeSeriesProfile_QC.xml" ).getCanonicalPath();
			describeSensNetFile = new java.io.File( 
					"./TestFiles/SML-DescribeSensor-Network.xml").getCanonicalPath();
			describeSensStatFile = new java.io.File( 
					"./TestFiles/SML-DescribeSensor-Station.xml").getCanonicalPath();
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
					//sweParser.parseSweDataRecord(sweSingleStationProfile, memObs.get(0));
					 sweParser.parseSweDataRecord(sweSingleProfileQc, memObs.get(0));

					 }
				 }
				 
				 System.out.println("Results: ");
				 System.out.println(getObsModel.toString());

				 System.out.println("----BEGIN GET CAPABILITIES");
				 GetCapabilities getCapsModel = gc.parseGO(getCapFile);
				 System.out.println(getCapsModel.toString());
				 System.out.println("----END GET CAPABILITIES");
				 
				 System.out.println("----BEGIN DESCRIBE SENSOR NETWORK----");
				 DescribeSensorStation sensStat = dss.parseDesribeStation(describeSensNetFile);
				 System.out.println(sensStat.toString());
				 System.out.println("----END DESCRIBE SENSOR NETWORK----");

				 System.out.println("----BEGIN DESCRIBE SENSOR STATION----");
				 DescribeSensorStation sensStat2 = dss.parseDesribeStation(describeSensStatFile);
				 System.out.println(sensStat2.toString());
				 System.out.println("----END DESCRIBE SENSOR STATION----");

			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IoosSosParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
