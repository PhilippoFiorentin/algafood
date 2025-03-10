package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PaymentMethodModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface RestaurantPaymentMethodControllerOpenApi {

    CollectionModel<PaymentMethodModel> list(Long restaurantId);

    ResponseEntity<Void> disaffiliate(Long restaurantId, Long paymentMethodId);

    ResponseEntity<Void> affiliate(Long restaurantId, Long paymentMethodId);

}
