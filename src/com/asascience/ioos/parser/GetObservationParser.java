/*
 * Package to parse a getObservation
 * @author cmorse
 */
 package com.asascience.ioos.parser;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.joda.time.DateTime;

import com.asascience.ioos.exception.GetObservationException;
import com.asascience.ioos.exception.GetObservationException.GetObservationErrors;
import com.asascience.ioos.model.FeatureCollectionModel;
import com.asascience.ioos.model.GetObservation;
import com.asascience.ioos.model.LatLonPoint;
import com.asascience.ioos.model.MemberObservation;
import com.asascience.ioos.model.ObservedPropertyModel;
import com.asascience.ioos.model.SweDataRecord;


public class GetObservationParser {
	private final String omNsTag = "om";
	private final String gmlNsTag = "gml";
	private final String sweNsTag = "swe";
	private final String swe2NsTag = "swe2";
	private final String xlinkNsTag = "xlink";
	private final String xsiNsTag = "xsi";
	private final String schemaLocTag = "schemaLocation";	
	private final String memberTag ="member";
	private final String gmlMetaDataTag = "metaDataProperty";
	private final String xlinkAttributeTitleTag = "title";
	private final String xlinkAttributeHrefTag = "href";
	private final String genericMetaDataTag = "GenericMetaData";
	private final String gmlDescriptionTag = "description";
	private final String gmlVersionTag = "version";
	private final String omObservationTag = "Observation";
	private final String omSamplingTime = "samplingTime";
	private final String gmlTimePeriod = "TimePeriod";
	private final String gmlBeginPosition = "beginPosition";
	private final String gmlEndPosition = "endPosition";
	private final String omProcedureTag = "procedure";
	private final String omProcessTag = "Process";
	private final String sweCompositePhenomTag ="CompositePhenomenon";
	private final String sweComponentTag = "component";
	private final String gmlIdTag = "id";
	private final String gmlNameTag = "name";
	private final String dimensionTag = "dimension";
	private final String omObservedPropertyTag = "observedProperty";
	private final String omFeatureTag = "featureOfInterest";
	private final String omResultTag = "result";
	private final String gmlFeatureCollectionTag = "FeatureCollection";
	private final String gmlCodeSpaceTag = "codeSpace";
	private final String gmlBoundedByTag = "boundedBy";
	private final String gmlEnvelopeTag = "Envelope";
	private final String gmlLowerCornerTag = "lowerCorner";
	private final String gmlUpperCornerTag =  "upperCorner";
	private final String gmlPosTag =  "pos";
	private final String gmlSrsAttTag = "srsName";
	private final String gmlLocationTag = "location";
	private final String gmlMultiPointTag = "MultiPoint";
	private final String gmlPointTag = "Point";
	private final String gmlPointMembersTag = "pointMembers";
	private final String swe2DataRecordTag = "DataRecord";

	
	private Namespace omNs;
	private Namespace gmlNs;
	private Namespace sweNs;
	private Namespace swe2Ns;
	private Namespace xlinkNs;
	
	private String xsiSchemaLocation;
	
	public GetObservationParser(){
		
	}
	
	
	// initialize the getObservation namespaces
	private void initGoNamespaces(Element root){
		for(Namespace ns : root.getAdditionalNamespaces()){
			switch(ns.getPrefix()){
			case omNsTag:
				omNs = ns;
				break;
			case gmlNsTag:
				gmlNs = ns;
				break;
			case sweNsTag:
				sweNs = ns;
				break;
			case xlinkNsTag:
				xlinkNs = ns;
				break;
			case swe2NsTag:
				swe2Ns = ns;
				break;
			}
		}
		
	}
	
