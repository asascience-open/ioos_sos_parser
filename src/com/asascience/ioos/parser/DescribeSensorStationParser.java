package com.asascience.ioos.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.joda.time.DateTime;

import com.asascience.ioos.exception.IoosSosParserException;
import com.asascience.ioos.exception.IoosSosParserException.IoosSosParserErrors;
import com.asascience.ioos.model.AddressModel;
import com.asascience.ioos.model.LatLonPoint;
import com.asascience.ioos.model.describe.ComponentData;
import com.asascience.ioos.model.describe.ContactData;
import com.asascience.ioos.model.describe.DescribeSensorStation;
import com.asascience.ioos.model.describe.DescriptionObject;
import com.asascience.ioos.model.describe.DocumentationObject;
import com.asascience.ioos.model.describe.EventObject;
import com.asascience.ioos.model.describe.QuantityObject;

public class DescribeSensorStationParser  extends BaseParser{
	private final String memberTag = "member";
	private final String systemTag = "System";
	private final String identificationTag = "identification";
	private final String identifierListTag  = "IdentifierList";
	private final String identifierTag = "identifier";
	private final String termTag = "Term";
	private final String classificationTag = "classification";
	private final String classListTag = "ClassifierList";
	private final String classifierTag = "classifier";
	private final String networkIdDef = "http://mmisw.org/ont/ioos/definition/networkID";
	private final String shortNameDef = "http://mmisw.org/ont/ioos/definition/shortName";
	private final String longNameDef = "http://mmisw.org/ont/ioos/definition/longName";
	private final String capabilitiesTag = "capabilities";
	private final String contactTag = "contact";
	private final String contactListTag = "ContactList";
	private final String responsiblePartyTag = "ResponsibleParty";
	private final String organizationNameTag ="organizationName";
	private final String componentsTag = "components";
	private final String componentListTag = "ComponentList";
	private final String componentTag = "component";
	private final String lineStringTag = "LineString";
	private final String locationTag = "location";
	private final String outputsTag = "outputs";
	private final String outputListTag = "OutputList";
	private final String outputTag = "output";
	private final String addressTag = "address";
	private final String deliveryPointTag ="deliveryPoint";
	private final String cityTag ="city";
	private final String administrativeAreaTag = "administrativeAreaTag";
	private final String postalCodeTag = "postalCode";
	private final String countryTag = "country";
	private final String emailTag ="electronicMailAddress";
	private final String obsTimeRangeDef ="http://mmisw.org/ont/ioos/definition/observationTimeRange";
	private final String simpleDataRecordTag = "SimpleDataRecord";
	private final String textTag = "Text";
	private final String documentationTag = "documentation";
	private final String documentListTag ="DocumentList";
	private final String documentTag ="Document";
	private final String onlineResTag ="onlineResource";
	private final String arcRoleTag ="arcrole";
	private final String formatTag ="format";
	private final String historyTag = "history";
	private final String eventListTag ="EventList";
	private final String eventTag = "Event";
	private final String dateTag = "date";
	private final String ioosServiceMetadata = "ioosServiceMetadata";
	
