package com.philippo.algafood.domain.listener;

import com.philippo.algafood.domain.event.OrderCancelledEvent;
import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ClientCancelledOrderNotificationListener {

    @Autowired
    private EmailService emailService;

    @TransactionalEventListener
    public void whenCancelOrder(OrderCancelledEvent event){
        RestaurantOrder order = event.getOrder();

        var message = EmailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order cancelled")
                .body("order-cancelled.html")
                .variable("order", order)
                .recipient(order.getClient().getEmail())
                .build();

        emailService.send(message);
    }
}
