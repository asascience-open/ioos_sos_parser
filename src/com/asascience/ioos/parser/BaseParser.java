package com.asascience.ioos.parser;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.asascience.ioos.model.AddressModel;
import com.asascience.ioos.model.BoundingBox;
import com.asascience.ioos.model.LatLonPoint;

public class BaseParser {
	private final String omNsTag = "om";
	private final String gmlNsTag = "gml";
	private final String sweNsTag = "swe";
	private final String swe2NsTag = "swe2";
	private final String xlinkNsTag = "xlink";
	private final String sosNsTag = "sos";
	private final String owsNsTag = "ows";
	private final String xsiNsTag = "xsi";
	private final String smlNsTag = "sml";
	protected final String fieldTag = "field";
	protected final String uomTag = "uom";
	protected final String codeTag = "code";

	protected final String dataRecordTag = "DataRecord";
	protected final String codeSpaceTag = "codeSpace";
	protected final String attDefinitionTag = "definition";
	protected final String xlinkAttributeHrefTag = "href";
	protected final String gmlMetaDataTag = "metaDataProperty";
	protected final String gmlVersionTag = "version";
	protected final String xlinkAttributeTitleTag = "title";
	protected final String gmlDescriptionTag = "description";
	protected final String nameTag = "name";
	protected final String gmlCodeSpaceTag = "codeSpace";
	protected final String gmlBoundedByTag = "boundedBy";
	protected final String gmlEnvelopeTag = "Envelope";
	protected final String gmlLowerCornerTag = "lowerCorner";
	protected final String gmlUpperCornerTag =  "upperCorner";
	protected final String gmlPosTag =  "pos";
	protected final String gmlSrsNameTag = "srsName";
	protected final String gmlLocationTag = "location";
	protected final String gmlMultiPointTag = "MultiPoint";
	protected final String gmlPointTag = "Point";
	protected final String gmlPointMembersTag = "pointMembers";
	protected final String gmlTimePeriod = "TimePeriod";
	protected final String gmlBeginPosition = "beginPosition";
	protected final String gmlEndPosition = "endPosition";
	protected final String indeterminatePosTag = "indeterminatePosition";
	protected final String timeNowTag = "now";
	protected final String valueTag = "value";
	protected final String timeRangeTag = "TimeRange";
	protected final String deliveryPointTag = "DeliveryPoint";
	protected final String cityTag = "cityTag";
	protected final String administrativeAreaTag = "AdministrativeArea";
	protected final String postalCodeTag = "PostalCode";
	protected final String countryTag = "Country";
	protected final String emailTag = "ElectronicMailAddress";
	protected final String owsContactInfoTag = "ContactInfo";
	protected final String smlContactInfoTag = "contactInfo";
	protected final String gmlIdTag = "id";

	protected final String voiceTag = "Voice";
	protected final String quantityTag = "Quantity";

	protected Namespace omNs;
	protected Namespace gmlNs;
	protected Namespace sweNs;
	protected Namespace swe2Ns;
	protected Namespace xlinkNs;
	protected Namespace sosNs;
	protected Namespace owsNs;
	protected Namespace xsiNs;
	protected Namespace smlNs;
	// initialize the getObservation namespaces
	protected void initNamespaces(Element root){
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
			case sosNsTag:
				sosNs = ns;
				break;
			case owsNsTag:
				owsNs = ns;
				break;
			case xsiNsTag:
				xsiNs = ns;
				break;
			case smlNsTag:
				smlNs = ns;
				break;
			}
		}
		
	}
	protected BoundingBox parseBoundedBy(Element boundedByElem){
		BoundingBox bb = null;
		if(boundedByElem != null){
			Element envelopeE = boundedByElem.getChild(gmlEnvelopeTag, gmlNs);
			if(envelopeE != null){
				bb = new BoundingBox();
				bb.setUpperRightCornerBB(parseCorner(envelopeE.getChild(gmlUpperCornerTag, gmlNs)));
				bb.setLowerLeftCornerBB(parseCorner(envelopeE.getChild(gmlLowerCornerTag, gmlNs)));
			}
		}
		return bb;
	}
	
	private LatLonPoint parseCorner(Element corner){
		LatLonPoint cornerPt = null;
		if(corner != null){
			String cornerStr = corner.getText();
			String [] latLon = cornerStr.split(" ");
			if(latLon.length == 2)
				cornerPt = new LatLonPoint(Double.valueOf(latLon[0]),
														Double.valueOf(latLon[1]));
		}
		return cornerPt;
	}
	

	protected AddressModel parseContactInfo(Element contactElem, AddressModel address){
		if(contactElem != null){
			
			for(Element childElem : contactElem.getChildren()){
				for(Element dataElem : childElem.getChildren()){
					String dataText = dataElem.getText();
					switch(dataElem.getName()){
					case voiceTag:
						address.setContactPhone(dataText);
						break;
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
		}
		return address;
	}
}
