package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PaymentMethodModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestaurantPaymentMethodControllerOpenApi {

    CollectionModel<PaymentMethodModel> list(Long restaurantId);

    ResponseEntity<Void> disaffiliate(Long restaurantId, Long paymentMethodId);

    ResponseEntity<Void> affiliate(Long restaurantId, Long paymentMethodId);

}
