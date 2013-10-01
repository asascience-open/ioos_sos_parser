package com.asascience.ioos.netcdf;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;


import ucar.ma2.Array;
import ucar.ma2.ArrayChar;
import ucar.ma2.ArrayFloat;
import ucar.ma2.DataType;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.Attribute;
import ucar.nc2.Dimension;
import ucar.nc2.Group;
import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFileWriter;
import ucar.nc2.Variable;

import com.asascience.ioos.model.Coordinate;
import com.asascience.ioos.model.GetObservation;
import com.asascience.ioos.model.MemberObservation;
import com.asascience.ioos.model.SensorModel;
import com.asascience.ioos.model.SensorProperty;
import com.asascience.ioos.model.SensorProperty.PropertyType;
import com.asascience.ioos.model.StationModel;
import com.asascience.ioos.model.SweDataRecord.SweFileType;
import com.asascience.ioos.model.VectorModel;
import com.asascience.ioos.model.describe.ComponentData;
import com.asascience.ioos.model.describe.DescribeSensorStation;

public class CreateNetcdf {
	private GetObservation getObs;
	private static final String UNITS = "units";
	private static final String STATION = "station";
	private static final String SENSOR = "sensor";
	private static final String SENSOR_DIM = "sensors";
	private static final String TIME_LEN_DIM = "time_str_len";
	private static final String DESCRIPTION ="description";
	private static final String LONG_NAME = "long_name";
	private static final String STATION_STR_LEN = "station_name_strlen";
	private static final String OBS_DIM = "obs";
	private static final String SENSOR_INDEX = "sensor_index";
	private static final String SENSOR_STR_LEN = "sensor_name_strlen";
	private static final String SENSOR_LNAME = "which sensor this obs is for";
	private static final String TIME_COV_START = "time_coverate_start";
	private static final String TIME_COV_END = "time_coverate_end";
	private static final String FEATURE_TYPE = "featureType";
	private static final String DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	private static final String BINS = "profile bins";
	private List<NetcdfFile> netcdfList;
	private Dimension obsDimension;
	private Dimension binDimension;
	private Dimension numSensors; 
	private Dimension sensorStrDim;
	private Dimension timeLenDimension;
	public CreateNetcdf(GetObservation getObs){
		this.getObs = getObs;
		netcdfList = new ArrayList<NetcdfFile>();
	}

	public List<NetcdfFile> generateNetcdf(String outputDirectory){
		if(getObs != null ){//&& describeSens  != null ){
			generateNetcdf(outputDirectory,netcdfList);
		}
		return netcdfList;
	}

