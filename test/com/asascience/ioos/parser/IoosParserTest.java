package com.asascience.ioos.parser;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;

import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFileWriteable;
import ucar.nc2.NetcdfFileWriter;

import com.asascience.ioos.exception.IoosSosParserException;
import com.asascience.ioos.model.GetObservation;
import com.asascience.ioos.model.MemberObservation;
import com.asascience.ioos.model.capabilities.GetCapabilities;
import com.asascience.ioos.model.describe.DescribeSensorStation;
import com.asascience.ioos.netcdf.CreateNetcdf;
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
		 String outputDirectory;
		 String timeSeriesOutput;
		try {
			outputDirectory = new java.io.File( "./TestOut").getCanonicalPath();
			timeSeriesOutput = outputDirectory + System.getProperty("file.separator") + "timeSeries";
			gopFile = new java.io.File( "./TestFiles/OM-GetObservation.xml" ).getCanonicalPath();
			getCapFile = new java.io.File( "./TestFiles/SOS-GetCapabilities.xml" ).getCanonicalPath();
			sweRecordFile  = new java.io.File( 
					"./TestFiles/SWE-SingleStation-SingleProperty-TimeSeries.xml" ).getCanonicalPath();
			sweMultiRecordFile = new java.io.File( 
					"./TestFiles/SWE-MultiStation-TimeSeries.xml" ).getCanonicalPath();
		
			sweMultiQcRecordFile = new java.io.File( 
					"./TestFiles/new-TimeSeries_QC.xml" ).getCanonicalPath();
			sweSingleStationProfile = new java.io.File( 
					"./TestFiles/SWE-SingleStation-TimeSeriesProfile.xml" ).getCanonicalPath();
		
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
					 SweDataRecordParser sweParser; 
					 CreateNetcdf ncCreate;
					 List<NetcdfFile> ncList;
					 
					 System.out.println("BEGIN GET OBSERVATION - SINGLE TIME SERIES ");
					 sweParser = new SweDataRecordParser("timeSeries", null);
					 sweParser.parseSweDataRecord(sweRecordFile, memObs.get(0));
					 System.out.println(getObsModel.toString());
					 System.out.println("END GET OBSERVATION - SINGLE TIME SERIES ");
					 
				
					
					 System.out.println("BEGIN GET OBSERVATION - MULTI QC TIME SERIES");
					  sweParser = new SweDataRecordParser("timeSeries", null);
					 sweParser.parseSweDataRecord(sweMultiQcRecordFile, memObs.get(0));
					 System.out.println(getObsModel.toString());
					 System.out.println("END GET OBSERVATION - MULTI QC TIME SERIES");
					 
					 

					 System.out.println("BEGIN GET OBSERVATION - SINGLE QC TIME SERIES PROFILE");
					 sweParser = new SweDataRecordParser("timeSeriesProfile", null);
					 sweParser.parseSweDataRecord(sweSingleProfileQc, memObs.get(0));
					 System.out.println(getObsModel.toString());
					 System.out.println("END GET OBSERVATION - SINGLE QC TIME SERIES PROFILE");


					 System.out.println("BEGIN GET OBSERVATION - SINGLE TIME SERIES PROFILE");
					 sweParser = new SweDataRecordParser("timeSeriesProfile", null);
					 sweParser.parseSweDataRecord(sweSingleStationProfile, memObs.get(0));
					 System.out.println(getObsModel.toString());
					 System.out.println("END GET OBSERVATION - SINGLE TIME SERIES PROFILE");
					 
					

				
					 System.out.println("BEGIN GET OBSERVATION - MULTI TIME SERIES ");
					 sweParser = new SweDataRecordParser("timeSeries", null);
					 sweParser.parseSweDataRecord(sweMultiRecordFile, memObs.get(0));
					 System.out.println(getObsModel.toString());
					 System.out.println("END GET OBSERVATION - MULTIE TIME SERIES ");
					 
					 ncCreate  = new CreateNetcdf(getObsModel);
					 ncList =  ncCreate.generateNetcdf(timeSeriesOutput);
					 for(NetcdfFile nc : ncList ){

						 System.out.println("NC " +nc.toString());
					 }
					 System.out.println("size " + ncList.size());
					 ncCreate.closeNetcdfFiles();
						
					 System.out.println("BEGIN GET OBSERVATION - SINGLE TIME SERIES PROFILE");
					 sweParser = new SweDataRecordParser("timeSeriesProfile", null);
					 sweParser.parseSweDataRecord(sweSingleStationProfile, memObs.get(0));
					 System.out.println(getObsModel.toString());
					 System.out.println("END GET OBSERVATION - SINGLE TIME SERIES PROFILE");
					 
					ncCreate  = new CreateNetcdf(getObsModel);
					ncList =  ncCreate.generateNetcdf(outputDirectory);
					for(NetcdfFile nc : ncList ){
					
						System.out.println("NC " +nc.toString());
					}
					System.out.println("size " + ncList.size());
					ncCreate.closeNetcdfFiles();
				 }



				 System.out.println("----BEGIN GET CAPABILITIES");
				 GetCapabilities getCapsModel = gc.parseGetCapabilities(getCapFile);
				 System.out.println(getCapsModel.toString());
				 System.out.println("----END GET CAPABILITIES");
				 
				 System.out.println("----BEGIN DESCRIBE SENSOR NETWORK----");
				 DescribeSensorStation sensStat = dss.parseDescribeStation(describeSensNetFile);
				 System.out.println(sensStat.toString());
				 System.out.println("----END DESCRIBE SENSOR NETWORK----");

				 System.out.println("----BEGIN DESCRIBE SENSOR STATION----");
				 DescribeSensorStation sensStat2 = dss.parseDescribeStation(describeSensStatFile);
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
