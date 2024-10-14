package com.philippo.algafood.domain.service;

import com.philippo.algafood.api.assembler.OrderModelAssembler;
import com.philippo.algafood.domain.exception.OrderNotFoundException;
import com.philippo.algafood.domain.exception.PaymentMethodNotFoundException;
import com.philippo.algafood.domain.model.Order;
import com.philippo.algafood.domain.model.PaymentMethod;
import com.philippo.algafood.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderIssuanceService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findOrFail(Long orderId){
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
