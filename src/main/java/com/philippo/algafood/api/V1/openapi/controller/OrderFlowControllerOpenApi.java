package com.philippo.algafood.api.V1.openapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface OrderFlowControllerOpenApi {

    ResponseEntity<Void> confirm(String orderUuid);

    ResponseEntity<Void> deliver(String orderUuid);

    ResponseEntity<Void> cancel(String orderUuid);
}
