package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.RestaurantOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlowService {

    @Autowired
    private OrderIssuanceService orderIssuanceService;

    @Transactional
    public void confirm(Long orderId){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderId);
        order.confirm();
    }

    @Transactional
    public void deliver(Long orderId){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderId);
        order.deliver();
    }

    @Transactional
    public void cancel(Long orderId){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderId);
        order.cancel();
    }
}
