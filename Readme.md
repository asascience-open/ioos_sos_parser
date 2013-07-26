# IOOS SWE SOS Parsing Library 
===================

A XML Parser library for Sensor Web Enablement (SWE) Common Data Model (CDM)
--------------------

<em>Note: OGC Sensor Web Enablement (SWE) Common Data Model (CDM) Encoding Standard defines low level data models for exchanging sensor related data between nodes of the OGC SWE framework. These models allow applications and/or servers to structure, encode and transmit sensor datasets in a self describing and semantically enabled way. 

This parsing library is for IOOS SWE 1.0.</em>

this parser parse the following data services into the data model:

* OM Observations - IOOS SWE SOS 1.0
* SOS Capabilities - IOOS SWE SOS 1.0
* SWE Multi Station TimeSeries - IOOS SWE SOS 1.0
* SWE Single Station TimeSeries - IOOS SWE SOS 1.0
* SWE Single Station Single Property - IOOS SWE SOS 1.0
* SML Sensor (Network) - IOOS SWE SOS 1.0
* SML Sensor (Station) - IOOS SWE SOS 1.0

## Common Interface

### Create Parser Service
The ioos_sos_parser can be used to parse getCapabilities, DescribeSensor and GetObservation requests. The
parser can be instantiated with the following calls:
```java 
GetObservationParser gop = new GetObservationParser();
GetCapabilitiesParser gc = new GetCapabilitiesParser();
DescribeSensorStationParser dss = new DescribeSensorStationParser();
```
### Obtain Data Model
Once the parser is instantiated the data model can be obtained by calling one of the following functions:
```java
GetObservation getObsModel = gop.parseGO(gopFile);
DescribeSensorStation describeSensorModel = dss.parseDescribeStation(describeSensStatFile);
GetCapabilities getCapsModel = gc.parseGetCapabilities(getCapFile);
```
The parsing functions can accept either the path to a file that contains the XML to parse or a URL.

### Create Netcdf File
Netcdf files can be created from the GetObservation data model. One Netcdf file will be created for each different station in the GetObservation data model. 
The following function call will create the Netcdf files in the specified output directory and will return a list of Netcdfile objects.
```java
 CreateNetcdf ncCreate  = new CreateNetcdf(getObsModel);
 List<NetcdfFile> ncList =  ncCreate.generateNetcdf(outputDirectory);
```

## Maven Repo

To use this library, add the following Maven repository to your pom.xml:

```xml
<repositories>
    <repository>
      <id>asa-sonatype-nexus-snapshots</id>
      <name>ASA Nexus SNAPSHOT Repo</name>
      <url>http://geo.asascience.com/nexus/content/repositories/snapshots/</url>
    </repository>
</repositories>
```

And place this dependency into you pom.xml:

```xml
<dependency>
  <groupId>com.asascience</groupId>
  <artifactId>ioos_sos_parser</artifactId>
  <version>RC1-SNAPSHOT</version>
</dependency>
```

## Roadmap
*  Full support for the IOOS SWE 1.0 templates


## Contributors
*  Cheryl Morse <CMorse@asascience.com>
