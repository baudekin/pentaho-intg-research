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
 * If it ran succesfully you should see:
 ```
 ERROR] Tests run: 72, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 18.089 s <<< FAILURE! - in com.pentaho.intergrationtestex.ResultsReader_IT
[ERROR] Test: /Users/mbodkin/repos/pentaho-intg-research/IntegrationExamples/IntegrationTestEx/src/main/data/PentahoOrderSummation_IT.xlsx|RowsAfterExecution:14(countrySumTests())  Time elapsed: 0.005 s  <<< FAILURE!
org.junit.ComparisonFailure: expected:<[identical]> but was:<[changed]>

[ERROR] Test: /Users/mbodkin/repos/pentaho-intg-research/IntegrationExamples/IntegrationTestEx/src/main/data/PentahoOrderSummation_IT.xlsx|RowsAfterExecution:17(countrySumTests())  Time elapsed: 0 s  <<< FAILURE!
org.junit.ComparisonFailure: expected:<[identical]> but was:<[deleted]>

[INFO]
[INFO] Results:
[INFO]
[ERROR] Failures:
[ERROR]   expected:<[identical]> but was:<[changed]>
[ERROR]   expected:<[identical]> but was:<[deleted]>
[INFO]
[ERROR] Tests run: 72, Failures: 2, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-failsafe-plugin:2.20.1:verify (integration-test) @ IntegrationTestEx ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 22.024 s
[INFO] Finished at: 2017-12-18T08:05:29-05:00
[INFO] Final Memory: 10M/169M
[INFO] ------------------------------------------------------------------------
 ```
## References
### Plugins
* surefire - Unit Test Framework http://maven.apache.org/surefire/maven-surefire-plugin/
* failsafe - Integration Test Framework http://maven.apache.org/surefire/maven-failsafe-plugin/
* junit-jupiter-engine - JUnit 5 which enables dyanmic testing http://junit.org/junit5/docs/current/user-guide/
* docker-maven-plugin - Manges docker containers https://github.com/fabric8io/docker-maven-plugin
* spring-boot-maven-plugin - Manages creating simple web based applications https://docs.spring.io/spring-boot/docs/current/maven-plugin/index.html
* gson - Googles json parser https://github.com/google/gson
