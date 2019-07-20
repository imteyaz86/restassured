package com.restassured.googleplacescripts;

import org.testng.annotations.Test;
import com.restassured.basesetup.BaseSetup;
import com.restassured.commonutils.Generics;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetPlace extends BaseSetup {
    
    @Test
    public void getPlace()throws  Throwable {
        Generics generic = new Generics();
        
        String s = given().
        params("location","-33.8670522,151.1957362","radius","500","key","AIzaSyB2JfAXGF2GJptqyPGDrG_MvGAYzw7tRkg").
        when().
        get(generic.getResource("Epic1", "SearcPlace_Get")).
        then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
        body("results[0].name",equalTo("Sydney")).and().
        body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
        header("Server","scaffolding on HTTPServer2").and().
        
        extract().asString();
       System.out.println(s);
       
        
    }

}
