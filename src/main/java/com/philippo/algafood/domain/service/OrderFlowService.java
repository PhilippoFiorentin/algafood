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
                    "Order status %d cannot be changed from %s to %s",
                    order.getId(),
                    order.getStatus().getDescription(),
                    OrderStatus.CONFIRMED));

        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmationDate(OffsetDateTime.now());
    }
}
