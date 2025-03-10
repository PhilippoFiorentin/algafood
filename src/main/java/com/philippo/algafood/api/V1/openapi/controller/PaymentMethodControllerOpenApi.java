package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PaymentMethodModel;
import com.philippo.algafood.api.V1.model.input.PaymentMethodInput;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

public interface PaymentMethodControllerOpenApi {

    ResponseEntity<CollectionModel<PaymentMethodModel>> list(ServletWebRequest request);

    ResponseEntity<PaymentMethodModel> find(Long paymentMethodId, ServletWebRequest request);

    PaymentMethodModel add(PaymentMethodInput paymentMethodInput);

    PaymentMethodModel update(Long paymentMethodId, PaymentMethodInput paymentMethodInput);

    void delete(Long paymentMethodId);

}