	public DescribeSensorStationParser(){
		
	}
	
	
	public DescribeSensorStation parseDescribeStation(URL xmlUrl) throws IoosSosParserException{
		DescribeSensorStation describeStation = null;
		URLConnection connect;
		try {
			connect = xmlUrl.openConnection();
	
		InputStream isReader = connect.getInputStream();
		if(isReader != null){
			Document xmlDoc = new SAXBuilder().build(isReader);
			describeStation = parseDescribeStation(xmlDoc);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return describeStation;
	
	}
	
	
	private DescribeSensorStation parseDescribeStation(Document xmlDoc) throws IoosSosParserException{
		DescribeSensorStation describeStation = null;
		Element root = xmlDoc.getRootElement();
		describeStation = new DescribeSensorStation();

		initNamespaces(root);

		for(Element capa : root.getChildren(capabilitiesTag, smlNs)){
			String version = parseVersionMetadata(capa);
			if(version != null)
				describeStation.setServiceVersion(version);
		}
		parseMemberData(root.getChild(memberTag, smlNs), describeStation);
		return describeStation;
	}
	
	public DescribeSensorStation parseDescribeStation(String xmlFileName) 
			throws JDOMException, IOException, IoosSosParserException{
		File xmlFile = new File(xmlFileName);
		DescribeSensorStation describeStation = null;
		if(xmlFile.exists()){
			describeStation = new DescribeSensorStation();
			Document xmlDoc = new SAXBuilder().build(xmlFile);
			describeStation = parseDescribeStation(xmlDoc);
		}
		return describeStation;
	}
	
	private Map<String, DescriptionObject> parseIdentificationRecords(Element idElem) 
			throws IoosSosParserException{
		Map<String, DescriptionObject> idMap = new HashMap<String, DescriptionObject>();
		boolean foundNetworkId = false;
		boolean foundShortName = false;
		boolean foundLongName = false;
		if(idElem != null){
		Element idList = idElem.getChild(identifierListTag, smlNs);
		if(idList != null){
			for(Element id : idList.getChildren(identifierTag, smlNs)){
				Element term = id.getChild(termTag, smlNs);
				if(term != null){
					String def = term.getAttributeValue(attDefinitionTag);
					DescriptionObject desc = new DescriptionObject();
					desc.setName(id.getAttributeValue(nameTag));
					desc.setValue( term.getChildText(valueTag, smlNs));
					idMap.put(def,  desc);
					switch(def){
					case networkIdDef:
						foundNetworkId = true;
						break;
					case shortNameDef:
						foundShortName = true;
						break;
					case longNameDef:
						foundLongName = true;
						break;
					}
				}
				
			}
		}
		}
//		if(!foundNetworkId || !foundShortName || !foundLongName){
//			System.out.println(foundNetworkId + " " + foundShortName + " " + foundLongName);
//			throw new IoosSosParserException(IoosSosParserErrors.IDENTIFIER_MISSING);
//		}
		return idMap;
	}
	
	
	private String parseVersionMetadata(Element capaElement){
		String version = null;
		if(capaElement != null && capaElement.getAttributeValue(nameTag).equals(ioosServiceMetadata)){
			Element simpleData = capaElement.getChild(simpleDataRecordTag, sweNs);
			if(simpleData != null){
				for(Element field : simpleData.getChildren(fieldTag, sweNs)){
				
						Element textE = field.getChild(textTag, sweNs);
						if(textE != null){
							version = textE.getChildText(valueTag, sweNs);
						}
					
				}
			}
		}
			return version;
	}
		
	private void parseClassificationRecords(Element classElem, DescribeSensorStation dStation){
		if(classElem == null)
			return;
		
		Element classList = classElem.getChild(classListTag, smlNs);
		if(classList != null){
			for(Element classifier : classList.getChildren(classifierTag, smlNs)){
				Element term = classifier.getChild(termTag, smlNs);
				if(term != null){
					String def = term.getAttributeValue(attDefinitionTag);
					DescriptionObject desc = new DescriptionObject();
					desc.setName(classifier.getAttributeValue(nameTag));
					desc.setValue( term.getChildText(valueTag, smlNs));
					
					Element codeSpace = term.getChild(codeSpaceTag, smlNs);
					if(codeSpace != null)
						desc.setCodeSpace(codeSpace.getAttributeValue(xlinkAttributeHrefTag, xlinkNs));
					dStation.getClassificationMap().put(def, desc);
				}
			}
		}
	}
	
	private DateTime[] parseTimeCapabilitiesRecord(Element capaElement){
		DateTime[] dataTime = null;
		if(capaElement != null) {
		Element dataRec = capaElement.getChild(dataRecordTag, sweNs);
		if(dataRec != null){
			for(Element field : dataRec.getChildren(fieldTag, sweNs)){
				Element timeRange = field.getChild(timeRangeTag, sweNs);
				if(timeRange != null && timeRange.getAttributeValue(attDefinitionTag).equals(obsTimeRangeDef)){
					String rep = timeRange.getChildText(valueTag, sweNs);
					if(rep != null){
						String [] timeRep = rep.split(" ");
						if(timeRep.length == 2){
							dataTime = new DateTime[2];
							dataTime[0] = DateTime.parse(timeRep[0]);
							dataTime[1] = DateTime.parse(timeRep[1]);
						}
					}
				}
			}
		}
		}
		return dataTime;
	}
	
	private Map<String, DescriptionObject> parseCapabilitiesRecord(Element capaElement){
		Map<String, DescriptionObject> capaMap = null;
		if(capaElement != null){
			Element simpleData = capaElement.getChild(simpleDataRecordTag, sweNs);
			if(simpleData != null){
				for(Element field : simpleData.getChildren(fieldTag, sweNs)){
					Element textE = field.getChild(textTag, sweNs);
					if(textE != null){
						String def = textE.getAttributeValue(attDefinitionTag);
						String val = textE.getChildText(valueTag, sweNs);
						DescriptionObject ob = new DescriptionObject();
						ob.setName(def);
						ob.setValue(val);
						if(capaMap == null)
							capaMap = new HashMap<String, DescriptionObject>();
						capaMap.put(def, ob);
					}
					
				}
			}
		}
		return capaMap;
	}
	
	private ContactData parseContact(Element responseParty){
		ContactData contact = null;
		if(responseParty  != null) {
			contact = new ContactData();
			Element contactInfo = responseParty.getChild(smlContactInfoTag, smlNs);
			if(contactInfo != null)
				parseContactFromData(contactInfo.getChild(addressTag, smlNs), contact);
			
			contact.setOrganizationName(responseParty.getChildText(organizationNameTag, smlNs));
			
		}
		return contact;
	}
	
	protected AddressModel parseContactFromData(Element dataElem, AddressModel address){
		if(dataElem != null){
			for(Element childElem : dataElem.getChildren()){
			String dataText = childElem.getText();
			switch(childElem.getName()){
	
			case deliveryPointTag:
				address.setAddressDeliveryPoint(dataText);
				break;
			case cityTag:
				address.setAddressCity(dataText);
				break;
			case administrativeAreaTag:
				address.setAddressAdminArea(dataText);
				break;
			case postalCodeTag:
				address.setAddressZipCode(dataText);
				break;
			case countryTag:
				address.setAddressCountry(dataText);
				break;
			case emailTag:
				address.setContactEmail(dataText);
				break;
			}
			}
		}
		return address;
	}

	

	
	private List<ContactData> parseContactDataList(Element contactE){
		List<ContactData> contact = new ArrayList<ContactData>();
		if(contactE != null){
			Element contactList = contactE.getChild(contactListTag, smlNs);
			if(contactList != null){
				for(Element member : contactList.getChildren(memberTag, smlNs)){
					ContactData contactData = parseContact(member.getChild(responsiblePartyTag, smlNs));
					if(contactData != null)
						contact.add(contactData);
				}
			}
		}
		return contact;
	}

	private List<LatLonPoint> parseLocationPts(Element locationElem){
		List<LatLonPoint> latLonPoints = new ArrayList<LatLonPoint>();
		if(locationElem != null){
			for(Element point : locationElem.getChildren()){
				if(point.getNamespace().equals(gmlNs)){
					if(point.getName().equals(gmlPointTag)){
						String rep = point.getChildText(gmlPosTag, gmlNs);
						if(rep != null){
							String [] ll = rep.split(" ");
							if(ll.length == 2){
								LatLonPoint llPt = new LatLonPoint(Double.valueOf(ll[0]), 
										Double.valueOf(ll[1]));
								latLonPoints.add(llPt);
							}
						}
					}
					else if(point.getName().equals(lineStringTag)){
						String rep = point.getChildText(gmlPosTag, gmlNs);
						if(rep != null){
							String [] ll = rep.split(" ");
							if(ll.length % 2 == 0){
								for(int i = 0; i < ll.length; i = i + 2){
									LatLonPoint llPt = new LatLonPoint(Double.valueOf(ll[i]), 
											Double.valueOf(ll[i + 1]));
									latLonPoints.add(llPt);
								}
							}
						}
					}
				}
			}
		}
		return latLonPoints;
	}
	
	private Map<String, QuantityObject> parseOutputList(Element outputs){
		Map<String, QuantityObject> outputMap = new HashMap<String, QuantityObject>();
		if(outputs != null){
			Element outputList = outputs.getChild(outputListTag, smlNs);
			if(outputList != null){
				for(Element output : outputList.getChildren(outputTag, smlNs)){
					String name = output.getAttributeValue(nameTag);
					Element quantity = output.getChild(quantityTag, sweNs);
					if(quantity != null){
						String def = quantity.getAttributeValue(attDefinitionTag);
						QuantityObject quantObject = new QuantityObject();
						quantObject.setQuantityDef(def);
						quantObject.setQuantityName(name);
						Element uom = quantity.getChild(uomTag, sweNs);
						if(uom != null)
							quantObject.setUnitOfMeasure(uom.getAttributeValue(codeTag));
						if(def != null)
							outputMap.put(name, quantObject);
					}
				}
				
			}
		}
		return outputMap;
	}
	
	private List<ComponentData> parseComponents(Element components) throws IoosSosParserException{
		List<ComponentData> componentDataList = new ArrayList<ComponentData>();

		if(components != null){
			Element componentList = components.getChild(componentListTag, smlNs);
			if(componentList != null){
				for(Element comp : componentList.getChildren(componentTag, smlNs)){
					ComponentData componentData = new ComponentData();
					componentData.setComponentName(comp.getAttributeValue(nameTag));
					Element systemElem = comp.getChild(systemTag, smlNs);

					if(systemElem != null){

						componentData.setDescription(systemElem.getChildText(gmlDescriptionTag, gmlNs));

						Element childElem = systemElem.getChild(identificationTag, smlNs);
						if(childElem != null) {
							componentData.setIdentificationReference(childElem.getAttributeValue(xlinkAttributeHrefTag, xlinkNs));
							componentData.setIdentificationMap(parseIdentificationRecords(childElem));
						}

						childElem = systemElem.getChild(documentationTag, smlNs);
						if(childElem != null)
							componentData.setDocumentationReference(childElem.getAttributeValue(xlinkAttributeHrefTag, xlinkNs));
						// parse the date data

						DateTime[] startEnd = parseTimeCapabilitiesRecord(systemElem.getChild(capabilitiesTag, smlNs));
						if(startEnd != null && startEnd.length == 2){
							componentData.setComponentStartDate(startEnd[0]);
							componentData.setComponentEndDate(startEnd[1]);

						}


						componentData.setLocationPoints(parseLocationPts(
								systemElem.getChild(locationTag, smlNs)));
						componentData.setOutputQuantitiesMap(parseOutputList(
								systemElem.getChild(outputsTag, smlNs)));
					}
					componentDataList.add(componentData);

				}


			}
		}
		return componentDataList;
	}
	
	
	private DocumentationObject parseDocObject(Element docObj, String name, String role){
		DocumentationObject documentation = null;
		if(docObj != null){
			documentation = new DocumentationObject();
			documentation.setName(name);
			documentation.setRole(role);
			documentation.setDescription(docObj.getChildText(gmlDescriptionTag, gmlNs));
			documentation.setFormat(docObj.getChildText(formatTag, smlNs));
			documentation.setOnlineResource(docObj.getChildText(onlineResTag, smlNs));
		}
		return documentation;
	}
	private List<DocumentationObject> parseDocumentation(Element docElem){
		List<DocumentationObject> docList = new ArrayList<DocumentationObject>();
		
		if(docElem != null){
			Element docElemList = docElem.getChild(documentListTag, smlNs);
			if(docElemList != null){
				for(Element member : docElemList.getChildren(memberTag, smlNs)){
					DocumentationObject doc = parseDocObject(member.getChild(documentTag, smlNs),
							member.getAttributeValue(nameTag), member.getAttributeValue(arcRoleTag, xlinkNs));
					
					if(doc != null){
						docList.add(doc);
					}
					
				}
			}
			
		}
		return docList;
	}
	
	

	private EventObject parseEventObject(Element eventObj){
		EventObject event = null;
		if(eventObj != null){
			event = new EventObject();
			String strDate = eventObj.getChildText(dateTag, smlNs);
			if(strDate != null)
				event.setEventDate(DateTime.parse(strDate));
			event.setDescription(eventObj.getChildText(gmlDescriptionTag, gmlNs));
			Element docElem = eventObj.getChild(documentationTag, smlNs);
			if(docElem != null){
				event.setDocumentationLink(docElem.getAttributeValue(this.xlinkAttributeHrefTag, xlinkNs));
			}
			
		}
		return event;
	}
	private List<EventObject> parseHistory(Element historyElem){
		List<EventObject> eventList = new ArrayList<EventObject>();
		
		if(historyElem != null){
			Element docElemList = historyElem.getChild(eventListTag, smlNs);
			if(docElemList != null){
				for(Element member : docElemList.getChildren(memberTag, smlNs)){
					EventObject event = parseEventObject(member.getChild(eventTag, smlNs));
					if(event != null){
						eventList.add(event);
					}
					
				}
			}
			
		}
		return eventList;
	}
	
	
	
	private void parseMemberData(Element memberElem, DescribeSensorStation describeStation) 
			throws IoosSosParserException{
		if(memberElem == null) 
			return;
		Element systemElem = memberElem.getChild(systemTag, smlNs);
		if(systemElem != null){
			describeStation.setBoundingBox(parseBoundedBy(systemElem.getChild(gmlBoundedByTag, gmlNs)));

			describeStation.setName(systemElem.getChildText(nameTag, gmlNs));
			describeStation.setDescription(systemElem.getChildText(gmlDescriptionTag, gmlNs));
			describeStation.setIdentifierMap(parseIdentificationRecords(
					systemElem.getChild(identificationTag, smlNs)));
			parseClassificationRecords(systemElem.getChild(classificationTag, smlNs), describeStation);
			for(Element capaElem : systemElem.getChildren(capabilitiesTag, smlNs)){
				DateTime[] startEnd = parseTimeCapabilitiesRecord(capaElem);
				if(startEnd != null && startEnd.length == 2){
					describeStation.setStartSamplingTime(startEnd[0]);
					describeStation.setEndSamplingTime(startEnd[1]);

				}
				else {
					Map<String, DescriptionObject> obj = parseCapabilitiesRecord(capaElem);
					if(obj != null){
						describeStation.getCapabilitiesMap().putAll(obj);
					}
				}
			}
			describeStation.setContactList(parseContactDataList(systemElem.getChild(contactTag, smlNs)));
			describeStation.setComponentList(parseComponents(systemElem.getChild(componentsTag, smlNs)));
			describeStation.setDocumentationList(
					parseDocumentation(systemElem.getChild(documentationTag, smlNs)));
			describeStation.setHistoryList(parseHistory(systemElem.getChild(historyTag, smlNs)));
		}
	}
}
