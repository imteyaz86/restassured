package com.restassured.googleplacescripts;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.restassured.basesetup.BaseSetup;
import com.restassured.commonutils.Generics;
import static org.hamcrest.Matchers.*;

public class GoggleAddDelete extends BaseSetup {

    @Test
    public void addPlaceAtGoggle() throws Throwable {
        Generics generic = new Generics();
        String s = given().queryParam("key", "qaclick123").body(generic.getPayLoad("Epic2", "AddPlace_Post")).when()
                .post(generic.getResource("Epic2", "AddPlace_Post")).then().statusCode(200).log().all().extract()
                .jsonPath().get("place_id");

        Map<String, Object> obj = new HashMap<>();
        obj.put("place_id", s);

        given().queryParam("key", "qaclick123").body(obj).when().post(generic.getResource("Epic2", "DeletePlace_Post"))
                .then().assertThat().log().all().body("status", equalTo("OK")).and().statusCode(200);

    }
    
    

}
