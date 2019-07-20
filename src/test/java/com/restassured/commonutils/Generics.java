package com.restassured.commonutils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import com.restassured.basesetup.BaseSetup;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Generics extends BaseSetup {

    final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
    public String getResource(String sheetName, String resourceName) throws Throwable {
        try {
            return resourceExcel.excelStringFetchDataUtility(sheetName, resourceName, 1);
        } catch (Throwable t) {
            throw t;
        }
    }

    public String getPayLoad(String sheetName, String resourceName) throws Throwable {
        try {
            return payLoadExcel.excelStringFetchDataUtility(sheetName, resourceName, 1);
        } catch (Throwable t) {
            throw t;
        }
    }

    public JsonPath getJsonPath(Response response) throws Throwable {
       String respString;
        try {
            respString = response.asString();
            JsonPath json = new JsonPath(respString);
            return json;
        } catch (Throwable t) {
            throw t;
        }
    }
    
    public XmlPath getXmlPath(Response response) throws Throwable {
        String respString;
         try {
             respString = response.asString();
             XmlPath xml = new XmlPath(respString);
             return xml;
         } catch (Throwable t) {
             throw t;
         }
     }

    public String generateResourceFromFiles(String path) throws Throwable {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Throwable t) {
            throw t;
        }
    }

    public String getPayLoad(String path) throws Throwable {
        String completePath;
        String modifiedPath = "";
        String[] arrayPath;
        int loop;
        try {
            arrayPath = path.split("/");
            for (loop = 0; loop < arrayPath.length; loop++) {
                modifiedPath = modifiedPath + pathSeparator + arrayPath[loop];
            }
            completePath = System.getProperty("user.dir") + pathSeparator + "src" + pathSeparator + "test"
                    + pathSeparator + "java" + pathSeparator + "com" + pathSeparator + "restassured" + modifiedPath
                    + ".json";
            return generateResourceFromFiles(completePath);

        } catch (Throwable t) {
            throw t;
        }
    }
    
    public String generateRandomString(int stringLength) {

        StringBuffer randStr = new StringBuffer();
        try {
            for (int i = 0; i < stringLength; i++) {

                int number = getRandomNumber();

                char ch = CHAR_LIST.charAt(number);

                randStr.append(ch);

            }
        } catch (Exception | Error e)

        {
            throw e;
        }
        return randStr.toString();

    }

    private int getRandomNumber() {
        int randomInt = 0;
        try {
            Random randomGenerator = new Random();
            randomInt = randomGenerator.nextInt(CHAR_LIST.length());

            if (randomInt - 1 == -1) {
                return randomInt;
            } else {
                return randomInt - 1;
            }
        } catch (Exception | Error e)
        {
            throw e;
        }
    }
    
    
    public int generateRandomInteger(int num) throws Exception, Error {
        int randomNum = 1;
        int temp = 1;
        try {
            Random rand = new Random();
            for (int i = 1; i <= num; i++) {
                temp = temp * 10;

            }
            randomNum = rand.nextInt(temp);

        } catch (Exception | Error e) {
            throw e;
        }
        return randomNum;
    }

    public String updateJson(String json, String key) throws Throwable {
        int start,middle,last;
        String subString, randomString, updatedJsonString;
        try {
            
          start =  json.indexOf(key);
          middle = json.indexOf(":", start);
          last = json.indexOf(",", middle);
          subString =  json.substring(middle+1, last);
          randomString = generateRandomString(4);
         // System.out.println("SubString is "+subString);
          updatedJsonString = json.substring(0, middle+1)+"\""+randomString+"\""+json.substring(last);
         //  System.out.println("Final String is" +  updatedJsonString);
          return updatedJsonString; 
            
        }catch (Throwable t) {
            throw t;
        }
    }

    
    public String getJsonValue(String json, String key) throws Throwable {
        int start,middle,last;
        String subString;
        
        try {
            
          start =  json.indexOf(key);
          middle = json.indexOf(":", start);
          last = json.indexOf(",", middle);
          subString =  json.substring(middle+2, last-1);
          //System.out.println("SubString is "+subString);
          return subString; 
            
        }catch (Throwable t) {
            throw t;
        }
    }

}
