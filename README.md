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
│       └── target
|           ├── failsafe-reports
│           │   ├── TEST-com.pentaho.intergrationtestex.ResultsReader_IT.xml
│           │   └── failsafe-summary.xml
```

https://spring.io/guides/gs/rest-service/
