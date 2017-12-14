package com.pentaho.intergrationtestex;
import com.pentaho.intergrationestex.CountryOrderResultItem;
import com.pentaho.intergrationestex.ResultsReader;
import com.pentaho.intergrationestex.TypeExecutionResultItem;
import com.pentaho.intergrationestex.TypeOrderNumberSummationItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Credit: http://www.baeldung.com/run-shell-command-in-java
class StreamGobbler implements Runnable {
  private InputStream inputStream;
  private Consumer<String> consumer;

  public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
    this.inputStream = inputStream;
    this.consumer = consumer;
  }

  @Override
  public void run() {
    new BufferedReader(new InputStreamReader(inputStream)).lines()
      .forEach(consumer);
  }
}

@RunWith( JUnitPlatform.class )
public class ResultsReader_IT {
  private static ResultsReader rr = null;

  // TODO: Explain
  @BeforeAll
  static public void runTransform() throws IOException, InterruptedException {
    // Get the path to spoon from PEHATAHO_HOME enviroment variable.
    String pdidirstr = System.getenv("PENTAHO_HOME");

    if (pdidirstr == null) {
      System.err.println("PENTAHO_HOME is not set, please set to PDI location of pan.");
      assert pdidirstr != null;
    }
    System.out.println("Running pan from " + pdidirstr);

    File pdidir = new File(pdidirstr);

    // Get the Transform under Test from the pom file
    String transformUnderTest = System.getProperty( "transformUnderTest" );

    // Get the tester transfrom
    String transformTester = System.getProperty( "transformTester" );

    // Get the input data dir
    String inputDataDir = System.getProperty( "inputDataDir" );

    // Get the output dir
    String outputDir = System.getProperty( "outputDir" );

    // Get Test file
    String testFile = System.getProperty( "testFile" );

    // Setup and run pan to exectute the Tester Transform
    String cmd[] = new String[7];
    cmd[0] = "./pan.sh";
    cmd[1] = "-file";
    cmd[2] = transformTester;
    cmd[3] = "-param:inputDir=" + inputDataDir;
    cmd[4] = "-param:outputDir=" + outputDir;
    cmd[5] = "-param:testFile=" + testFile;
    cmd[6] = "-param:transformUnderTest=" + transformUnderTest;
    Process p = Runtime.getRuntime().exec( cmd, null, pdidir);
    StreamGobbler streamGobbler = new StreamGobbler( p.getInputStream(), System.out::println );
    Executors.newSingleThreadExecutor().submit( streamGobbler );
    int exitCode = p.waitFor();
    assert exitCode == 0;

    // Get json Files
    File[] files = pdidir.listFiles(new FilenameFilter() {
                                        public boolean accept( File dir, String nameFilter) {
                                           return nameFilter.startsWith("PentahoOrderSummation_IT_ExecutionResults");
                                        }});
    String executionResultFile  = files[files.length - 1].getAbsolutePath();

    files = pdidir.listFiles(new FilenameFilter() {
      public boolean accept( File dir, String nameFilter) {
        return nameFilter.startsWith("PentahoCountryOrderSummation_IT_Results");
      }});
    String countryOrderSummationFile  = files[files.length - 1].getAbsolutePath();

    files = pdidir.listFiles(new FilenameFilter() {
      public boolean accept( File dir, String nameFilter) {
        return nameFilter.startsWith("PentahoOrderSummation_IT_OrderNumberResults");
      }});
    String orderNumberSummationFile  = files[files.length - 1].getAbsolutePath();
    ResultsReader_IT.rr = new ResultsReader(executionResultFile, countryOrderSummationFile,orderNumberSummationFile);
  }

  @Test
  public void readExecutionResults() {
    try {
      TypeExecutionResultItem res = rr.readExectionResult();
      if ( res == null ) {
        Assert.fail( "Missing Execution Results" );
      }
     else {
       assertEquals("IN", res.getTimeInRange());
     }
    }
    catch (IOException io) {
      Assert.fail( io.getMessage() );
    }
  }

  @TestFactory
  public Collection<DynamicTest> countrySumTests() {
    ArrayList<DynamicTest> dtests = new ArrayList<DynamicTest>();
    try {
      ArrayList<CountryOrderResultItem> results = rr.readCountrySumResults();
      if ( results == null ) {
        Assert.fail( "Missing Execution Results" );
      }
      // TODO: Explain
      for (CountryOrderResultItem res : results) {
        String testName = "Test: " + res.getTestFileName() + "|" + res.getSheetName() + ":" + res.getTestRowNumber();
        String diffValue = res.getDifference();
        DynamicTest dtest = DynamicTest.dynamicTest( testName, () ->
          assertEquals( "identical", diffValue) );
        dtests.add( dtest );
      }
      return dtests;
    }
    catch (IOException io) {
      Assert.fail( io.getMessage() );
    }
    return dtests;
  }

  @TestFactory
  public Collection<DynamicTest> orderNumberSumTests() {
    ArrayList<DynamicTest> dtests = new ArrayList<DynamicTest>();
    try {
      ArrayList<TypeOrderNumberSummationItem> results = rr.readOrderNumberSummation();
      if ( results == null ) {
        Assert.fail( "Missing Execution Results" );
      }
      // TODO: Explain
      for (TypeOrderNumberSummationItem res : results) {
        String testName = "Test: " + res.getTestFileName() + "|" + res.getSheetName() + ":" + res.getTestRowNumber();
        String diffValue = res.getDifference();
        DynamicTest dtest = DynamicTest.dynamicTest( testName, () ->
          assertEquals( "identical", diffValue) );
        dtests.add( dtest );
      }
      return dtests;
    }
    catch (IOException io) {
      Assert.fail( io.getMessage() );
    }
    return dtests;
  }

}
