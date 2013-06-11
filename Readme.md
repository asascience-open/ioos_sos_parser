# IOOS SWE SOS Parser - A XML Parser library for Sensor Web Enablement (SWE) Common Data Model (CDM)

*Note: OGC Sensor Web Enablement (SWE) Common Data Model (CDM) Encoding Standard defines low level data models for exchanging sensor related data between nodes of the OGC SWE framework. These models allow applications and/or servers to structure, encode and transmit sensor datasets in a self describing and semantically enabled way. 

This parsing library is for IOOS SWE 1.0.*

this parser parse the following data services into the data model:

* OM Observations - IOOS SWE SOS 1.0
* SOS Capabilities - IOOS SWE SOS 1.0
* SWE Multi Station TimeSeries - IOOS SWE SOS 1.0
* SWE Single Station TimeSeries - IOOS SWE SOS 1.0
* SWE Single Station Single Property - IOOS SWE SOS 1.0
* SML Sensor (Network) - IOOS SWE SOS 1.0
* SML Sensor (Station) - IOOS SWE SOS 1.0

## Common Interface

### Create parser Service

```java 
GetObservationParser gop = new GetObservationParser();
GetCapabilitiesParser gc = new GetCapabilitiesParser();
DescribeSensorStationParser dss = new DescribeSensorStationParser();
```
### Obtain Data Model

GetObservation getObsModel = gop.parseGO(gopFile);
...

## Maven Repo

Maven repository link:

```xml
<repositories>
    <repository>
      <id>asa-sonatype-nexus-snapshots</id>
      <name>ASA Nexus SNAPSHOT Repo</name>
      <url>http://geo.asascience.com/nexus/content/repositories/snapshots/</url>
    </repository>
</repositories>
```

place this dependency into pom.xml file:

```xml
<dependency>
  <groupId>com.asascience</groupId>
  <artifactId>ncsos</artifactId>
  <version>RC1-SNAPSHOT</version>
</dependency>
```

## Roadmap
* Development of a standardized Metadata concept, possibly through SensorML and/or ISO 19115-2


## Contributors
* Cheryl Morse CMorse<@asascience.com>
