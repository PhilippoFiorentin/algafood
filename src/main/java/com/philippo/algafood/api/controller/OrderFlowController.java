package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.openapi.controller.OrderFlowControllerOpenApi;
import com.philippo.algafood.domain.service.OrderFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/orders/{orderUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderFlowController implements OrderFlowControllerOpenApi {

    @Autowired
    private OrderFlowService orderFlowService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirm(@PathVariable String orderUuid){
        orderFlowService.confirm(orderUuid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deliver(@PathVariable String orderUuid){
        orderFlowService.deliver(orderUuid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancel(@PathVariable String orderUuid){
        orderFlowService.cancel(orderUuid);

        return ResponseEntity.noContent().build();
    }
}
