package com.philippo.algafood.domain.listener;

import com.philippo.algafood.domain.event.OrderConfirmedEvent;
import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClientConfirmedOrderNotificationListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    public void whenConfirmOrder(OrderConfirmedEvent event){
        RestaurantOrder order = event.getOrder();

        var message = EmailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order confirmed")
                .body("order-confirmed.html")
                .variable("order", order)
                .recipient(order.getClient().getEmail())
                .build();

        emailService.send(message);


    }
}
