package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.RestaurantOrderModelAssembler;
import com.philippo.algafood.api.model.RestaurantOrderModel;
import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.repository.RestaurantOrderRepository;
import com.philippo.algafood.domain.service.OrderIssuanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class RestaurantOrderController {

    @Autowired
    private RestaurantOrderRepository restaurantOrderRepository;

    @Autowired
    private OrderIssuanceService orderIssuanceService;

    @Autowired
    private RestaurantOrderModelAssembler restaurantOrderModelAssembler;

    @GetMapping
    public List<RestaurantOrderModel> list(){
        List<RestaurantOrder> allRestaurantOrders = restaurantOrderRepository.findAll();
        return restaurantOrderModelAssembler.toCollectionModel(allRestaurantOrders);
    }

    @GetMapping("/{orderId}")
    public RestaurantOrderModel findOrder(@PathVariable Long orderId) {
        RestaurantOrder restaurantOrder = orderIssuanceService.findOrFail(orderId);
        return restaurantOrderModelAssembler.toModel(restaurantOrder);
    }
}