	public void closeNetcdfFiles(){
		for(NetcdfFile ncFile : netcdfList){
			try {
				ncFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void generateNetcdf(String outputDirectory,List<NetcdfFile> netcdfList){
		for(MemberObservation memberObs : getObs.getMemberObservation()){
			if(memberObs.getSweDataRecords() != null){
				for(String station :memberObs.getSweDataRecords().getStationIdMap().keySet()){
					try {
						String ncLocation = outputDirectory + System.getProperty("file.separator")+  station+".nc";
						StationModel stationModel = memberObs.getSweDataRecords().getStationModelMap().get(station);
						SweFileType featureType = memberObs.getSweDataRecords().getFileType();
						NetcdfFileWriter ncfile = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3,
								ncLocation);
						ncfile.addGroupAttribute(null, new Attribute(DESCRIPTION, memberObs.getDescription()));
						ncfile.addGroupAttribute(null, new Attribute(TIME_COV_START, 
								memberObs.getStartSamplingTime().toString()));
						ncfile.addGroupAttribute(null, new Attribute(TIME_COV_END, 
								memberObs.getStartSamplingTime().toString()));

						ncfile.addGroupAttribute(null, new Attribute(FEATURE_TYPE, featureType.getTypeName()));
						addStationAndLocationStructure(ncfile, station, stationModel);
						addStationObservations(memberObs.getSweDataRecords().getStationModelMap().get(station),
								ncfile, featureType);

						ncfile.create();


						try {
							writeStationLocationData(stationModel, ncfile);
							writeStationObservationData(stationModel, ncfile);

						} catch (InvalidRangeException e) {
							e.printStackTrace();
						}
						netcdfList.add(ncfile.getNetcdfFile());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private String getStationPrefix(){
		return STATION + "_";
	}


	private String getSensorPrefix(){
		return SENSOR + "_";
	}

	private void writeStationLocationData(StationModel stationModel, NetcdfFileWriter netcdf) 
			throws IOException, InvalidRangeException{
		Variable stat = netcdf.findVariable(STATION);
		String stationName = stationModel.getStationName();
		if(stat!= null && stationName != null){
			Array statName = Array.factory(stationName.toCharArray());
			netcdf.write(stat,  statName);
		}

		String stationPrefix =  getStationPrefix();
		VectorModel vect = stationModel.getPlatformLocation();
		if(vect != null){
			writeCoordinate(vect.getXCoordinate(), stationPrefix, netcdf);
			writeCoordinate(vect.getYCoordinate(), stationPrefix, netcdf);
			writeCoordinate(vect.getZCoordinate(), stationPrefix, netcdf);


		}
	}

	private int getIndexForTime(String timeVal, List<String> timeVals) {
		int rowIndex = 0;
		for(String time : timeVals) {
			if(timeVal.equals(time))
				break;
			rowIndex++;
		}

		return rowIndex;
	}
	private void writeStationObservationData(StationModel stationRecord, NetcdfFileWriter netcdf) 
			throws IOException, InvalidRangeException{
		Map<String, Integer> sensorToIndex = new HashMap<String, Integer>();

		Variable sensor = netcdf.findVariable(SENSOR);
		int sensorIndex = 0;
		ArrayChar ac2 = new ArrayChar.D2(numSensors.getLength(), sensorStrDim.getLength());

		for(String sensorKey : stationRecord.getSensorIdtoSensorDataMap().keySet()){
			ac2.setString( sensorIndex, sensorKey);

			sensorToIndex.put(sensorKey, sensorIndex);
			sensorIndex++;
		}
		netcdf.write(sensor, ac2);
		int sensorIndexArray[] = new int[obsDimension.getLength()];
		Map<Integer, Map<SensorProperty, Object[]>> propertyList = new HashMap<Integer, 
				Map<SensorProperty, Object[]>>();

		int rowIndex = 0;
		List<String> timeRowIndex = new ArrayList<String>();
		Map<SensorProperty, Object[]> binPropertyMap = 
				new HashMap<SensorProperty,Object[]>();
				List<String> currSensorTimeRowIndex = new ArrayList<String>();
				Object [] savedTimeData = null;
				SensorProperty timeProperty = null;

				for(String sensorKey : stationRecord.getSensorIdtoSensorDataMap().keySet()){
					SensorModel sensorModel = stationRecord.getSensorIdtoSensorDataMap().get(sensorKey);
					sensorIndex = sensorToIndex.get(sensorKey);
					currSensorTimeRowIndex.clear();
					for(Integer bin : sensorModel.getSensorDataRecord().getTimeSensorDataMap().keySet()){

						if( sensorModel.getSensorDataRecord().getTimeSensorDataMap().size() > 1){
							if(binPropertyMap != null && timeProperty != null)
								savedTimeData = binPropertyMap.get(timeProperty);
							binPropertyMap = new HashMap<SensorProperty,Object[]>();
							if(savedTimeData != null)
								binPropertyMap.put(timeProperty, savedTimeData);
						}
						List<SensorProperty> sensPropList = sensorModel.getSensorDataRecord().getModeledProperties();
						int propertyIndex = 0;

						for(Object[] sensorRow :  
							sensorModel.getSensorDataRecord().getTimeSensorDataMap().get(bin)){
							propertyIndex = 0;
							for(Object entry : sensorRow) {
								SensorProperty sensProp = sensPropList.get(propertyIndex);
								boolean alreadyCreated = false;

								if(sensProp != null){

									if(propertyList.get(bin) != null) {
										// check to see if the property was already created
										for(SensorProperty sensP : propertyList.get(bin).keySet()){
											if(sensP.getPropertyName().equals(sensProp.getPropertyName())){
												alreadyCreated = true;
												sensProp = sensP;
												break;
											}
										}

									}
									else if(sensProp.getPropertyType() == PropertyType.TIME && timeProperty != null)
										sensProp = timeProperty;
									if(propertyList.get(bin) == null || propertyList.get(bin).get(sensProp)==null){


										if(!alreadyCreated){
											Object[] propArray = new Object[obsDimension.getLength()];

											 if(sensProp.getPropertyType() == PropertyType.TIME &&
														savedTimeData != null && binPropertyMap.get(timeProperty) != null){
													savedTimeData = null;
													propertyList.put(bin, binPropertyMap);

												}
											 else if(propertyList.get(bin) == null){
												binPropertyMap.put(sensProp,  propArray);
												propertyList.put(bin, binPropertyMap);

											}
								
											else 
												propertyList.get(bin).put(sensProp, propArray);

											//	sensorPropMap.put(sensorKey, binPropertyMap);
											//if(propertyList.get(bin) == null)

										}
									}
									if(sensProp.getPropertyType() == PropertyType.TIME)
										timeProperty = sensProp;
									if( sensorModel.getSensorDataRecord().getTimeSensorDataMap().size() >1){
										if(sensProp.getPropertyType() == PropertyType.TIME){
											if(!currSensorTimeRowIndex.contains(entry))
												currSensorTimeRowIndex.add((String) entry);
											rowIndex = timeRowIndex.size() +
													getIndexForTime(entry.toString(), currSensorTimeRowIndex);

								}
							}
							// get the variable name for each element in the row
							Variable senPropVar = netcdf.findVariable(sensProp.getPropertyName());
							if(senPropVar != null){
								propertyList.get(bin).get(sensProp)[rowIndex] = entry;
							}
							else
								propertyList.get(bin).get(sensProp)[rowIndex] = Float.NaN;

						}

					
						propertyIndex++;

					}
							sensorIndexArray[rowIndex] = sensorIndex;

					if( sensorModel.getSensorDataRecord().getTimeSensorDataMap().size() == 1)
						rowIndex++;

				}

			}
			timeRowIndex.addAll(currSensorTimeRowIndex);
			
		}
		writeObsData(propertyList, sensorIndexArray, netcdf);
	}



	private void writeObsData(Map<Integer,Map<SensorProperty, Object[]>> propertyList, 
			int sensorIndexArray[], 
			NetcdfFileWriter netcdf) 
					throws IOException, InvalidRangeException{
		Variable sensIndex = netcdf.findVariable(SENSOR_INDEX);

		if(sensIndex != null) {
			Array sensArrayIndex = Array.factory(DataType.INT.getPrimitiveClassType(),
					sensIndex.getShape(), sensorIndexArray);
			netcdf.write(sensIndex, sensArrayIndex);
			boolean profileData = false;
			if(propertyList.size() > 1)
				profileData = true;
			Map<Variable, ArrayFloat.D2> binVarDataMap= new HashMap<Variable, ArrayFloat.D2>();
			for(int bin :  propertyList.keySet() ){
				int[] curI = new int[2];
				curI[0]= 0;
				for(SensorProperty sensProp: propertyList.get(bin).keySet()){
					Object[] propToWrite =  propertyList.get(bin).get(sensProp);

					PropertyType propType = sensProp.getPropertyType();
					Variable var = netcdf.findVariable(sensProp.getPropertyName());
					if(var != null){
						if(propType == PropertyType.QUANTITY ) {
							float[] quant = new float[propToWrite.length];
							int quantIndex = 0;
							for(Object quantOb : propToWrite) {
								if(quantOb != null)
									quant[quantIndex] = (float)((double) ((Double)quantOb));
								//else quant[quantIndex] = Float.NaN;
								quantIndex++;
							}
							if(!profileData){
								Array floatArray = Array.factory(DataType.FLOAT.getPrimitiveClassType(),
										var.getShape(), quant);
								netcdf.write(var, floatArray);
							}
							else {
								ArrayFloat.D2 quantArray;
								int cur = 0;
								
								if(binVarDataMap.get(var) == null){
									binVarDataMap.put(var, (new ArrayFloat.D2(
											obsDimension.getLength(), propertyList.size())));
								}

								quantArray = binVarDataMap.get(var);
								for(float quantEntry : quant){		
									//	if(quantEntry != Float.NaN) 
									quantArray.set(cur,bin, quantEntry);

									cur++;
								}

								//netcdf.write(var,  quantArray);

							}
						}
						else if(propType == PropertyType.TIME){
							ArrayChar time2 = new ArrayChar.D2(this.obsDimension.getLength(),
									this.timeLenDimension.getLength());
							int quantIndex = 0;
							for(Object quantOb : propToWrite){
								if(quantOb != null){
									DateTime dt = DateTime.parse(quantOb.toString());
									time2.setString(quantIndex,dt.toString(DATE_FORMAT_STR));									
								}
								quantIndex++;
							}
							netcdf.write(var, time2);
						}

					}
				}
				}
			
			if(profileData){
				for(Variable var : binVarDataMap.keySet()){
					netcdf.write(var, binVarDataMap.get(var));
				}
			}
		}

	}

	private void writeCoordinate(Coordinate coord, String prefix, NetcdfFileWriter netcdf) 
			throws IOException, InvalidRangeException{
		if(coord != null){
			Variable coordVar = netcdf.findVariable(prefix + coord.getAxisId());
			if(coordVar != null){
				float coordVal[] = new float[1];
				coordVal[0] = (float)((double) coord.getCoordinateValue());
				if(coordVal != null) {
					Array coordArr = Array.factory(DataType.FLOAT.getPrimitiveClassType(),
							coordVar.getShape(), coordVal);
					netcdf.write(coordVar, coordArr);
				}
			}
		}
	}


	private int computeNumberRows(StationModel stationRecord){
		int numRows = 0;
		if(stationRecord != null) {
			for(String sensorKey : stationRecord.getSensorIdtoSensorDataMap().keySet()){
				SensorModel sensorModel = stationRecord.getSensorIdtoSensorDataMap().get(sensorKey);
				List<Object> uniqueTimes = new ArrayList<Object>();
				List<SensorProperty> sensPropList = sensorModel.getSensorDataRecord().getModeledProperties();

				for(Integer bin : sensorModel.getSensorDataRecord().getTimeSensorDataMap().keySet()){
					if(sensorModel.getSensorDataRecord().getTimeSensorDataMap().size() >1) {
						int propertyIndex = 0;
						for(Object[] sensorRow :  
							sensorModel.getSensorDataRecord().getTimeSensorDataMap().get(bin)){
							propertyIndex = 0;
							for(Object entry : sensorRow) {
								SensorProperty sensProp = sensPropList.get(propertyIndex);
								if(sensProp != null && sensProp.getPropertyType() == PropertyType.TIME){

									if(!uniqueTimes.contains(entry)) {
										uniqueTimes.add(entry);
									}
								}
								propertyIndex++;

							}
						}
					}
					else {
						for(Object[] sensorRow :  
							sensorModel.getSensorDataRecord().getTimeSensorDataMap().get(bin)){
							numRows++; 
						}
					}
				}
				numRows+=uniqueTimes.size();

				

			}
			}
		return numRows;
	}

	private int getLongestSensorName(StationModel stationRecord) {
		int longLen = 0;
		for(String sensorKey : stationRecord.getSensorIdtoSensorDataMap().keySet()){
			if(sensorKey.length() > longLen)
				longLen = sensorKey.length();
		}
		return longLen+1;
	}

	private void addSensorHeight(Coordinate coordH, NetcdfFileWriter ncFile, String prefix){
		if(coordH != null && ncFile.findVariable(prefix + coordH.getAxisId()) == null){
			Variable hVar = ncFile.addVariable(null, prefix + coordH.getAxisId(),
					DataType.FLOAT, new ArrayList<Dimension>());

			ncFile.addVariableAttribute(hVar, new Attribute(LONG_NAME, 
					coordH.getCoordinateName()));

			ncFile.addVariableAttribute(hVar, new Attribute(UNITS, 
					coordH.getUnitOfMeasure()));

		}

	}

	private int computeMaxBins(StationModel stationRecord){

		int maxBins = 0;
		for(String sensorKey : stationRecord.getSensorIdtoSensorDataMap().keySet()){
			SensorModel sensorModel = stationRecord.getSensorIdtoSensorDataMap().get(sensorKey);
			if(sensorModel.getSensorDataRecord().getTimeSensorDataMap().size()	> maxBins)
				maxBins = sensorModel.getSensorDataRecord().getTimeSensorDataMap().size();
		}
		return maxBins;
	}

	private void addStationObservations(StationModel stationRecord, NetcdfFileWriter ncFile,
			SweFileType featureType){
		if(stationRecord != null) {
			int numberRows = computeNumberRows(stationRecord);
			int longestSensorName = getLongestSensorName(stationRecord);
			obsDimension = ncFile.addDimension(null, OBS_DIM, numberRows);
			sensorStrDim =ncFile.addDimension(null, SENSOR_STR_LEN, longestSensorName);
			numSensors = ncFile.addDimension(null, SENSOR_DIM,
					stationRecord.getSensorIdtoSensorDataMap().size() );
			List<Dimension> sensorDimList = new ArrayList<Dimension>();
			sensorDimList.add(numSensors);
			List<Dimension> profileDimList = new ArrayList<Dimension>();
			List<Dimension> timeDims = new ArrayList<Dimension>();

			if(featureType == SweFileType.TIME_SERIES_PROFILE) {
				binDimension = ncFile.addDimension(null,BINS, computeMaxBins(stationRecord));
				profileDimList.add(obsDimension);

				profileDimList.add(binDimension);
			}
			sensorDimList.add(sensorStrDim);
			ncFile.addVariable(null, SENSOR,  DataType.CHAR, sensorDimList);
			Attribute sensorLongName = new Attribute(LONG_NAME, SENSOR_LNAME);
			Variable sensorIndex = ncFile.addVariable(null, SENSOR_INDEX, DataType.INT, OBS_DIM);
			ncFile.addVariableAttribute(sensorIndex, sensorLongName);
			timeLenDimension = ncFile.addDimension(null, TIME_LEN_DIM, DATE_FORMAT_STR.length());
			timeDims.add(obsDimension);
			timeDims.add(timeLenDimension);
			for(String sensorKey : stationRecord.getSensorIdtoSensorDataMap().keySet()){
				SensorModel sensorModel = stationRecord.getSensorIdtoSensorDataMap().get(sensorKey);

				addVectorLocation(sensorModel.getSensorLocation(), ncFile,  getSensorPrefix());

				addSensorHeight(sensorModel.getSensorHeight(), ncFile, getSensorPrefix());

				List<SensorProperty> sensorData = sensorModel.getSensorDataRecord().getModeledProperties();

				if(sensorData != null){
					for(SensorProperty modeledProp : sensorData){
						if(modeledProp.getPropertyType() == PropertyType.QUANTITY ||
								modeledProp.getPropertyType() == PropertyType.TIME) {
							if(ncFile.findVariable(modeledProp.getPropertyName()) == null){
								Variable propVar;

								if(modeledProp.getPropertyType() == PropertyType.QUANTITY) 
									if(featureType == SweFileType.TIME_SERIES)
										propVar =	ncFile.addVariable(null, 
												modeledProp.getPropertyName(), DataType.FLOAT, OBS_DIM);
									else
										propVar =	ncFile.addVariable(null, 
												modeledProp.getPropertyName(), DataType.FLOAT, profileDimList);
								else {
									propVar = ncFile.addVariable(null, modeledProp.getPropertyName(), 
											DataType.CHAR, timeDims);

								}
								if(modeledProp.getSensorUnitOfMeasure() != null){
									Attribute unitMeasure = new Attribute(UNITS, modeledProp.getSensorUnitOfMeasure());
									ncFile.addVariableAttribute(propVar, unitMeasure);

								}
								if(modeledProp.getSensorType() != null){
									Attribute longName = new Attribute(LONG_NAME, modeledProp.getSensorType());
									ncFile.addVariableAttribute(propVar, longName);
								}
							}
						}
					}
				}	
			}

		}

	}

	private void addVectorLocation(VectorModel vect, NetcdfFileWriter netcdf, String prefix){
		if(vect != null){
			Coordinate x = vect.getXCoordinate();
			Coordinate y = vect.getYCoordinate();
			Coordinate z = vect.getZCoordinate();
			if(netcdf.findVariable( prefix + x.getAxisId()) == null) {
				Variable xVar = netcdf.addVariable(null, prefix + x.getAxisId(),
						DataType.FLOAT, new ArrayList<Dimension>());
				netcdf.addVariableAttribute(xVar, new Attribute(LONG_NAME, x.getCoordinateName()));
				netcdf.addVariableAttribute(xVar, new Attribute(UNITS,   x.getUnitOfMeasure()));
			}
			if(netcdf.findVariable( prefix + y.getAxisId()) == null) {

				Variable yVar = netcdf.addVariable(null,prefix +  y.getAxisId(),
						DataType.FLOAT, new ArrayList<Dimension>());
				netcdf.addVariableAttribute(yVar, new Attribute(LONG_NAME,  y.getCoordinateName()));
				netcdf.addVariableAttribute(yVar, new Attribute(UNITS, y.getUnitOfMeasure()));
			}
			if(netcdf.findVariable( prefix + z.getAxisId()) == null) {

				Variable zVar = netcdf.addVariable(null,prefix +  z.getAxisId(), 
						DataType.FLOAT, new ArrayList<Dimension>());
				netcdf.addVariableAttribute(zVar, new Attribute(LONG_NAME, z.getCoordinateName()));
				netcdf.addVariableAttribute(zVar, new Attribute(UNITS, z.getUnitOfMeasure()));
			}
		}
	}


	private void addStationAndLocationStructure(NetcdfFileWriter netcdf, 
			String stationName, StationModel stationModel){

		//Variable station = new Variable(netcdf, null, stationName);

		netcdf.addDimension(null, STATION_STR_LEN, stationName.length());
		Variable station = netcdf.addVariable(null, STATION, DataType.CHAR, STATION_STR_LEN);

		addVectorLocation(stationModel.getPlatformLocation(), netcdf,  getStationPrefix());

	}
}
