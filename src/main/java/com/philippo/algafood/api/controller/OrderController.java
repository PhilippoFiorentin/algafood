package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.OrderModelAssembler;
import com.philippo.algafood.api.model.OrderModel;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.domain.model.Order;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.repository.OrderRepository;
import com.philippo.algafood.domain.service.OrderIssuanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderIssuanceService orderIssuanceService;

    @Autowired
    private OrderModelAssembler orderModelAssembler;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<OrderModel> list(){
        return orderModelAssembler.toCollectionModel(orderRepository.findAll());
    }

    @GetMapping("/{orderId}")
    public OrderModel findOrder(@PathVariable Long orderId) {
        Order order = orderIssuanceService.findOrFail(orderId);

        return orderModelAssembler.toModel(order);
    }
}
