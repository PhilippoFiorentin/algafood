package com.philippo.algafood.api.controller;

import com.philippo.algafood.domain.service.OrderFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/{orderId}")
public class OrderFlowController {

    @Autowired
    private OrderFlowService orderFlowService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable Long orderId){
        orderFlowService.confirm(orderId);
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable Long orderId){
        orderFlowService.deliver(orderId);
    }

    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long orderId){
        orderFlowService.cancel(orderId);
    }
}
