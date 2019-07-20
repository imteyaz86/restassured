package com.restassured.basesetup;

import java.io.File;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.restassured.commonutils.ExcelLib;
import com.restassured.commonutils.PropertyReader;

import io.restassured.RestAssured;

/**
 * Purpose : This is a base class in which before suite and after suite
 * configuration is defined
 * 
 * @author iahmad
 *
 */

public class BaseSetup {
    public static ExcelLib testDataExcel;
    public static ExcelLib resourceExcel;
    public static ExcelLib payLoadExcel;
    public static String pathSeparator;
    public static PropertyReader properties;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Throwable {
        try {
           // updatePropertyFile();
            pathSeparator = File.separator;
            
            testDataExcel = new ExcelLib(
                    System.getProperty("user.dir") + pathSeparator+"src"+pathSeparator+"test"+pathSeparator+"resources"+pathSeparator+"data"+pathSeparator+"testData"+pathSeparator+"TestData.xlsx");
            resourceExcel = new ExcelLib(
                    System.getProperty("user.dir") + pathSeparator+"src"+pathSeparator+"test"+pathSeparator+"resources"+pathSeparator+"data"+pathSeparator+"testData"+pathSeparator+"Resource.xlsx");            
            payLoadExcel = new ExcelLib(
                    System.getProperty("user.dir") + pathSeparator+"src"+pathSeparator+"test"+pathSeparator+"resources"+pathSeparator+"data"+pathSeparator+"testData"+pathSeparator+"Payload.xlsx");
            
            properties = new PropertyReader("utilities.properties");
            System.out.println("Url is "+properties.getPropertyValue("BaseURL"));
            RestAssured.baseURI= properties.getPropertyValue("BaseURL");
            //RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            //RestAssured.config = RestAssured.config().connectionConfig(connectionConfig().closeIdleConnectionsAfterEachResponse());

        } catch (Throwable e) {
            System.out.println("Error Occurred In Before Suite Configuration Is :" + e.toString());

        }
    }

    @AfterSuite(alwaysRun = true)
    public void exitSetUp() throws Throwable {
        try {
            testDataExcel = null;
            resourceExcel = null;
            payLoadExcel = null;
            
        } catch (Throwable e) {
            System.out.println("Error Occurred In After Suite Configuration Is :" + e.toString());
        }

    }

    /**
     * update Property File
     */
    private void updatePropertyFile() throws Throwable {
       /* PropertiesConfiguration config = new PropertiesConfiguration(
                BaseSetup.class.getClassLoader().getResource("utilities.properties"));
        config.setProperty("Execution", "Local");
        config.save();*/
    }

    /**
     * Read Environment Variable From CI Tool
     */
   /* private void readEnvVariablesFromCiTool() throws Throwable {

    }*/
}