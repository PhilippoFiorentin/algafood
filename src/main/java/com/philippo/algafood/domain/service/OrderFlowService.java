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
    public void confirm(String orderUuid){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderUuid);
        order.confirm();
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
