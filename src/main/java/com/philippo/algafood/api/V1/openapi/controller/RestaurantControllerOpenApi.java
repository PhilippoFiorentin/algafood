package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.BasicRestaurantModel;
import com.philippo.algafood.api.V1.model.RestaurantJustNameModel;
import com.philippo.algafood.api.V1.model.RestaurantModel;
import com.philippo.algafood.api.V1.model.input.RestaurantInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface RestaurantControllerOpenApi {

    CollectionModel<BasicRestaurantModel> list();

    CollectionModel<RestaurantJustNameModel> listJustNames();

    RestaurantModel find(Long restaurantId);

    RestaurantModel add(RestaurantInput restaurantInput);

    RestaurantModel update(Long restaurantId, RestaurantInput restaurantInput);

    ResponseEntity<Void> activate(Long restaurantId);

    ResponseEntity<Void> deactivate(Long restaurantId);

    ResponseEntity<Void> open(Long restaurantId);

    ResponseEntity<Void> close(Long restaurantId);
}
