# pentaho-intg-research
## Pentaho Automated Data Driven Test Research

The purpose of this set of projects it to explore the tools we
can use to automated the testing of kettle transforms. The long term 
goal of this research is to automate the testing of Adaptive Execution Layer (AEL) engines using 
kettle test transforms. 

## Project Structure
```
pentaho-intg-research
├── ContainerExamples
│   └── actuatorservice
|       |
|      ...
│       └── target
│           ├── failsafe-reports
│           │   ├── TEST-com.pentaho.intg.res.salutation.SalutationConfigurationIntgs.xml
│           │   ├── com.pentaho.intg.res.salutation.SalutationConfigurationIntgs.txt
│           │   └── failsafe-summary.xml
│           ├── surefire-reports
│           │   ├── TEST-com.pentaho.intg.res.salutation.SalutationConfigurationTests.xml
│           │   └── com.pentaho.intg.res.salutation.SalutationConfigurationTests.txt
├── IntegrationExamples
│   └── IntegrationTestEx
|       |
|      ...
│       └── target
|           ├── failsafe-reports
│           │   ├── TEST-com.pentaho.intergrationtestex.ResultsReader_IT.xml
│           │   └── failsafe-summary.xml
```

* ContainerExamples - Hosts all the examples related to managing docker from maven.
  * actuatorservice - Is a simple springboot application (https://spring.io/guides/gs/rest-service/) hosted on docker container. It is comprised of unit tests using surefire plugin and integration tests using failsafe plugin. 
  * To run all test execute:
```
  mvn verify
```
* IntegrationExamples - Hosts all Intergration test examples. 
  * IntegrationTextEx - Is a simple data driven integration test based of off excell spreadsheet (https://github.com/baudekin/pentaho-intg-research/blob/master/IntegrationExamples/IntegrationTestEx/src/main/data/PentahoOrderSummation_IT.xlsx) created from transform results from spoon. This tests the kettle transform OrderSummation (https://github.com/baudekin/pentaho-intg-research/blob/master/IntegrationExamples/IntegrationTestEx/src/main/kettle/OrderSummation.ktr) using OrderSummationTester (https://github.com/baudekin/pentaho-intg-research/blob/master/IntegrationExamples/IntegrationTestEx/src/main/kettle/OrderSummationTester.ktr) 
  * To run:
```
export PENTAHO_HOME=/Users/mbodkin/clients/pdi-ee-client-8.0.0.1-27/data-integration
mvn verify
```

## References
### Plugins
* surefire - Unit Test Framework http://maven.apache.org/surefire/maven-surefire-plugin/
* failsafe - Integration Test Framework http://maven.apache.org/surefire/maven-failsafe-plugin/
* junit-jupiter-engine - JUnit 5 which enables dyanmic testing http://junit.org/junit5/docs/current/user-guide/
* docker-maven-plugin - Manges docker containers https://github.com/fabric8io/docker-maven-plugin
* spring-boot-maven-plugin - Manages creating simple web based applications https://docs.spring.io/spring-boot/docs/current/maven-plugin/index.html
* gson - Googles json parser https://github.com/google/gson
