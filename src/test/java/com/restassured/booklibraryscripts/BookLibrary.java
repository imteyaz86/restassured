package com.restassured.booklibraryscripts;

import org.testng.annotations.Test;
import com.restassured.basesetup.BaseSetup;
import com.restassured.commonutils.Generics;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class BookLibrary extends BaseSetup {
    Generics generic = new Generics();
    Map<String, Object> obj;
    String id, json;

    @Test(priority = 0,description ="Add A Book", groups= "BookLibrary")
    public void addBook() throws Throwable {
        String payLoad;

        payLoad = generic.getPayLoad("booklibraryjsonpayload/addbook");
        payLoad = generic.updateJson(payLoad, "isbn");
        json= generic.getJsonValue(payLoad, "isbn");
        id = given().contentType(ContentType.JSON).body(payLoad).when()
                .post(generic.getResource("BookLibrary", "AddBook_Post")).then().assertThat().statusCode(200).and()
                .contentType(ContentType.JSON).and().body("Msg", equalTo("successfully added")).and().extract()
                .jsonPath().get("ID");
        obj = new HashMap<>();
        obj.put("ID", id);

    }

    @Test(priority = 1, description = "Find a book with id",groups= "BookLibrary")
    public void findBookWithId() throws Throwable {
        given().contentType(ContentType.JSON).param("ID", id).when()
                .get(generic.getResource("BookLibrary", "RetrieveBook_Get")).then().contentType(ContentType.JSON)
                .assertThat().statusCode(200).and().body("isbn", equalTo(json));

    }

    @Test(priority = 2, description = "Delete a book with passing id",groups= "BookLibrary1")
    public void deleteBook() throws Throwable {
        given().contentType(ContentType.JSON).body(obj).when()
                .post(generic.getResource("BookLibrary", "DeleteBook_Post")).then().contentType(ContentType.JSON)
                .assertThat().statusCode(200).and().body("msg", equalTo("book is successfully deleted"));
    }

}
