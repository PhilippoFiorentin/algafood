package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.ProductModel;
import com.philippo.algafood.api.V1.model.input.ProductInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface RestaurantProductControllerOpenApi {

    CollectionModel<ProductModel> list(Long restaurantId, Boolean addInactives);

    ProductModel find(Long restaurantId, Long productId);

    ProductModel add(Long restaurantId, ProductInput productInput);

    ProductModel update(Long restaurantId, Long productId, ProductInput productInput);
}
