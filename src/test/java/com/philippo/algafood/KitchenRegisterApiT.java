package com.philippo.algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KitchenRegisterApiT {

    @LocalServerPort
    private int port;

    @Before
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";
    }

    @Test
    public void shouldReturnStatus200_WhenGetKitchens(){
        given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void shouldHave4Kitchens_WhenSearchKitchens(){
        given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .body("", Matchers.hasSize(4))
                    .body("name", Matchers.hasItems("Indiana", "Tailandesa"));

    }

    @Test
    public void shouldReturnStatusCode201_WhenRegisterKitchen(){
        given()
                .body("{ \"name\": \"Chinese\" }")
                .contentType("application/json")
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