	// Parse an observation record
	private void processObservation(Element obsElement, MemberObservation memberObs) throws MalformedURLException{
		if(obsElement == null) return;
		
		memberObs.setDescription(obsElement.getChildText(gmlDescriptionTag, gmlNs));
		
		Element samplingElem = obsElement.getChild(omSamplingTime, omNs);
		if(samplingElem != null){
			// Get the start and end time periods
			Element timePeriod = samplingElem.getChild(gmlTimePeriod, gmlNs);
			String beginPos = timePeriod.getChildText(gmlBeginPosition, gmlNs);
			String endPos = timePeriod.getChildText(gmlEndPosition, gmlNs);
			
			DateTime dateTime = DateTime.parse(beginPos);
			memberObs.setStartSamplingTime(dateTime);
			dateTime = DateTime.parse(endPos);
			memberObs.setEndSamplingTime(dateTime);
		}
		
		Element procedureElem = obsElement.getChild(omProcedureTag, omNs);
		if(procedureElem != null){
			// get the station keys
			Element processElem = procedureElem.getChild(omProcessTag, omNs);
			String stationKey;
			for(Element stationElem : processElem.getChildren(memberTag, gmlNs)){
				stationKey = stationElem.getAttributeValue(xlinkAttributeHrefTag, xlinkNs);
				if(memberObs.getFeatureCollectionProperties() ==  null){
					FeatureCollectionModel featureProp = new FeatureCollectionModel();
					memberObs.setFeatureCollectionProperties(featureProp);
				}
				memberObs.getFeatureCollectionProperties().addStationKey(stationKey);
			}
		}

		// process the observed properties
		parseObservedProperties(obsElement.getChild(omObservedPropertyTag, omNs), memberObs);
		
		// process the feature list
		parseFeatureList(obsElement.getChild(omFeatureTag, omNs), memberObs);
		
		// process the result block
		parseResultRecord(obsElement.getChild(this.omResultTag, omNs), memberObs);
	}

	
	// parses the result block
	private void parseResultRecord(Element resultElem, MemberObservation memberObs){
		
		if(resultElem != null){
			SweDataRecordParser sweParser = new SweDataRecordParser(memberObs.getFeatureCollectionProperties().getFeatureType());
			sweParser.parseSweDataRecord(resultElem.getChild(swe2DataRecordTag, swe2Ns), memberObs);
		}
	}
	
	// parse the feature list record
	private void parseFeatureList(Element featureElem, MemberObservation memberObs)
		throws NumberFormatException{
		if(featureElem != null){
			FeatureCollectionModel featureModel; 
			LatLonPoint latLonPt;
			if(memberObs.getFeatureCollectionProperties() ==  null){
				featureModel = new FeatureCollectionModel();
			}
			else {
				featureModel = memberObs.getFeatureCollectionProperties();
			}
			Element featureCollectionElem = featureElem.getChild(gmlFeatureCollectionTag, gmlNs);
			if(featureCollectionElem != null){
				System.out.println("Feature coll"+featureCollectionElem.toString());
				Element childDataElem = featureCollectionElem.getChild(gmlMetaDataTag, gmlNs);
				if(childDataElem != null){
					Element featureType = childDataElem.getChild(gmlNameTag, gmlNs);
					featureModel.setFeatureCodeSpace(featureType.getAttributeValue(gmlCodeSpaceTag));
					featureModel.setFeatureType(featureType.getValue());
				}
				// parse the bounding box of the feature
				childDataElem = featureCollectionElem.getChild(gmlBoundedByTag, gmlNs);
				if(childDataElem != null){
					Element envelope = childDataElem.getChild(gmlEnvelopeTag, gmlNs);
					if(envelope != null){
					
						featureModel.setBoundingBoxSrsName(envelope.getAttributeValue(gmlSrsAttTag, gmlNs));
						String cornerStr = envelope.getChildText(gmlLowerCornerTag, gmlNs);
					
						String [] latLon = cornerStr.split(" ");
						if(latLon.length == 2){
							latLonPt = new LatLonPoint(Double.valueOf(latLon[0]),
																	Double.valueOf(latLon[1]));
							featureModel.setLowerLeftCornerBB(latLonPt);
							
						}
						 cornerStr = envelope.getChildText(gmlUpperCornerTag, gmlNs);
							latLon = cornerStr.split(" ");
							if(latLon.length == 2){
								latLonPt = new LatLonPoint(Double.valueOf(latLon[0]),
																		Double.valueOf(latLon[1]));
								featureModel.setUpperRightCornerBB(latLonPt);

								
							}
					}
				}
				// get the location for each station
				childDataElem = featureCollectionElem.getChild(gmlLocationTag, gmlNs);
				if(childDataElem != null){
					Element multiPoint = childDataElem.getChild(gmlMultiPointTag, gmlNs);
					if(multiPoint != null){
						Element pointMembers = multiPoint.getChild(gmlPointMembersTag, gmlNs);
						String stationKey;
						String posStr;
						for(Element point : pointMembers.getChildren(gmlPointTag, gmlNs)){
							stationKey = point.getChildText(gmlNameTag, gmlNs);
							posStr = point.getChildText(gmlPosTag, gmlNs);
							
							if(stationKey != null && posStr != null){
								String [] latLon = posStr.split(" ");
								if(latLon.length == 2){
									latLonPt = new LatLonPoint(Double.valueOf(latLon[0]),
											Double.valueOf(latLon[1]));
									featureModel.addStationData(stationKey, latLonPt);
								}
							}
						}
					}
				}
			}
			memberObs.setFeatureCollectionProperties(featureModel);
		}
	}
	
