package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.UserModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface RestaurantResponsibleUserControllerOpenApi {

    CollectionModel<UserModel> list(Long restaurantId);

    ResponseEntity<Void> disaffiliate(Long restaurantId, Long userId);

    ResponseEntity<Void> affiliate(Long restaurantId, Long userId);
}
