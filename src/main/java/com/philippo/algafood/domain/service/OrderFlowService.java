package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.repository.RestaurantOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlowService {

    @Autowired
    private OrderIssuanceService orderIssuanceService;

    @Autowired
    private RestaurantOrderRepository restaurantOrderRepository;

    @Transactional
    public void confirm(String orderUuid){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderUuid);
        order.confirm();

        restaurantOrderRepository.save(order);
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