	// parse the om:observedProperty record
	private void parseObservedProperties(Element obPropElem, MemberObservation memberObs){
		if(obPropElem != null){
			System.out.println("--swe composite " + obPropElem.toString());
			ObservedPropertyModel obPropModel = new ObservedPropertyModel();
		
			Element compositeElem = obPropElem.getChild(sweCompositePhenomTag, sweNs);
			
			System.out.println(compositeElem.toString());
			if(compositeElem != null) {
				obPropModel.setPhonemononName(compositeElem.getChildText(gmlNameTag, gmlNs));
				obPropModel.setDimension(compositeElem.getAttributeValue(dimensionTag));
				obPropModel.setPhonemononId(compositeElem.getAttributeValue(gmlIdTag, gmlNs));
				for(Element childProp : compositeElem.getChildren(sweComponentTag, sweNs)){
					String hrefValue = childProp.getAttributeValue(xlinkAttributeHrefTag, xlinkNs);
					obPropModel.addObservedProperty(hrefValue);
				}
				memberObs.setObservedProperties(obPropModel);
			}
		}
	}
	
	// Parse an om:member record
	private void parseMembers(Element root, GetObservation getObservationModel) 
			throws GetObservationException, MalformedURLException{
		for(Element member : root.getChildren(memberTag, omNs)){
			MemberObservation memberObs = new MemberObservation();
			
			processObservation(member.getChild(omObservationTag, omNs), memberObs);
			
			getObservationModel.addMemberObservation(memberObs);
		}
		
		if(getObservationModel.getNumberMemberObservations() < 1)
			throw new GetObservationException(GetObservationErrors.MEMBER_REQUIRED);
		
	}
	
	
	// Parse the meta data
	private void parseGmlMetaData(Element root, GetObservation getObservationModel){
		for(Element gmlMetaData : root.getChildren(gmlMetaDataTag, gmlNs)){
			
			if(gmlMetaData.getAttributeValue(xlinkAttributeTitleTag, xlinkNs) != null){
				Element metaDataElement = gmlMetaData.getChild(genericMetaDataTag, gmlNs);
				if(metaDataElement != null){
						System.out.println("----"+metaDataElement.getChildText(gmlDescriptionTag, gmlNs)+"-----");
						getObservationModel.setGmlDescription(metaDataElement.getChildText(gmlDescriptionTag, gmlNs));
					
				}
				else if(gmlMetaData.getChild(gmlVersionTag, gmlNs) != null) {
					String hrefValue = gmlMetaData.getAttributeValue(xlinkAttributeHrefTag, xlinkNs);
					getObservationModel.setIoosSosVersion(gmlMetaData.getChildText(gmlVersionTag, gmlNs), hrefValue);
					
				}
			}
		

		}
	}
	
	
	
	public GetObservation parseGO(String xmlFileName) throws JDOMException, IOException, GetObservationException{
		File xmlFile = new File(xmlFileName);
		GetObservation getObservation = null;

		if(xmlFile.exists()){

			getObservation = new GetObservation();
			Document xmlDoc = new SAXBuilder().build(xmlFile);
			Element root = xmlDoc.getRootElement();
			initGoNamespaces(root);

			parseGmlMetaData(root, getObservation);
			parseMembers(root, getObservation);


		}
		return getObservation;
	}

}