package com.restassured.googleplacescripts;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.restassured.basesetup.BaseSetup;
import com.restassured.commonutils.Generics;
import static org.hamcrest.Matchers.*;

public class GoggleAddDeleteJsonLoad extends BaseSetup {
    Map<String, Object> obj;
    Generics generic = new Generics();
   
    @Test
    public void addPlace() throws Throwable {
       
        String s = given().queryParam("key", "qaclick123").body(generic.getPayLoad("googleplacejsonpayload/addplace")).when()
                .post(generic.getResource("Epic2", "AddPlace_Post")).then().statusCode(200).log().all().extract()
                .jsonPath().get("place_id");

        obj = new HashMap<>();
        obj.put("place_id", s);

       
    }
    @Test
    public void deletePlace () throws Throwable {
        given().queryParam("key", "qaclick123").body(obj).when().post(generic.getResource("Epic2", "DeletePlace_Post"))
        .then().assertThat().log().all().body("status", equalTo("OK")).and().statusCode(200);
        obj=null;
        generic = null;

    }

}
