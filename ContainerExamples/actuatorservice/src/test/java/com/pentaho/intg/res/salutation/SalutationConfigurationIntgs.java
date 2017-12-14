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
package com.pentaho.intg.res.salutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;

public class SalutationConfigurationIntgs {

  @BeforeClass
  public static void setupUrls() {
    RestAssured.baseURI = "http://localhost:9000";
    RestAssured.basePath = "/";
  }

  @Test
  public void shouldReturn200WhenSendingRequestToController() {
    try {
      RestAssured.given().when()
        .get( "/health" )
        .then()
        .statusCode( 200 )
        .body( "status", Matchers.is( "UP" ) );
    } catch ( Exception e ) {
      Assert.fail( "Webapplication is down." );
    }
  }

  @Test
  public void dataCheck() {
    try {
      RestAssured.given().when()
        .get( "/greetings" )
        .then()
        .statusCode( 200 )
        .body( "payload", Matchers.is( "Guten Tag, Stranger!" ) );
    } catch ( Exception e ) {
      Assert.fail( "Webapplication is down." );
    }
  }
}
