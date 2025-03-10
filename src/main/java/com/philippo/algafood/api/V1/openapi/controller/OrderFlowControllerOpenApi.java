package com.philippo.algafood.api.V1.openapi.controller;

import org.springframework.http.ResponseEntity;

public interface OrderFlowControllerOpenApi {

    ResponseEntity<Void> confirm(String orderUuid);

    ResponseEntity<Void> deliver(String orderUuid);

    ResponseEntity<Void> cancel(String orderUuid);
}
