/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2002 - 2017, Hitachi Vantara
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */
package com.pentaho.intergrationestex;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ResultsReader {
  private String executionResultFile;
  private String countryOrderSummationFile;
  private String orderNumberSummationFile;

  public ResultsReader( String executionResultFile, String countryOrderSummationFile,
                        String orderNumberSummationFile ) {
    this.executionResultFile = executionResultFile;
    this.countryOrderSummationFile = countryOrderSummationFile;
    this.orderNumberSummationFile = orderNumberSummationFile;
  }

  public TypeExecutionResultItem readExectionResult() throws IOException {
    FileReader fr = new FileReader( this.executionResultFile );
    Gson gson = new Gson();

    // Get Results
    TypeExectuionResultsDTO result = gson.fromJson( fr, TypeExectuionResultsDTO.class );
    return result.results.get( 0 );
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
