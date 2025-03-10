package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.UserModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestaurantResponsibleUserControllerOpenApi {

    CollectionModel<UserModel> list(Long restaurantId);

    ResponseEntity<Void> disaffiliate(Long restaurantId, Long userId);

    ResponseEntity<Void> affiliate(Long restaurantId, Long userId);
}
