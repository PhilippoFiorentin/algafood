package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.model.OrderStatus;
import com.philippo.algafood.domain.model.RestaurantOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class OrderFlowService {

    @Autowired
    private OrderIssuanceService orderIssuanceService;

    @Transactional
    public void confirm(Long orderId){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderId);

        if(!order.getStatus().equals(OrderStatus.CREATED))
            throw new BusinessException(String.format(
                    "The order status %d cannot be changed from %s to %s",
                    order.getId(),
                    order.getStatus().getDescription(),
                    OrderStatus.CONFIRMED));

        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmationDate(OffsetDateTime.now());
    }

    @Transactional
    public void deliver(Long orderId){
        RestaurantOrder order = orderIssuanceService.findOrFail(orderId);

        if(!order.getStatus().equals(OrderStatus.CONFIRMED))
            throw new BusinessException(String.format(
                    "The order status %d cannot be changed from %s to %s",
                    order.getId(),
                    order.getStatus().getDescription(),
                    OrderStatus.DELIVERED));

        order.setStatus(OrderStatus.DELIVERED);
        order.setConfirmationDate(OffsetDateTime.now());
    }
}
