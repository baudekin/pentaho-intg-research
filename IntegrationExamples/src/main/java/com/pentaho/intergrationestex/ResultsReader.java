package com.pentaho.intergrationestex;

import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;


public class ResultsReader {
  private String executionResultFile;
  private String countryOrderSummationFile;
  private String orderNumberSummationFile;

  public ResultsReader(String executionResultFile, String countryOrderSummationFile, String orderNumberSummationFile) {
    this.executionResultFile = executionResultFile;
    this.countryOrderSummationFile = countryOrderSummationFile;
    this.orderNumberSummationFile = orderNumberSummationFile;
  }

  public TypeExecutionResultItem readExectionResult() throws IOException {
    FileReader fr = new FileReader(  this.executionResultFile );
    Gson gson = new Gson();

    // Get Results
    TypeExectuionResultsDTO result = gson.fromJson( fr, TypeExectuionResultsDTO.class );
    return result.results.get(0);
  }

  public ArrayList<CountryOrderResultItem> readCountrySumResults() throws IOException {
    FileReader fr = new FileReader( this.countryOrderSummationFile );
    Gson gson = new Gson();

    // Get Results
    CountryOrderSummationDTO result = gson.fromJson( fr, CountryOrderSummationDTO.class );
    return result.getTests();
  }

  public ArrayList<TypeOrderNumberSummationItem> readOrderNumberSummation() throws IOException {
    FileReader fr = new FileReader( this.orderNumberSummationFile );
    Gson gson = new Gson();

    // Get Results
    TypeOrderNumberSummationResultsDTO result = gson.fromJson( fr, TypeOrderNumberSummationResultsDTO.class );
    return result.getTests();
  }
}
