package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.RestaurantOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlowService {

    @Autowired
    private OrderIssuanceService orderIssuanceService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void confirm(String orderUuid){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderUuid);
        order.confirm();

        var message = EmailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order confirmed")
                .body("The order with code <strong>" + order.getUuid() + "</strong> was confirmed!")
                .recipient(order.getClient().getEmail())
                .build();

        emailService.send(message);
    }

    @Transactional
    public void deliver(String orderUuid){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderUuid);
        order.deliver();
    }

    @Transactional
    public void cancel(String orderUuid){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderUuid);
        order.cancel();
    }
}
