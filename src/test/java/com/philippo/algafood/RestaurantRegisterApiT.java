package com.philippo.algafood;

import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.repository.RestaurantRepository;
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
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestaurantRegisterApiT {

    @LocalServerPort
    private int port;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    KitchenRepository kitchenRepository;

    private Restaurant frenchRestaurant;
    private Kitchen FrenchKitchen;
    private String jsonRestaurants;
    private String jsonRestaurantsNoKitchen;
    private String jsonRestaurantsNoDeliveryFee;
    private String jsonRestaurantsInvalidKitchenId;
    private int quantityRegisteredRestaurant;
    private static final int RESTAURANT_ID_NO_EXISTENT = 100;
    private static final String INVALID_DATA_TITLE_PROBLEM = "Invalid data";
    private static final String BUSINESS_RULE_TYPE_VIOLATION = "Business rule violation";

    @Before
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurants";

        jsonRestaurants = ResourceUtils.getContentFromResource("/restaurants/restaurants.json");
        jsonRestaurantsNoKitchen = ResourceUtils.getContentFromResource("/restaurants/restaurants-no-kitchen.json");
        jsonRestaurantsNoDeliveryFee = ResourceUtils.getContentFromResource("/restaurants/restaurants-no-delivery-fee.json");
        jsonRestaurantsInvalidKitchenId = ResourceUtils.getContentFromResource("/restaurants/restaurants-invalid-kitchen-id.json");

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void shouldReturnStatus200_WhenSearchRestaurants(){
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldHaveRegisteredKitchens_WhenSearchRestaurants(){
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", Matchers.hasSize(this.quantityRegisteredRestaurant));

    }

    @Test
    public void shouldReturnStatusCode201_WhenRegisterRestaurant(){
        given()
            .body(jsonRestaurants)
            .contentType("application/json")
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnCorrectResponseAndStatus_WhenCheckExistingRestaurant(){
        given()
            .pathParam("restaurantId", 2)
            .accept(ContentType.JSON)
        .when()
            .get("/{restaurantId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo("French Restaurant"));
    }

    @Test
    public void shouldReturnStatusCode400_WhenRegisterRestaurantNoKitchen(){
        given()
            .body(jsonRestaurantsNoKitchen)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(INVALID_DATA_TITLE_PROBLEM));
    }

    @Test
    public void shouldReturnStatusCode400_WhenRegisterRestaurantNoDeliveryFee(){
        given()
            .body(jsonRestaurantsNoDeliveryFee)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(INVALID_DATA_TITLE_PROBLEM));
    }

    @Test
    public void shouldReturnStatusCode400_WhenRegisterRestaurantInvalidKitchenId(){
        given()
            .body(jsonRestaurantsInvalidKitchenId)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(BUSINESS_RULE_TYPE_VIOLATION));
    }

    @Test
    public void shouldReturnStatus404_WhenCheckNoRestaurant(){
        given()
                .pathParam("restaurantId", RESTAURANT_ID_NO_EXISTENT)
                .accept(ContentType.JSON)
                .when()
                .get("/{restaurantId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        Restaurant r = new Restaurant();
        Kitchen thaiKitchen = new Kitchen();

        thaiKitchen.setName("Thai");
        kitchenRepository.save(thaiKitchen);

        r.setName("Thai Restaurant");
        r.setDeliveryFee(BigDecimal.valueOf(10.00));
        r.setKitchen(thaiKitchen);
        restaurantRepository.save(r);

        Restaurant frenchRestaurant = new Restaurant();
        Kitchen FrenchKitchen = new Kitchen();

        FrenchKitchen.setName("French");
        kitchenRepository.save(FrenchKitchen);

        frenchRestaurant.setName("French Restaurant");
        frenchRestaurant.setDeliveryFee(BigDecimal.valueOf(5.00));
        frenchRestaurant.setKitchen(FrenchKitchen);
        restaurantRepository.save(frenchRestaurant);

        quantityRegisteredRestaurant = (int) restaurantRepository.count();
    }
}
