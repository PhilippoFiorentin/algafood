package com.philippo.algafood;

import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.util.DatabaseCleaner;
import com.philippo.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class KitchenRegisterApiT {

    @LocalServerPort
    private int port;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private KitchenRepository kitchenRepository;

    private Kitchen americanKitchen;
    private int quantityRegisteredKitchen;
    private static final int KITCHEN_ID_NO_EXISTENT = 100;

    private String jsonKitchens;

    @Before
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";

        jsonKitchens = ResourceUtils.getContentFromResource("/kitchens.json");

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void shouldReturnStatus200_WhenSearchKitchens(){
        given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void shouldHaveRegisteredKitchens_WhenSearchKitchens(){
        given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .body("", Matchers.hasSize(this.quantityRegisteredKitchen));

    }

    @Test
    public void shouldReturnStatusCode201_WhenRegisterKitchen(){
        given()
                .body(jsonKitchens)
                .contentType("application/json")
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnCorrectResponseAndStatus_WhenCheckExistingKitchen(){
        given()
            .pathParam("kitchenId", 2)
            .accept(ContentType.JSON)
        .when()
            .get("/{kitchenId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo("American"));
    }

    @Test
    public void shouldReturnStatus404_WhenCheckNoKitchen(){
        given()
                .pathParam("kitchenId", KITCHEN_ID_NO_EXISTENT)
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        Kitchen thaiKitchen = new Kitchen();
        thaiKitchen.setName("Thai");
        kitchenRepository.save(thaiKitchen);

        Kitchen americanKitchen = new Kitchen();
        americanKitchen.setName("American");
        kitchenRepository.save(americanKitchen);

        quantityRegisteredKitchen = (int) kitchenRepository.count();
    }
}
