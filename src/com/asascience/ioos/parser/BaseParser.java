package com.asascience.ioos.parser;

import org.jdom2.Element;
import org.jdom2.Namespace;

public class BaseParser {
	private final String omNsTag = "om";
	private final String gmlNsTag = "gml";
	private final String sweNsTag = "swe";
	private final String swe2NsTag = "swe2";
	private final String xlinkNsTag = "xlink";
	private final String sosNsTag = "sos";
	private final String owsNsTag = "ows";
	private final String xsiNsTag = "xsi";
	protected final String xlinkAttributeHrefTag = "href";
	protected final String gmlMetaDataTag = "metaDataProperty";
	protected final String gmlVersionTag = "version";
	protected final String xlinkAttributeTitleTag = "title";
	protected final String gmlDescriptionTag = "description";
	protected final String gmlNameTag = "name";
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
	protected Namespace omNs;
	protected Namespace gmlNs;
	protected Namespace sweNs;
	protected Namespace swe2Ns;
	protected Namespace xlinkNs;
	protected Namespace sosNs;
	protected Namespace owsNs;
	protected Namespace xsiNs;
	
	// initialize the getObservation namespaces
	protected void initGoNamespaces(Element root){
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
			}
		}
		
	}
}
