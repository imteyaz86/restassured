package com.restassured.jiratestscripts;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.restassured.basesetup.BaseSetup;
import com.restassured.commonutils.Generics;

import io.restassured.http.ContentType;

public class CreateIsssue extends BaseSetup {
    Generics generic = new Generics();
    String sessionId;
    
    @Test(priority=0)
    public void createSession() throws Throwable {
        
        sessionId = given().contentType(ContentType.JSON).body(generic.getPayLoad("jirajsonpayload/createsession")).
        when().post(generic.getResource("Jira", "CreateSession_Post")).then().assertThat().statusCode(200).and().
        extract().jsonPath().get("session.value");
        
        System.out.println(sessionId);
    }
    @Test(priority=1)
    public void createIssueInJira() throws Throwable {
        
        
       String s = given().header("Cookie:","JSESSIONID="+sessionId).body(generic.getPayLoad("jirajsonpayload/createissue")).
        when().post(generic.getResource("Jira", "CreateIssue_Post")).then().
        extract().jsonPath().get("key");
       
       System.out.println("Issue is "+s);
        
        
        
        
        
        
        
        
    }

}
